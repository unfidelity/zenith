/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.managment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.command.BindCommand;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.feature.command.DrawnCommand;
import me.gopro336.zenith.feature.command.FakePlayerCommand;
import me.gopro336.zenith.feature.command.FontCommand;
import me.gopro336.zenith.feature.command.HelpCommand;
import me.gopro336.zenith.feature.command.ModuleListCommand;
import me.gopro336.zenith.feature.command.NbtDumpCommand;
import me.gopro336.zenith.feature.command.PeekCommand;
import me.gopro336.zenith.feature.command.PrefixCommand;
import me.gopro336.zenith.feature.command.SaveCommand;
import me.gopro336.zenith.feature.command.SetCommand;
import me.gopro336.zenith.feature.command.SoftLeaveCommand;
import me.gopro336.zenith.feature.command.TeleportCommand;
import me.gopro336.zenith.feature.command.ToggleCommand;

public class CommandManager {
    private List<Command> commandList = new ArrayList<Command>();
    private String prefix = ".";

    public CommandManager() {
        this.commandList.add(new ToggleCommand());
        this.commandList.add(new TeleportCommand());
        this.commandList.add(new PrefixCommand());
        this.commandList.add(new PeekCommand());
        this.commandList.add(new HelpCommand());
        this.commandList.add(new SaveCommand());
        this.commandList.add(new BindCommand());
        this.commandList.add(new SoftLeaveCommand());
        this.commandList.add(new SetCommand());
        this.commandList.add(ModuleListCommand.INSTANCE);
        this.commandList.add(NbtDumpCommand.INSTANCE);
        this.commandList.add(new FontCommand());
        this.commandList.add(DrawnCommand.INSTANCE);
        this.commandList.add(FakePlayerCommand.INSTANCE);
    }

    public void executeCommand(String input) {
        String commandName = input.contains(" ") ? input.split(Pattern.quote(" "))[0] : input;
        Command command = this.getCommand(commandName);
        if (command == null) {
            ChatUtils.error("Command [" + commandName + "] not found");
            return;
        }
        String[] args2 = input.contains(" ") ? input.substring(input.indexOf(" ") + 1).split(" ") : new String[]{};
        try {
            command.call(args2);
        }
        catch (Throwable t) {
            ChatUtils.error("Error while executing command: " + t.getMessage());
            t.printStackTrace();
        }
        Zenith.save();
    }

    public Command getCommand(String command) {
        for (Command command2 : this.getCommandList()) {
            if (command2.getName().equalsIgnoreCase(command)) {
                return command2;
            }
            if (command2.getAlias() == null) continue;
            for (String alias : command2.getAlias()) {
                if (!alias.equalsIgnoreCase(command)) continue;
                return command2;
            }
        }
        return null;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Command> getCommandList() {
        return this.commandList;
    }
}

