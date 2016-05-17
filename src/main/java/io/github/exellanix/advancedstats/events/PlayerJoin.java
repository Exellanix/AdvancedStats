package io.github.exellanix.advancedstats.events;

/**
 * Created by Exellanix on 5/15/2016.
 */

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener {

    public PlayerJoin() {}

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        AdvancedStats.getSingleton().getBoardManager().createBoard(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        AdvancedStats.getSingleton().getBoardManager().deleteBoard(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        AdvancedStats.getSingleton().getBoardManager().deleteBoard(event.getPlayer());
    }

}
