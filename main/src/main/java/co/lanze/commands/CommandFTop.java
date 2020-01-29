package co.lanze.commands;

import co.lanze.utils.Chat;
import com.massivecraft.factions.Factions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class CommandFTop implements CommandExecutor {

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) {
      Bukkit.getLogger().info("This command is only available to players.");
      return true;
    }

    sendToplist(((Player) sender).getPlayer());

    return true;
  }

  public static void sendToplist(Player p) {
    if (p == null) {
      return;
    }

    AtomicInteger i = new AtomicInteger();

    p.sendMessage(Chat.info("&a--------------- &l[Top Factions]&r &a---------------"));
    Factions.getInstance().getAllFactions().forEach(faction -> {
      if (!faction.getComparisonTag().equalsIgnoreCase("wilderness") ||
      !faction.getComparisonTag().equalsIgnoreCase("safezone") ||
      !faction.getComparisonTag().equalsIgnoreCase("warzone") &&
      i.get() >= 10) {
        i.getAndIncrement();
        p.sendMessage(Chat.info("&a #" + i.toString() + " " + faction.getComparisonTag()));
      }
    });
    p.sendMessage(Chat.info("&a----------------------------------------------"));
  }
}
