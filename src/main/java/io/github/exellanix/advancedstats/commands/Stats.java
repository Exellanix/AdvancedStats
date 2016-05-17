package io.github.exellanix.advancedstats.commands;

/**
 * Created by Exellanix on 5/15/2016.
 */
import java.text.DecimalFormat;

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        final DecimalFormat df = new DecimalFormat("#,##0.00");
        if (cmd.getName().equalsIgnoreCase("stats")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(
                            ChatColor.GREEN + "" + ChatColor.BOLD + "----== Stats of " + player.getName() + " ==----");
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Balance: " + ChatColor.WHITE + ChatColor.BOLD + AdvancedStats.getSingleton().getEcon().getBalance(player));
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: " + ChatColor.WHITE + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills());
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ChatColor.WHITE + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths());
                    player.sendMessage(" ");
                    player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: " + ChatColor.WHITE + ChatColor.BOLD + df.format(((double) AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills()
                            / AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths())));
                    player.sendMessage(" ");
                    if (AdvancedStats.getSingleton().getKit().getPlayerKits().get(player) != null) {
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                                + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerKits().get(player).getName());
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                                + ChatColor.BOLD + "None");
                    }
                    player.sendMessage(" ");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.RED + "You can not use this command like that!");
                    return true;
                }
            } else if (args.length == 1) {
                for(Player online : Bukkit.getOnlinePlayers()) {
                    if(args[0].equals(online.getName())) {
                        sender.sendMessage(
                                ChatColor.GREEN + "" + ChatColor.BOLD + "----== Stats of " + online.getName() + " ==----");
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Balance: " + ChatColor.WHITE + ChatColor.BOLD + AdvancedStats.getSingleton().getEcon().getBalance(online));
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: " + ChatColor.WHITE +AdvancedStats.getSingleton().getKit().getPlayerStats(online).getTotalKills());
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ChatColor.WHITE + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerStats(online).getTotalDeaths());
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: " + ChatColor.WHITE + ChatColor.BOLD + df.format(((double) AdvancedStats.getSingleton().getKit().getPlayerStats(online).getTotalKills()
                                / AdvancedStats.getSingleton().getKit().getPlayerStats(online).getTotalDeaths())));
                        sender.sendMessage(" ");
                        if (AdvancedStats.getSingleton().getKit().getPlayerKits().get(online) != null) {
                            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                                    + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerKits().get(online).getName());
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                                    + ChatColor.BOLD + "None");
                        }
                        sender.sendMessage(" ");
                        break;
                    }else{
                        sender.sendMessage(ChatColor.RED + "That player is not online!");
                        return true;
                    }
                }
                return true;

            } else {
                sender.sendMessage(ChatColor.RED + "Too many arguments!");
                return true;
            }
        }
        return false;
    }

}