package com.vicmatskiv.weaponlib.command;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MainCommand extends CompatibleCommand {
   private static final String SHOW_OPTION_RECIPE = "recipe";
   private static final String SHOW_OPTION_ATTACHMENTS = "attachments";
   private static final String ARG_SHOW = "show";
   private String modId;
   private String mainCommandName;
   private ModContext modContext;

   public MainCommand(String modId, ModContext modContext) {
      this.modId = modId;
      this.modContext = modContext;
      this.mainCommandName = modId;
   }

   public String getCompatibleName() {
      return this.modId;
   }

   public String getCompatibleUsage(ICommandSender sender) {
      return "/" + this.mainCommandName + "<options>";
   }

   private String getSubCommandShowUsage() {
      return String.format("/%s %s recipe|attachments", new Object[]{this.mainCommandName, "show"});
   }

   public void execCommand(ICommandSender sender, String[] args) {
      if(args.length > 0) {
         if("show".indexOf(args[0].toLowerCase()) == 0) {
            this.processShowSubCommand(args);
         } else {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getCompatibleUsage(sender));
         }
      } else {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getCompatibleUsage(sender));
      }

   }

   private void processShowSubCommand(String[] args) {
      if(args.length < 2) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandShowUsage());
      } else {
         if("recipe".indexOf(args[1].toLowerCase()) == 0) {
            this.showRecipe();
         } else if("attachments".indexOf(args[1].toLowerCase()) == 0) {
            int page = 1;
            if(args.length == 3) {
               page = Integer.parseInt(args[2]);
            }

            this.showAttachments(page);
         } else {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), this.getSubCommandShowUsage());
         }

      }
   }

   private void showAttachments(int page) {
      ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(CompatibilityProvider.compatibility.clientPlayer());
      if(itemStack != null) {
         Item item = itemStack.getItem();
         if(item instanceof AttachmentContainer) {
            AttachmentContainer container = (AttachmentContainer)item;
            Collection compatibleAttachments = container.getCompatibleAttachments(new AttachmentCategory[]{AttachmentCategory.BULLET, AttachmentCategory.GRIP, AttachmentCategory.MAGAZINE, AttachmentCategory.SCOPE, AttachmentCategory.SILENCER, AttachmentCategory.SKIN});
            ArrayList sorted = new ArrayList(compatibleAttachments);
            sorted.sort((c1, c2) -> {
               return c1.getAttachment().getUnlocalizedName().compareTo(c2.getAttachment().getUnlocalizedName());
            });
            byte pageSize = 8;
            int offset = pageSize * (page - 1);
            if(page < 1) {
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Invalid page");
            } else if(sorted.size() == 0) {
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "No attachments found for " + item.getItemStackDisplayName(itemStack));
            } else if(offset < sorted.size()) {
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Attachments for " + item.getItemStackDisplayName(itemStack) + ", page " + page + " of " + (int)Math.ceil((double)sorted.size() / (double)pageSize));

               for(int i = offset; i < offset + pageSize && i >= 0 && i < sorted.size(); ++i) {
                  CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), " - " + ((CompatibleAttachment)sorted.get(i)).getAttachment().getItemStackDisplayName((ItemStack)null));
               }
            } else {
               CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Invalid page");
            }
         }
      }

   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   private void showRecipe() {
      ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(CompatibilityProvider.compatibility.clientPlayer());
      if(itemStack != null) {
         Item item = itemStack.getItem();
         this.showRecipe(item);
      }

   }

   private void showRecipe(Item item) {
      if(item != null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "");
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Recipe for " + item.getItemStackDisplayName((ItemStack)null));
         List recipe = this.modContext.getRecipeManager().getRecipe(item);
         if(recipe != null) {
            this.formatRecipe(recipe);
         } else {
            CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Recipe for " + item.getItemStackDisplayName((ItemStack)null) + " not found");
         }
      }

   }

   private String formatRecipe(List recipe) {
      StringBuilder output = new StringBuilder();
      HashMap decoder = new HashMap();
      boolean inRow = true;

      int i;
      Object element;
      for(i = 0; i < recipe.size(); ++i) {
         element = recipe.get(i);
         if(inRow && !(element instanceof String)) {
            inRow = false;
         }

         if(!inRow && element instanceof Character && recipe.size() > i + 1) {
            Object builder = recipe.get(i + 1);
            if(builder instanceof Item) {
               builder = ((Item)builder).getItemStackDisplayName((ItemStack)null);
            } else if(builder instanceof Block) {
               builder = ((Block)builder).getLocalizedName();
            }

            decoder.put((Character)element, builder);
            ++i;
         }
      }

      CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "");

      for(i = 0; i < recipe.size(); ++i) {
         element = recipe.get(i);
         if(!(element instanceof String)) {
            break;
         }

         StringBuilder var13 = new StringBuilder();
         char[] var8 = ((String)element).toCharArray();
         int var9 = var8.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            Character c = Character.valueOf(var8[var10]);
            Object decoded = decoder.get(c);
            var13.append(String.format("[%.20s] ", new Object[]{decoded != null?decoded:"*"}));
         }

         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "" + var13.toString());
      }

      return output.toString();
   }
}
