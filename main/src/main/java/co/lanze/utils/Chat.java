package co.lanze.utils;

import org.bukkit.ChatColor;

public class Chat {

  public static String info(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
}
