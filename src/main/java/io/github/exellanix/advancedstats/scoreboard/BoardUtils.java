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
    private static int objName = 0;
    private static DecimalFormat df = new DecimalFormat("#,##0.00");

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
        obj.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Exell"
                + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PvP");

        boards.showMessage(15);
        boards.getBoardMessage(15).setMessage1(ChatColor.GREEN + "Welcome, ");
        boards.getBoardMessage(15).setMessage2(ChatColor.GREEN + player.getName() + "!");

        boards.showMessage(14);
        boards.getBoardMessage(14).setMessage1(" ");

        boards.showMessage(13);
        boards.getBoardMessage(13).setMessage1(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Balance: ");
        boards.getBoardMessage(13).setMessage2(ChatColor.BOLD + "" + AdvancedStats.getSingleton().getEcon().getBalance(player));

        boards.showMessage(12);
        boards.getBoardMessage(12).setMessage1(" ");

        boards.showMessage(11);
        boards.getBoardMessage(11).setMessage1(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: ");
        boards.getBoardMessage(11).setMessage2(ChatColor.BOLD + ""
                + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills());

        boards.showMessage(10);
        boards.getBoardMessage(10).setMessage1(" ");

        boards.showMessage(9);
        boards.getBoardMessage(9).setMessage1(ChatColor.RED + "" + ChatColor.BOLD + "Deaths: ");
        boards.getBoardMessage(9).setMessage2(ChatColor.BOLD + ""
                + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths());

        boards.showMessage(8);
        boards.getBoardMessage(8).setMessage1(" ");

        boards.showMessage(7);
        boards.getBoardMessage(7).setMessage1(ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: ");
        boards.getBoardMessage(7).setMessage2(ChatColor.BOLD + df.format(((double) AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills()
                / AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths())));

        boards.showMessage(6);
        boards.getBoardMessage(6).setMessage1(" ");

        boards.showMessage(5);

        if (AdvancedStats.getSingleton().getKit().getPlayerKits().get(player) != null) {
            boards.getBoardMessage(5).setMessage1(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: ");
            boards.getBoardMessage(5).setMessage2(ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerKits().get(player).getName());
        } else {
            boards.getBoardMessage(5).setMessage1(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: ");
            boards.getBoardMessage(5).setMessage2(ChatColor.BOLD + "None");

        }

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        objName++;
        objName = objName % 10;
    }

    public static void updateScoreboard(PlayerBoards boards) {
        loadScores(boards);
        boards.cycleMessages();
    }
}
