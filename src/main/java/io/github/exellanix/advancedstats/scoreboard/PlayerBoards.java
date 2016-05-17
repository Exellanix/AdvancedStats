package io.github.exellanix.advancedstats.scoreboard;

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

/**
 * Created by Mac on 5/16/2016.
 */
public class PlayerBoards {
    private Scoreboard scoreboard;
    private Objective active;
    private Objective deactive;
    private Player player;

    public PlayerBoards(Player player) {
        this.scoreboard = AdvancedStats.getSingleton().getServer().getScoreboardManager().getNewScoreboard();
        active = this.scoreboard.registerNewObjective("board_1", "dummy");
        deactive = this.scoreboard.registerNewObjective("board_2", "dummy");
        this.player = player;
        this.player.setScoreboard(this.scoreboard);
    }

    public PlayerBoards(Scoreboard scoreboard, Player player) {
        this.scoreboard = scoreboard;
        createActive();
        createDeactive();
        this.player = player;
        this.player.setScoreboard(this.scoreboard);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getActive() {
        return active;
    }

    public Objective getDeactive() {
        return deactive;
    }

    public Player getPlayer() {
        return player;
    }

    public void setActive(Objective active) {
        this.active = active;
    }

    public void setDeactive(Objective deactive) {
        this.deactive = deactive;
    }

    public void createActive() {
        if (scoreboard.getObjective("board_1") == null) {
            active = this.scoreboard.registerNewObjective("board_1", "dummy");
        }
    }

    public void createDeactive() {
        if (scoreboard.getObjective("board_2") == null) {
            deactive = this.scoreboard.registerNewObjective("board_2", "dummy");
        }
    }

    public void createScoreboard() {
        scoreboard = AdvancedStats.getSingleton().getServer().getScoreboardManager().getNewScoreboard();
        createActive();
        createDeactive();
    }

    public Objective resetActiveObjective() {
        String name = active.getName();
        active.unregister();
        active = this.scoreboard.registerNewObjective(name, "dummy");
        return active;
    }

    public Objective resetDeactiveObjective() {
        String name = deactive.getName();
        deactive.unregister();
        deactive = this.scoreboard.registerNewObjective(name, "dummy");
        return deactive;
    }
}
