package io.github.exellanix.advancedstats.events;

/**
 * Created by Exellanix on 5/15/2016.
 */
import java.util.HashMap;

import io.github.exellanix.advancedstats.AdvancedStats;
import io.github.exellanix.advancedstats.tasks.ScoreboardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerJoin implements Listener {

    HashMap<Player, Integer> scoreUpdate;

    public PlayerJoin() {
        scoreUpdate = new HashMap<>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        player.setScoreboard(board);

        scoreUpdate.put(player, Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(AdvancedStats.plugin, new ScoreboardUpdate(player), 0, 20));


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        scoreUpdate.remove(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        scoreUpdate.remove(event.getPlayer());
    }

}
