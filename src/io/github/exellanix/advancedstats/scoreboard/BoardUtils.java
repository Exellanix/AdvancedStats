package io.github.exellanix.advancedstats.scoreboard;

import io.github.exellanix.advancedstats.AdvancedStats;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Mac on 5/16/2016.
 */
public class BoardUtils {
    private static DecimalFormat df = new DecimalFormat("#,##0.00");
    private static String objDisplayName = ChatColor.GREEN + "" + ChatColor.BOLD + "    Exell"
            + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PvP    ";

    public static void clearEntries(Scoreboard scoreboard) {
        for (String entry : new ArrayList<>(scoreboard.getEntries())) {
            scoreboard.resetScores(entry);
        }
    }

    public static void clearObjectives(Scoreboard scoreboard) {
        for (Objective obj : new ArrayList<>(scoreboard.getObjectives())) {
            obj.unregister();
        }
    }

    public static void loadScores(PlayerBoards boards) {
        Player player = boards.getPlayer();

        Objective obj = boards.getActive();

        boards.showMessage(10);
        if (!obj.getDisplayName().equals(objDisplayName)) {
            obj.setDisplayName(objDisplayName);
        }

        boards.showMessage(15);
        boards.getBoardMessage(15).setMessage1((ChatColor.GREEN + "Hi, " + player.getName() + "! Welcome to Exell" + ChatColor.DARK_PURPLE + "PvP" + ChatColor.GREEN + "!").trim());

        boards.showMessage(14);
        boards.getBoardMessage(14).setMessage1(" ");

        boards.showMessage(13);
        boards.getBoardMessage(13).setMessage1(ChatColor.DARK_PURPLE + "> " + ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: " + ChatColor.WHITE + "" + ChatColor.BOLD + ""
                + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills());

        boards.showMessage(12);
        boards.getBoardMessage(12).setMessage1(" ");

        boards.showMessage(11);
        boards.getBoardMessage(11).setMessage1(ChatColor.DARK_PURPLE + "> " + ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ChatColor.WHITE + "" + ChatColor.BOLD + ""
                + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths());

        boards.showMessage(10);
        boards.getBoardMessage(10).setMessage1(" ");

        boards.showMessage(9);
        boards.getBoardMessage(9).setMessage1(ChatColor.DARK_PURPLE + "> " + ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: " + ChatColor.WHITE + "" + ChatColor.BOLD + df.format(((double) AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills()
                / AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths())));

        boards.showMessage(8);
        boards.getBoardMessage(8).setMessage1(" ");

        boards.showMessage(7);

        if (AdvancedStats.getSingleton().getKit().getPlayerKits().get(player) != null) {
            boards.getBoardMessage(7).setMessage1(ChatColor.DARK_PURPLE + "> " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + "" + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerKits().get(player).getName());
        } else {
            boards.getBoardMessage(7).setMessage1(ChatColor.DARK_PURPLE + "> " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + "" + ChatColor.BOLD + "None");

        }

        if (obj.getDisplaySlot() != DisplaySlot.SIDEBAR){
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }

    public static void updateScoreboard(PlayerBoards boards) {
        boards.cycleMessages();
        loadScores(boards);
    }
}
