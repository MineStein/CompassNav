package info.minestein.compassnav;

import info.minestein.compassnav.listener.InteractListener;
import info.minestein.compassnav.listener.JoinListener;
import info.minestein.compassnav.listener.MessageListener;
import info.minestein.compassnav.util.Server;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * ****************************************************************************************
 * Copyright MineStein © 2015
 * <p/>
 * Any code contained within this document, and any associated API's with similar branding
 * are the sole property of MineStein. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * <p/>
 * Thanks, and have a nice day.
 * ****************************************************************************************
 */
public class Core extends JavaPlugin {

    public static List<Server> servers;
    public static Core plugin;

    @Override
    public void onEnable() {
        plugin = this;
        servers = new ArrayList<>();

        getConfig().options().copyDefaults(true);
        saveConfig();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new MessageListener());

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                reloadConfig();

                servers.clear();

                for (String strings : getConfig().getConfigurationSection("servers").getKeys(false)) {
                    servers.add(new Server(strings, Material.getMaterial(Core.plugin.getConfig().getConfigurationSection("servers." + strings).getInt("item")), ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getConfigurationSection("servers." + strings).getString("name")), Core.plugin.getConfig().getConfigurationSection("servers." + strings).getInt("slot")));
                }
            }
        }, 0, 60);
    }
}
