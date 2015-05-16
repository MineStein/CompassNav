package info.minestein.compassnav.inventory;

import info.minestein.compassnav.Core;
import info.minestein.compassnav.util.Server;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

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
public class ServerNavigationInventory {

    public static Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, Core.plugin.getConfig().getInt("inventory-size"), ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getString("inventory-name")));

        for (Server servers : Core.servers) {
            inventory.setItem(servers.getSlot() - 1, servers.getItem());
        }

        return inventory;
    }
}
