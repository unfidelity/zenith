/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.command;

import me.gopro336.zenith.Zenith;
import me.gopro336.zenith.api.util.newUtil.ChatUtils;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.command.Command;
import me.gopro336.zenith.property.Property;

public class SetCommand
extends Command {
    public SetCommand() {
        super("Set", new String[]{"s"}, "Sets a value within a module", "set [module] <Property> <value>");
    }

    @Override
    public void call(String[] args2) {
        if (args2.length < 3) {
            ChatUtils.message(this.getUsage());
            return;
        }
        Feature feature = FeatureManager.getModule(args2[0]);
        if (feature == null) {
            ChatUtils.message("Can't find module " + args2[0]);
            return;
        }
        if (Zenith.SettingManager.getPropertiesByMod(feature).isEmpty()) {
            ChatUtils.message("Module " + args2[0] + " has no Properties");
            return;
        }
        for (Property<?> value : Zenith.SettingManager.getPropertiesByMod(feature)) {
            String name = value.getName().replace(" ", "");
            if (!name.equalsIgnoreCase(args2[1])) continue;
            try {
                if (value.isBoolean()) {
                    value.setValue(Boolean.parseBoolean(args2[2]));
                    ChatUtils.message("Set " + args2[1] + " in " + args2[0] + " to " + args2[2]);
                    return;
                }
                if (value.isNumber()) {
                    if (this.determineNumber(value.getValue()).equalsIgnoreCase("INTEGER")) {
                        value.setValue(Integer.parseInt(args2[3]));
                    } else if (this.determineNumber(value.getValue()).equalsIgnoreCase("FLOAT")) {
                        value.setValue(Float.valueOf(Float.parseFloat(args2[3])));
                    } else if (this.determineNumber(value.getValue()).equalsIgnoreCase("DOUBLE")) {
                        value.setValue(Double.parseDouble(args2[3]));
                    } else if (this.determineNumber(value.getValue()).equalsIgnoreCase("LONG")) {
                        value.setValue(Long.parseLong(args2[3]));
                    } else {
                        ChatUtils.message("UNKNOWN NUMBER VALUE");
                    }
                    ChatUtils.message("Set " + args2[1] + " in " + args2[0] + " to " + args2[2]);
                    return;
                }
                if (value.isEnum()) {
                    ChatUtils.message("Sorry, Enum Properties are not currently supported by the set command");
                    return;
                }
                if (value.isString()) {
                    value.setValue(args2[2]);
                    ChatUtils.message("Set " + args2[1] + " in " + args2[0] + " to " + args2[2]);
                    return;
                }
                if (!value.isColor()) continue;
                ChatUtils.message("Sorry, Color Properties are not currently supported by the set command");
                return;
            }
            catch (Exception e) {
                ChatUtils.message("An error occurred");
            }
        }
        ChatUtils.message("No such Property!");
    }

    public String determineNumber(Object o) {
        if (o instanceof Integer) {
            return "INTEGER";
        }
        if (o instanceof Float) {
            return "FLOAT";
        }
        if (o instanceof Double) {
            return "DOUBLE";
        }
        if (o instanceof Long) {
            return "LONG";
        }
        return "INVALID";
    }
}

