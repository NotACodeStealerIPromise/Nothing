package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.ExtendedState;
import com.vicmatskiv.weaponlib.state.ManagedState;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.PermitManager;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StateManager {
   private static final Logger logger = LogManager.getLogger(StateManager.class);
   private StateManager.StateComparator stateComparator;
   private Map contextRules = new HashMap();

   private static Object safeCast(Object u) {
      return u;
   }

   public StateManager(StateManager.StateComparator stateComparator) {
      this.stateComparator = stateComparator;
   }

   public StateManager.RuleBuilder in(Aspect aspect) {
      return new StateManager.RuleBuilder(aspect);
   }

   public StateManager.Result changeState(Aspect aspect, ExtendedState extendedState, ManagedState... targetStates) {
      return this.changeState(aspect, extendedState, (Permit)null, targetStates);
   }

   public StateManager.Result changeState(Aspect aspect, ExtendedState extendedState, Permit permit, ManagedState... targetStates) {
      ManagedState currentState = extendedState.getState();
      return this.changeStateFromTo(aspect, extendedState, permit, currentState, targetStates);
   }

   public StateManager.Result changeStateFromAnyOf(Aspect aspect, ExtendedState extendedState, Collection fromStates, ManagedState... targetStates) {
      ManagedState currentState = extendedState.getState();
      return !fromStates.contains(currentState)?new StateManager.Result(false, currentState):this.changeStateFromTo(aspect, extendedState, currentState, targetStates);
   }

   protected StateManager.Result changeStateFromTo(Aspect aspect, ExtendedState extendedState, ManagedState currentState, ManagedState... targetStates) {
      return this.changeStateFromTo(aspect, extendedState, (Permit)null, currentState, targetStates);
   }

   protected StateManager.Result changeStateFromTo(Aspect aspect, ExtendedState extendedState, Permit permit, ManagedState currentState, ManagedState... targetStates) {
      if(extendedState == null) {
         return null;
      } else if(targetStates.length == 1 && Arrays.stream(targetStates).anyMatch((target) -> {
         return this.stateComparator.compare(currentState, target);
      })) {
         return new StateManager.Result(false, currentState);
      } else {
         StateManager.Result result = null;
         ManagedState s = currentState;

         StateManager.TransitionRule newStateRule;
         for(ManagedState[] ts = targetStates; (newStateRule = this.findNextStateRule(aspect, extendedState, s, ts)) != null; ts = (ManagedState[])safeCast(new ManagedState[0])) {
            extendedState.setState(newStateRule.toState);
            logger.debug("Changed state of {} to {}", new Object[]{extendedState, newStateRule.toState});
            result = new StateManager.Result(true, newStateRule.toState);
            if(newStateRule.action != null) {
               result.actionResult = newStateRule.action.execute(extendedState, s, newStateRule.toState, permit);
            }

            s = newStateRule.toState;
         }

         if(result == null) {
            result = new StateManager.Result(false, s);
         }

         return result;
      }
   }

   private StateManager.TransitionRule findNextStateRule(Aspect aspect, ExtendedState extendedState, ManagedState currentState, ManagedState... targetStates) {
      return (StateManager.TransitionRule)this.contextRules.entrySet().stream().filter((e) -> {
         return e.getKey() == aspect;
      }).map((e) -> {
         return (LinkedHashSet)e.getValue();
      }).flatMap(Collection::stream).filter((rule) -> {
         return rule.matches(this.stateComparator, extendedState, currentState, targetStates);
      }).findFirst().orElse((Object)null);
   }

   private static class TransitionRule {
      ManagedState fromState;
      ManagedState toState;
      BiPredicate predicate;
      StateManager.PostAction action;
      boolean auto;

      TransitionRule(ManagedState fromState, ManagedState toState, BiPredicate predicate, StateManager.PostAction action, boolean auto) {
         if(fromState == null) {
            throw new IllegalArgumentException("From-state cannot be null");
         } else if(toState == null) {
            throw new IllegalArgumentException("To-state cannot be null");
         } else {
            this.fromState = fromState;
            this.toState = toState;
            this.predicate = predicate;
            this.action = action;
            this.auto = auto;
         }
      }

      boolean matches(StateManager.StateComparator stateComparator, ExtendedState context, ManagedState fromState, ManagedState... targetStates) {
         boolean result = fromState == null || stateComparator.compare(this.fromState, fromState);
         result = result && (this.auto && targetStates.length == 0 || Arrays.stream(targetStates).anyMatch((targetState) -> {
            return stateComparator.compare(this.toState, targetState) || stateComparator.compare(this.toState, targetState.preparingPhase()) || stateComparator.compare(this.toState, targetState.permitRequestedPhase());
         }));
         result = result && this.predicate.test(this.toState, context);
         return result;
      }
   }

   public interface VoidAction2 {
      void execute(Object var1);
   }

   public interface VoidAction {
      void execute(Object var1, ManagedState var2, ManagedState var3);
   }

   public interface VoidPostAction {
      void execute(Object var1, ManagedState var2, ManagedState var3, Permit var4);
   }

   public interface PostAction {
      Object execute(Object var1, ManagedState var2, ManagedState var3, Permit var4);
   }

   public class Result {
      private boolean stateChanged;
      private ManagedState state;
      protected Object actionResult;

      private Result(boolean stateChanged, ManagedState targetState) {
         this.stateChanged = stateChanged;
         this.state = targetState;
      }

      public boolean isStateChanged() {
         return this.stateChanged;
      }

      public ManagedState getState() {
         return this.state;
      }

      public Object getActionResult() {
         return this.actionResult;
      }

      // $FF: synthetic method
      Result(boolean x1, ManagedState x2, Object x3) {
         this();
      }
   }

   public interface StateComparator {
      boolean compare(ManagedState var1, ManagedState var2);
   }

   public class RuleBuilder {
      private static final long DEFAULT_REQUEST_TIMEOUT = 10000L;
      private Aspect aspect;
      private ManagedState fromState;
      private ManagedState toState;
      private StateManager.VoidAction prepareAction;
      private StateManager.PostAction action;
      private BiPredicate predicate;
      private BiFunction permitProvider;
      private BiFunction stateUpdater;
      private PermitManager permitManager;
      private Predicate preparePredicate;
      private long requestTimeout = 10000L;
      private boolean isPermitRequired;

      public RuleBuilder(Aspect aspect) {
         this.aspect = aspect;
      }

      public StateManager.RuleBuilder prepare(StateManager.VoidAction prepareAction, Predicate preparePredicate) {
         this.prepareAction = prepareAction;
         this.preparePredicate = preparePredicate;
         return this;
      }

      public StateManager.RuleBuilder change(ManagedState fromState) {
         this.fromState = fromState;
         return this;
      }

      public StateManager.RuleBuilder to(ManagedState state) {
         this.toState = state;
         return this;
      }

      public StateManager.RuleBuilder when(Predicate predicate) {
         this.predicate = (s, e) -> {
            return predicate.test(e);
         };
         return this;
      }

      public StateManager.RuleBuilder when(BiPredicate predicate) {
         this.predicate = predicate;
         return this;
      }

      public StateManager.RuleBuilder withPermit(BiFunction permitProvider, BiFunction stateUpdater, PermitManager permitManager) {
         this.isPermitRequired = true;
         this.permitProvider = permitProvider;
         this.stateUpdater = stateUpdater;
         this.permitManager = permitManager;
         return this;
      }

      public StateManager.RuleBuilder withAction(StateManager.VoidPostAction action) {
         this.action = (context, from, to, permit) -> {
            action.execute(context, from, to, permit);
            return null;
         };
         return this;
      }

      public StateManager.RuleBuilder withAction(StateManager.VoidAction2 action) {
         this.action = (context, from, to, permit) -> {
            action.execute(context);
            return null;
         };
         return this;
      }

      public StateManager automatic() {
         return this.addRule(true);
      }

      public StateManager manual() {
         return this.addRule(false);
      }

      private StateManager addRule(boolean auto) {
         LinkedHashSet aspectRules = (LinkedHashSet)StateManager.this.contextRules.computeIfAbsent(this.aspect, (c) -> {
            return new LinkedHashSet();
         });
         if(this.predicate == null) {
            this.predicate = (s, c) -> {
               return true;
            };
         }

         if(this.action == null) {
            this.action = (c, f, t, p) -> {
               return null;
            };
         }

         ManagedState effectiveFromState;
         BiPredicate effectivePredicate;
         boolean isRequestRuleAutoTransitioned;
         StateManager.TransitionRule directTransitionRule;
         if(this.prepareAction == null && this.preparePredicate == null) {
            effectiveFromState = this.fromState;
            effectivePredicate = (s, e) -> {
               return this.predicate.test(s, StateManager.safeCast(e));
            };
            isRequestRuleAutoTransitioned = false;
         } else {
            if(auto) {
               throw new IllegalStateException("Prepared transition cannot be automatic");
            }

            directTransitionRule = new StateManager.TransitionRule(this.fromState, this.toState.preparingPhase(), (s, e) -> {
               return this.predicate.test(s, StateManager.safeCast(e));
            }, (c, f, t, p) -> {
               if(this.prepareAction != null) {
                  this.prepareAction.execute(StateManager.safeCast(c), f, t);
               }

               return null;
            }, false);
            aspectRules.add(directTransitionRule);
            effectiveFromState = this.toState.preparingPhase();
            effectivePredicate = (s, e) -> {
               return this.preparePredicate != null?this.preparePredicate.test(StateManager.safeCast(e)):true;
            };
            isRequestRuleAutoTransitioned = true;
         }

         if(this.isPermitRequired) {
            if(auto) {
               throw new IllegalStateException("Permitted transitions cannot be automatic");
            }

            directTransitionRule = new StateManager.TransitionRule(effectiveFromState, this.toState.permitRequestedPhase(), effectivePredicate, (s, f, t, p) -> {
               this.permitManager.request(p != null?p:(Permit)this.permitProvider.apply(t, StateManager.safeCast(s)), s, this::applyPermit);
               return null;
            }, isRequestRuleAutoTransitioned);
            aspectRules.add(directTransitionRule);
            StateManager.TransitionRule rollbackRule = new StateManager.TransitionRule(this.toState.permitRequestedPhase(), this.fromState, (s, c) -> {
               return System.currentTimeMillis() > c.getStateUpdateTimestamp() + this.requestTimeout;
            }, (c, f, t, p) -> {
               return this.action.execute(StateManager.safeCast(c), f, t, p);
            }, true);
            aspectRules.add(rollbackRule);
         } else {
            directTransitionRule = new StateManager.TransitionRule(effectiveFromState, this.toState, effectivePredicate, (c, f, t, p) -> {
               return this.action.execute(StateManager.safeCast(c), f, t, p);
            }, auto);
            aspectRules.add(directTransitionRule);
         }

         return StateManager.this;
      }

      private void applyPermit(Permit processedPermit, ExtendedState updatedState) {
         ManagedState updateToState = processedPermit.getStatus() == Permit.Status.GRANTED?this.toState:this.fromState;
         StateManager.logger.debug("Applying permit with status {} to {}, changing state to {}", new Object[]{processedPermit.getStatus(), updatedState, this.toState});
         if(((Boolean)this.stateUpdater.apply(updateToState, StateManager.safeCast(updatedState))).booleanValue()) {
            this.action.execute(StateManager.safeCast(updatedState), this.fromState, this.toState, processedPermit);
         }

      }
   }
}
