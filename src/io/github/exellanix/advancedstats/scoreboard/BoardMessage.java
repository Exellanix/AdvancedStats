package io.github.exellanix.advancedstats.scoreboard;

import io.github.exellanix.advancedstats.AdvancedStats;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Mac on 5/17/2016.
 */
public class BoardMessage {
    private Scoreboard scoreboard;
    private Team team;
    private String message;
    private boolean isVisible;
    private boolean cycle;
    private int messageNum;
    private int cyclePosition;

    public BoardMessage(Scoreboard scoreboard, int messageNum, String teamName, String entryName) {
        team = scoreboard.registerNewTeam(teamName);
        team.addEntry(entryName);
        this.messageNum = messageNum;
        isVisible = false;
        cycle = false;
        message = "";
        cyclePosition = 0;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage1(String message) {
        if (!team.getPrefix().equals(message)) {
            AdvancedStats.getSingleton().getLogger().info("Original: \"" + team.getPrefix() + "\" New: \"" + message + "\"");
            this.message = message;
            if (message.length() < 17) {
                cycle = false;
                cyclePosition = 0;
                team.setPrefix(message);
            } else {
                //AdvancedStats.getSingleton().getLogger().info(message + " Length: " + message.length());
                this.message = message + "     ";
                cycle = true;
            }
        }
    }

    public void setMessage2(String message) {
        if (!team.getSuffix().equals(message)) {
            AdvancedStats.getSingleton().getLogger().info("Original: \"" + team.getSuffix() + "\" New: \"" + message + "\"");
            this.message = message;
            if (message.length() < 17) {
                team.setSuffix(message);
            }
        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public int getCyclePosition() {
        return cyclePosition;
    }

    public void setCyclePosition(int cyclePosition) {
        this.cyclePosition = cyclePosition;
    }

    public boolean canCycle() {
        return cycle;
    }

    public void cycleMessage() {
        if (canCycle()) {
            String updatedMessage = "";
            for (int i = 0; i < 16; i++) {
                updatedMessage += message.charAt((cyclePosition + i) % message.length());
            }
            team.setPrefix(updatedMessage);
            cyclePosition++;
        }
    }
}
