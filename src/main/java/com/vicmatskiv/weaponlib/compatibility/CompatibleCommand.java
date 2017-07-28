package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class CompatibleCommand extends CommandBase {
   public void processCommand(ICommandSender sender, String[] args) {
      this.execCommand(sender, args);
   }

   protected abstract void execCommand(ICommandSender var1, String[] var2);

   public String getCommandName() {
      return this.getCompatibleName();
   }

   public String getCommandUsage(ICommandSender sender) {
      return this.getCompatibleUsage(sender);
   }

   protected abstract String getCompatibleName();

   protected abstract String getCompatibleUsage(ICommandSender var1);
}
