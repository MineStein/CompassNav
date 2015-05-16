package info.minestein.compassnav.listener;

import info.minestein.compassnav.Core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (!player.getInventory().contains(Material.COMPASS)) {

            ItemStack compass = new ItemStack(Material.COMPASS); {
                ItemMeta m = compass.getItemMeta();
                List<String> lore = new ArrayList<>();

                for (String strings : Core.plugin.getConfig().getConfigurationSection("items.compass").getStringList("lore")) {
                    lore.add(ChatColor.translateAlternateColorCodes('&', strings));
                }

                m.setDisplayName(ChatColor.translateAlternateColorCodes('&', Core.plugin.getConfig().getConfigurationSection("items.compass").getString("name")));
                m.setLore(lore);

                compass.setItemMeta(m);
            }

            player.getInventory().setItem(4, compass);

        }
    }
}
