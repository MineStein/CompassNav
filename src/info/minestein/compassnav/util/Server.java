package info.minestein.compassnav.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
public class Server {

    ItemStack item;
    Integer slot;

    public Server(String pointsTo, Material type, String name, Integer slot) {
        this.slot = slot;

        this.item = new ItemStack(type); {
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName("§7§oConnect " + name);
            meta.setLore(Arrays.asList("§a§lClick §7to connect to server", "§e" + ServerSender.getPlayerAmount(pointsTo) + " online players", "§e§o" + pointsTo));

            item.setItemMeta(meta);
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public Integer getSlot() {
        return slot;
    }
}
