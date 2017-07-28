package com.vicmatskiv.weaponlib.command;

import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;

public class DebugCommand extends CompatibleCommand {
   private static final String SHOW_OPTION_CODE = "code";
   private static final String COMMAND_DEBUG = "wdb";
   private static final String DEBUG_ARG_ON = "on";
   private static final String DEBUG_ARG_OFF = "off";
   private static final String DEBUG_ARG_PAUSE = "pause";
   private static final String DEBUG_ARG_PART = "part";
   private static final String DEBUG_ARG_SCALE = "scale";
   private static final String DEBUG_ARG_SHOW = "show";
   private static final String DEBUG_ARG_WATCH = "watch";
   private static final String DEBUG_ARG_STEP = "step";
   private String modId;

   public DebugCommand(String modId) {
      this.modId = modId;
   }

   public String getCompatibleName() {
      return "wdb";
   }

   public String getCompatibleUsage(ICommandSender sender) {
      return "/wdb<options>";
   }

   private String getSubCommandDebugUsage() {
      return "/wdb <on|off>";
   }

   private String getSubCommandPauseUsage() {
      return String.format("/%s %s <transition-number> <pause-duration>", new Object[]{"wdb", "pause"});
   }

   private String getSubCommandPartUsage() {
      return String.format("/%s %s main|lhand|rhand", new Object[]{"wdb", "part"});
   }

   private String getSubCommandShowUsage() {
      return String.format("/%s %s code", new Object[]{"wdb", "show"});
   }

   private String getSubCommandScaleUsage() {
      return String.format("/%s %s <scale>", new Object[]{"wdb", "scale"});
   }

   private String getSubCommandStepUsage() {
      return String.format("/%s %s <scale>", new Object[]{"wdb", "step"});
   }

   private String getSubCommandWatchUsage() {
      return String.format("/%s %s [entity-id]", new Object[]{"wdb", "watch"});
   }

   public void execCommand(ICommandSender sender, String[] args) {
      if(args.length > 0) {
         String var3 = args[0].toLowerCase();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case 3551:
            if(var3.equals("on")) {
               var4 = 0;
            }
            break;
         case 109935:
            if(var3.equals("off")) {
               var4 = 1;
            }
            break;
         case 3433459:
            if(var3.equals("part")) {
               var4 = 3;
            }
            break;
         case 3529469:
            if(var3.equals("show")) {
               var4 = 4;
            }
            break;
         case 3540684:
            if(var3.equals("step")) {
               var4 = 6;
            }
            break;
         case 106440182:
            if(var3.equals("pause")) {
               var4 = 2;
            }
            break;
         case 109250890:
            if(var3.equals("scale")) {
               var4 = 5;
            }
            break;
         case 112903375:
            if(var3.equals("watch")) {
               var4 = 7;
            }
         }

         switch(var4) {
         case 0:
            this.processDebugModeSubCommand(args);
            break;
         case 1:
            this.processDebugModeSubCommand(args);
            break;
         case 2:
            this.processPauseSubCommand(args);
            break;
         case 3:
            this.processPartSubCommand(args);
            break;
         case 4:
            this.processShowSubCommand(args);
            break;
         case 5:
            this.processScaleSubCommand(args);
            break;
         case 6:
            this.processStepSubCommand(args);
            break;
         case 7:
            this.processWatchSubCommand(args);
            break;
         default:
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getCompatibleUsage(sender));
         }
      } else {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getCompatibleUsage(sender));
      }

   }

   private void processDebugModeSubCommand(String[] args) {
      Boolean debugMode = null;
      String var3 = args[0].toLowerCase();
      byte var4 = -1;
      switch(var3.hashCode()) {
      case 3551:
         if(var3.equals("on")) {
            var4 = 0;
         }
         break;
      case 109935:
         if(var3.equals("off")) {
            var4 = 1;
         }
      }

      switch(var4) {
      case 0:
         debugMode = Boolean.valueOf(true);
         break;
      case 1:
         debugMode = Boolean.valueOf(false);
      }

      if(debugMode != null) {
         DebugPositioner.setDebugMode(debugMode.booleanValue());
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug mode " + args[0].toLowerCase());
      } else {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandDebugUsage());
      }

   }

   private void processPauseSubCommand(String[] args) {
      if(args.length != 3) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandPauseUsage());
      } else {
         try {
            int e = Integer.parseInt(args[1]);
            long pauseDuration = Long.parseLong(args[2]);
            DebugPositioner.configureTransitionPause(e, pauseDuration);
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Set transition " + e + " pause to " + pauseDuration + "ms");
         } catch (NumberFormatException var5) {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandPauseUsage());
         }

      }
   }

   private void processWatchSubCommand(String[] args) {
      if(args.length < 1) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandWatchUsage());
      } else {
         DebugPositioner.watch();
      }
   }

   private void processScaleSubCommand(String[] args) {
      if(args.length != 2) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandScaleUsage());
      } else if(DebugPositioner.getDebugPart() == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         try {
            float e = Float.parseFloat(args[1]);
            DebugPositioner.setScale(e);
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Set scale to " + e);
         } catch (NumberFormatException var3) {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandScaleUsage());
         }

      }
   }

   private void processStepSubCommand(String[] args) {
      if(args.length != 2) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandStepUsage());
      } else if(DebugPositioner.getDebugPart() == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         try {
            float e = Float.parseFloat(args[1]);
            DebugPositioner.setStep(e);
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Set step to " + e);
         } catch (NumberFormatException var3) {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandStepUsage());
         }

      }
   }

   private void processShowSubCommand(String[] args) {
      if(args.length != 2) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandShowUsage());
      } else if(DebugPositioner.getDebugPart() == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         String var2 = args[1].toLowerCase();
         byte var3 = -1;
         switch(var2.hashCode()) {
         case 3059181:
            if(var2.equals("code")) {
               var3 = 0;
            }
         default:
            switch(var3) {
            case 0:
               DebugPositioner.showCode();
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Code is copied to the console");
               break;
            default:
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandShowUsage());
            }

         }
      }
   }

   private void processPartSubCommand(String[] args) {
      if(args.length != 2) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandPartUsage());
      } else {
         try {
            String e = args[1].toLowerCase();
            byte var3 = -1;
            switch(e.hashCode()) {
            case -2020599460:
               if(e.equals("inventory")) {
                  var3 = 3;
               }
               break;
            case 3343801:
               if(e.equals("main")) {
                  var3 = 0;
               }
               break;
            case 102935259:
               if(e.equals("lhand")) {
                  var3 = 1;
               }
               break;
            case 108476385:
               if(e.equals("rhand")) {
                  var3 = 2;
               }
            }

            switch(var3) {
            case 0:
               DebugPositioner.setDebugPart(Part.MAIN_ITEM);
               break;
            case 1:
               DebugPositioner.setDebugPart(Part.LEFT_HAND);
               break;
            case 2:
               DebugPositioner.setDebugPart(Part.RIGHT_HAND);
               break;
            case 3:
               DebugPositioner.setDebugPart(Part.INVENTORY);
               break;
            default:
               String partName = args[1];
               Item item = CompatibilityProvider.compatibility.findItemByName(this.modId, partName);
               Part part = null;
               if(item instanceof Part) {
                  part = (Part)item;
               } else if(item instanceof ItemAttachment) {
                  part = ((ItemAttachment)item).getRenderablePart();
               }

               if(part != null) {
                  DebugPositioner.setDebugPart(part);
               }
            }

            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debugging part " + args[1]);
         } catch (NumberFormatException var7) {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandPartUsage());
         }

      }
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }
}
