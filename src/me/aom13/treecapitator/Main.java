package me.aom13.treecapitator;

import me.aom13.treecapitator.commands.HelloCommands;
import me.aom13.treecapitator.listener.TreeListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public Main(){
    }

    @Override
    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(new TreeListener(), this);
        new HelloCommands(this);
    }

    public void onDisable() {
    }
}
