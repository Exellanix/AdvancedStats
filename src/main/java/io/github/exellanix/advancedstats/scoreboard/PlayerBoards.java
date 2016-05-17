package io.github.exellanix.advancedstats.scoreboard;

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;

/**
 * Created by Mac on 5/16/2016.
 */
public class PlayerBoards {
    private Scoreboard scoreboard;
    private Objective active;
    private HashMap<Integer, BoardMessage> messages;
    private Player player;

    public PlayerBoards(Player player) {
        messages = new HashMap<>();
        this.scoreboard = AdvancedStats.getSingleton().getServer().getScoreboardManager().getNewScoreboard();
        active = this.scoreboard.registerNewObjective("board_1", "dummy");
        setupScoreboard();
        this.player = player;
        this.player.setScoreboard(this.scoreboard);
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getActive() {
        return active;
    }

    public Player getPlayer() {
        return player;
    }

    public BoardMessage getBoardMessage(int value) {
        return messages.get(value);
    }

    public void setActive(Objective active) {
        this.active = active;
    }

    public void createActive() {
        if (scoreboard.getObjective("board_1") == null) {
            active = this.scoreboard.registerNewObjective("board_1", "dummy");
        }
    }

    public void createScoreboard() {
        scoreboard = AdvancedStats.getSingleton().getServer().getScoreboardManager().getNewScoreboard();
        createActive();
    }

    public Objective resetActiveObjective() {
        String name = active.getName();
        active.unregister();
        active = this.scoreboard.registerNewObjective(name, "dummy");
        return active;
    }

    private void setupScoreboard() {
        messages.put(0, new BoardMessage(scoreboard, 0, "board_team_0", ChatColor.BLACK + "" + ChatColor.WHITE));
        messages.put(1, new BoardMessage(scoreboard, 1, "board_team_1", ChatColor.DARK_BLUE + "" + ChatColor.WHITE));
        messages.put(2, new BoardMessage(scoreboard, 2, "board_team_2", ChatColor.DARK_GREEN + "" + ChatColor.WHITE));
        messages.put(3, new BoardMessage(scoreboard, 3, "board_team_3", ChatColor.DARK_AQUA + "" + ChatColor.WHITE));
        messages.put(4, new BoardMessage(scoreboard, 4, "board_team_4", ChatColor.DARK_RED + "" + ChatColor.WHITE));
        messages.put(5, new BoardMessage(scoreboard, 5, "board_team_5", ChatColor.DARK_PURPLE + "" + ChatColor.WHITE));
        messages.put(6, new BoardMessage(scoreboard, 6, "board_team_6", ChatColor.GOLD + "" + ChatColor.WHITE));
        messages.put(7, new BoardMessage(scoreboard, 7, "board_team_7", ChatColor.GRAY + "" + ChatColor.WHITE));
        messages.put(8, new BoardMessage(scoreboard, 8, "board_team_8", ChatColor.DARK_GRAY + "" + ChatColor.WHITE));
        messages.put(9, new BoardMessage(scoreboard, 9, "board_team_9", ChatColor.BLUE + "" + ChatColor.WHITE));
        messages.put(10, new BoardMessage(scoreboard, 10, "board_team_10", ChatColor.GREEN + "" + ChatColor.WHITE));
        messages.put(11, new BoardMessage(scoreboard, 11, "board_team_11", ChatColor.AQUA + "" + ChatColor.WHITE));
        messages.put(12, new BoardMessage(scoreboard, 12, "board_team_12", ChatColor.RED + "" + ChatColor.WHITE));
        messages.put(13, new BoardMessage(scoreboard, 13, "board_team_13", ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE));
        messages.put(14, new BoardMessage(scoreboard, 14, "board_team_14", ChatColor.YELLOW + "" + ChatColor.WHITE));
        messages.put(15, new BoardMessage(scoreboard, 15, "board_team_15", ChatColor.WHITE + "" + ChatColor.WHITE));
    }

    public void showMessage(int value) {
        if (!isMessageVisible(value)) {
            switch (value) {
                case 0:
                    active.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(0);
                    break;
                case 1:
                    active.getScore(ChatColor.DARK_BLUE + "" + ChatColor.WHITE).setScore(1);
                    break;
                case 2:
                    active.getScore(ChatColor.DARK_GREEN + "" + ChatColor.WHITE).setScore(2);
                    break;
                case 3:
                    active.getScore(ChatColor.DARK_AQUA + "" + ChatColor.WHITE).setScore(3);
                    break;
                case 4:
                    active.getScore(ChatColor.DARK_RED + "" + ChatColor.WHITE).setScore(4);
                    break;
                case 5:
                    active.getScore(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE).setScore(5);
                    break;
                case 6:
                    active.getScore(ChatColor.GOLD + "" + ChatColor.WHITE).setScore(6);
                    break;
                case 7:
                    active.getScore(ChatColor.GRAY + "" + ChatColor.WHITE).setScore(7);
                    break;
                case 8:
                    active.getScore(ChatColor.DARK_GRAY + "" + ChatColor.WHITE).setScore(8);
                    break;
                case 9:
                    active.getScore(ChatColor.BLUE + "" + ChatColor.WHITE).setScore(9);
                    break;
                case 10:
                    active.getScore(ChatColor.GREEN + "" + ChatColor.WHITE).setScore(10);
                    break;
                case 11:
                    active.getScore(ChatColor.AQUA + "" + ChatColor.WHITE).setScore(11);
                    break;
                case 12:
                    active.getScore(ChatColor.RED + "" + ChatColor.WHITE).setScore(12);
                    break;
                case 13:
                    active.getScore(ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE).setScore(13);
                    break;
                case 14:
                    active.getScore(ChatColor.YELLOW + "" + ChatColor.WHITE).setScore(14);
                    break;
                case 15:
                    active.getScore(ChatColor.WHITE + "" + ChatColor.WHITE).setScore(15);
                    break;
            }
        }
    }

    public void hideMessage(int value) {
        if (isMessageVisible(value)) {
            switch (value) {
                case 0:
                    scoreboard.resetScores(ChatColor.BLACK + "" + ChatColor.WHITE);
                    break;
                case 1:
                    scoreboard.resetScores(ChatColor.DARK_BLUE + "" + ChatColor.WHITE);
                    break;
                case 2:
                    scoreboard.resetScores(ChatColor.DARK_GREEN + "" + ChatColor.WHITE);
                    break;
                case 3:
                    scoreboard.resetScores(ChatColor.DARK_AQUA + "" + ChatColor.WHITE);
                    break;
                case 4:
                    scoreboard.resetScores(ChatColor.DARK_RED + "" + ChatColor.WHITE);
                    break;
                case 5:
                    scoreboard.resetScores(ChatColor.DARK_PURPLE + "" + ChatColor.WHITE);
                    break;
                case 6:
                    scoreboard.resetScores(ChatColor.GOLD + "" + ChatColor.WHITE);
                    break;
                case 7:
                    scoreboard.resetScores(ChatColor.GRAY + "" + ChatColor.WHITE);
                    break;
                case 8:
                    scoreboard.resetScores(ChatColor.DARK_GRAY + "" + ChatColor.WHITE);
                    break;
                case 9:
                    scoreboard.resetScores(ChatColor.BLUE + "" + ChatColor.WHITE);
                    break;
                case 10:
                    scoreboard.resetScores(ChatColor.GREEN + "" + ChatColor.WHITE);
                    break;
                case 11:
                    scoreboard.resetScores(ChatColor.AQUA + "" + ChatColor.WHITE);
                    break;
                case 12:
                    scoreboard.resetScores(ChatColor.RED + "" + ChatColor.WHITE);
                    break;
                case 13:
                    scoreboard.resetScores(ChatColor.LIGHT_PURPLE + "" + ChatColor.WHITE);
                    break;
                case 14:
                    scoreboard.resetScores(ChatColor.YELLOW + "" + ChatColor.WHITE);
                    break;
                case 15:
                    scoreboard.resetScores(ChatColor.WHITE + "" + ChatColor.WHITE);
                    break;
            }
        }
    }

    public boolean isMessageVisible(int value) {
        return messages.get(value).isVisible();
    }

    public void setMessage(int value, String message) {
        messages.get(value).setMessage1(message);
    }

    public void cycleMessages() {
        for (BoardMessage m : messages.values()) {
            m.cycleMessage();
        }
    }
}
