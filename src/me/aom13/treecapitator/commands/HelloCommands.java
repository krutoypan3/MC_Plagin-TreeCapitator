package me.aom13.treecapitator.commands;

import me.aom13.treecapitator.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelloCommands implements CommandExecutor {
    private Main plugin;
    public  HelloCommands(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("hello").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("Только для игроков!");
            return true;
        }
        Player p = (Player) commandSender;

        if (p.hasPermission("hello.use")){
            p.sendMessage("Приветики");
            return true;
        }
        else{
            p.sendMessage("У тя нет прав");
        }

        return false;
    }
}
