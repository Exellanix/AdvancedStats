package io.github.exellanix.advancedstats.tasks;

import io.github.exellanix.advancedstats.AdvancedStats;
import io.github.exellanix.advancedstats.scoreboard.BoardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Brendan on 5/15/2016.
 */

public class ScoreboardUpdate implements Runnable {

    public ScoreboardUpdate() {
    }

    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            BoardUtils.updateScoreboard(AdvancedStats.getSingleton().getBoardManager().getBoard(p));
        }
    }
}
