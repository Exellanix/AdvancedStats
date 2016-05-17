package io.github.exellanix.advancedstats.scoreboard;

import io.github.exellanix.advancedstats.AdvancedStats;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
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
        Scoreboard disabled = boards.getScoreboard();
        Player player = boards.getPlayer();

        Objective obj = boards.resetDeactiveObjective();

        obj.setDisplayName(
                ChatColor.GREEN + "" + ChatColor.BOLD + "Exell" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PvP");
        Score score = obj.getScore(ChatColor.GREEN + "Welcome, " + player.getName() + "!");
        score.setScore(10);

        Score score8 = obj.getScore("              ");
        score8.setScore(9);

        Score score7 = obj.getScore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Balance: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.getSingleton().getEcon().getBalance(player));
        score7.setScore(8);

        Score score1 = obj.getScore("           ");
        score1.setScore(7);

        Score score2 = obj.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills());
        score2.setScore(6);

        Score score3 = obj.getScore("      ");
        score3.setScore(5);

        Score score4 = obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths());
        score4.setScore(4);

        Score score5 = obj.getScore(" ");
        score5.setScore(3);

        Score score6 = obj.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + df.format(((double) AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalKills()
                / AdvancedStats.getSingleton().getKit().getPlayerStats(player).getTotalDeaths())));
        score6.setScore(2);

        Score score9 = obj.getScore("               ");
        score9.setScore(1);

        Score score10;
        if (AdvancedStats.getSingleton().getKit().getPlayerKits().get(player) != null) {
            score10 = obj.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                    + ChatColor.BOLD + AdvancedStats.getSingleton().getKit().getPlayerKits().get(player).getName());
        } else {
            score10 = obj.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                    + ChatColor.BOLD + "None");
        }
        score10.setScore(0);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        objName++;
        objName = objName % 10;
    }

    public static void swapObjectives(PlayerBoards boards) {
        Objective temp = boards.getActive();
        boards.setActive(boards.getDeactive());
        boards.setDeactive(temp);
        boards.getActive().setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public static void updateScoreboard(PlayerBoards boards) {
        loadScores(boards);
    }
}
