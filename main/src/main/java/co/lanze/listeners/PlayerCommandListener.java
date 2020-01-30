package co.lanze.listeners;

import co.lanze.commands.CommandFTop;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandListener implements Listener {
  @EventHandler
  public boolean onPlayerCommandPreprocessor(PlayerCommandPreprocessEvent e) {
    String message = e.getMessage();

    if (message.contains("/f top")) {
      CommandFTop.sendToplist(e.getPlayer());
      e.setCancelled(true);
    }

    return true;
  }
}
