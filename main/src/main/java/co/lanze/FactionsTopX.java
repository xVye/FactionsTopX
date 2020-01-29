package co.lanze;

import co.lanze.commands.CommandFTop;
import co.lanze.listeners.PlayerCommandListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class FactionsTopX extends JavaPlugin {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(new PlayerCommandListener(), this);
    Objects.requireNonNull(this.getCommand("ftop")).setExecutor(new CommandFTop());
  }
}
