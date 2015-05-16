package info.minestein.compassnav.util;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import info.minestein.compassnav.Core;
import org.bukkit.entity.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
public class ServerSender {

    public static Integer getPlayerAmount(String server) {
        try {
            Socket socket = new Socket(Core.plugin.getConfig().getConfigurationSection("servers." + server).getString("ip"), Core.plugin.getConfig().getConfigurationSection("servers." + server).getInt("port"));
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.write(0xFE);

            int b;
            StringBuffer str = new StringBuffer();

            while ((b = in.read()) != -1) {
                if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {

                    str.append((char) b);

                }
            }

            String[] data = str.toString().split("§");

            return Integer.parseInt(data[1]);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void sendToServer(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF(server);

        player.sendPluginMessage(Core.plugin, "BungeeCord", out.toByteArray());
    }
}
