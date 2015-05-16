package info.minestein.compassnav.listener;

import info.minestein.compassnav.Core;
import info.minestein.compassnav.inventory.ServerNavigationInventory;
import info.minestein.compassnav.util.ServerSender;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (player.getItemInHand().getType().equals(Material.COMPASS) && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getConfigurationSection("items.compass").getString("name")))) {

            player.openInventory(ServerNavigationInventory.getInventory());

        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();

        if (event.getInventory().getName().equals(ServerNavigationInventory.getInventory().getName())) {

            if (event.getCurrentItem() == null) return;

            event.setCancelled(true);

            ServerSender.sendToServer(player, event.getCurrentItem().getItemMeta().getLore().get(2).substring(4));
        }
    }
}
