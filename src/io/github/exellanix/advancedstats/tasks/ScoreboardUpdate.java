package io.github.exellanix.advancedstats.tasks;

/**
 * Created by Brendan on 5/15/2016.
 */
import java.text.DecimalFormat;

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;

public class ScoreboardUpdate implements Runnable {

    private int objName = 0;
    private Player player;

    final DecimalFormat df = new DecimalFormat("#,##0.00");

    public ScoreboardUpdate(Player player) {
        this.player = player;

        clearObjectives(player.getScoreboard());
    }

    public void run() {
        Scoreboard board = player.getScoreboard();


        if (board.getObjective("stats" + objName) != null) {
            board.getObjective("stats" + objName).unregister(); }


        Objective obj;
        if (board.getObjective("stats" + objName) != null) {
            obj = board.getObjective("stats" + objName);
        } else {
            obj = board.registerNewObjective("stats" + objName, "dummy");
        }
        for(String s : board.getEntries()) {
            board.resetScores(s);
        }

        obj.setDisplayName(
                ChatColor.GREEN + "" + ChatColor.BOLD + "Exell" + ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "PvP");
        Score score = obj.getScore(ChatColor.GREEN + "Welcome, " + player.getName() + "!");
        score.setScore(10);

        Score score8 = obj.getScore("              ");
        score8.setScore(9);

        Score score7 = obj.getScore(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Balance: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.econ.getBalance(player));
        score7.setScore(8);

        Score score1 = obj.getScore("           ");
        score1.setScore(7);

        Score score2 = obj.getScore(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.kit.getPlayerStats(player).getTotalKills());
        score2.setScore(6);

        Score score3 = obj.getScore("      ");
        score3.setScore(5);

        Score score4 = obj.getScore(ChatColor.RED + "" + ChatColor.BOLD + "Deaths: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + AdvancedStats.kit.getPlayerStats(player).getTotalDeaths());
        score4.setScore(4);

        Score score5 = obj.getScore(" ");
        score5.setScore(3);

        Score score6 = obj.getScore(ChatColor.YELLOW + "" + ChatColor.BOLD + "KDR: " + ChatColor.WHITE + ""
                + ChatColor.BOLD + df.format(((double) AdvancedStats.kit.getPlayerStats(player).getTotalKills()
                / AdvancedStats.kit.getPlayerStats(player).getTotalDeaths())));
        score6.setScore(2);

        Score score9 = obj.getScore("               ");
        score9.setScore(1);

        Score score10;
        if (AdvancedStats.kit.getPlayerKits().get(player) != null) {
            score10 = obj.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                    + ChatColor.BOLD + AdvancedStats.kit.getPlayerKits().get(player).getName());
        } else {
            score10 = obj.getScore(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Kit: " + ChatColor.WHITE + ""
                    + ChatColor.BOLD + "None");
        }
        score10.setScore(0);

        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        objName++;
        objName = objName % 10;

    }

    private void clearObjectives(Scoreboard board) {
        for (int i = 0; i < 10; i++) {
            if (board.getObjective("stats" + i) != null) {
                board.getObjective("stats" + i).unregister();
            }
        }
    }

}
