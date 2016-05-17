package io.github.exellanix.advancedstats.events;

/**
 * Created by Exellanix on 5/15/2016.
 */
import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import net.milkbowl.vault.economy.EconomyResponse;

public class PlayerDeath implements Listener {

    //TODO Remove this and move it to AdvancedCombat to get the actual killer!
    // ^^ Do that only when Exellanix is ready

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = event.getEntity();
            Player killer = event.getEntity().getKiller();
            EconomyResponse r = AdvancedStats.getSingleton().getEcon().depositPlayer(killer, 1.00);
            if (r.transactionSuccess()) {
                killer.sendMessage(ChatColor.GREEN + "You have recieved $1 for killing" +  player.getDisplayName());
            }

            player.sendMessage(ChatColor.RED + "You have been killed by " + killer.getDisplayName() + "!");

        }

    }

}
