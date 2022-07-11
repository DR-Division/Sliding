package com.division.sliding.command;

import com.division.sliding.file.SneakConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SlidingCommand implements CommandExecutor {

    private final SneakConfig config;

    public SlidingCommand(SneakConfig config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() && args.length >= 1 && (args[0].equals("리로드") || args[0].equalsIgnoreCase("reload"))) {
            config.load();
            sender.sendMessage("리로드 완료");
            return true;
        }
        return false;
    }
}
