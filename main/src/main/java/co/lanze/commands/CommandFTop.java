package co.lanze.commands;

import co.lanze.FactionsTopX;
import co.lanze.utils.Chat;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import com.massivecraft.factions.integration.Econ;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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

    Map<String, Double> factionsEconomy = new LinkedHashMap<>();

    p.sendMessage(Chat.info("&6________________.[ &aTop Factions &6]._________________"));
    Factions.getInstance().getAllFactions().forEach(faction -> {
      if (!faction.getComparisonTag().equalsIgnoreCase("Warzone") &&
      !faction.getComparisonTag().equalsIgnoreCase("Wilderness") &&
      !faction.getComparisonTag().equalsIgnoreCase("Safezone") &&
      i.get() <= 10) {
        i.getAndIncrement();
        double playersEconomy = faction.getFPlayers().stream().mapToDouble(fPlayer ->
            FactionsTopX.economy.getBalance(fPlayer.getPlayer())).sum();

        factionsEconomy.put(faction.getTag(), playersEconomy);
      }
    });

    factionsEconomy.entrySet()
        .stream()
        .sorted()
        .forEachOrdered(f -> {
          i.getAndIncrement();
          p.sendMessage(Chat.info("&2#" + i.toString() + " &f" + f.getKey() + " &2- &a" +
              String.format("$%,.2f", f.getValue())));
        });
  }
}
