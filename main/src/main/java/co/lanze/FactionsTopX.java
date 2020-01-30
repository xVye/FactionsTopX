package co.lanze;

import co.lanze.commands.CommandFTop;
import co.lanze.listeners.PlayerCommandListener;
import co.lanze.utils.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FactionsTopX extends JavaPlugin {

  public static Economy economy;

  @Override
  public void onEnable() {
    if (!this.setupEconomy()) {
      Bukkit.getLogger().severe(String.format("\u001b[31;1m[%s]\u001b[0m - No Vault dependency found!",
          getDescription().getName()));
      Bukkit.getServer().getPluginManager().disablePlugin(this);
      return;
    }

    Bukkit.getLogger().info("\u001b[32;1m[Vault]\u001b[0m - Found!");

    if (!this.setupFactions()) {
      Bukkit.getLogger().severe(String.format("\u001b[31;1m[%s]\u001b[0m - No Factions dependency found!",
          getDescription().getName()));
      Bukkit.getServer().getPluginManager().disablePlugin(this);
      return;
    }

    Bukkit.getLogger().info("\u001b[32;1m[Factions]\u001b[0m - Found!");

    Bukkit.getPluginManager().registerEvents(new PlayerCommandListener(), this);
    Objects.requireNonNull(this.getCommand("ftop")).setExecutor(new CommandFTop());
  }

  private boolean setupFactions() {
    return this.getServer().getPluginManager().getPlugin("Factions") != null;
  }

  private boolean setupEconomy() {
    if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
      return false;
    }
    RegisteredServiceProvider<Economy> rsp =
        Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
      return false;
    }

    economy = rsp.getProvider();
    return economy != null;
  }
}
