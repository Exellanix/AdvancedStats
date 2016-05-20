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
    private String prefix;
    private String suffix;
    private MessageType type;
    private boolean isVisible;
    private boolean cycle;
    private boolean pauseCycle;
    private int messageNum;
    private int cyclePosition;
    private int messageWidth;
    private int cyclePad;

    public BoardMessage(Scoreboard scoreboard, int messageNum, int messageWidth, MessageType type, String teamName, String entryName) {
        team = scoreboard.registerNewTeam(teamName);
        team.addEntry(entryName);
        this.type = type;
        this.messageNum = messageNum;
        this.messageWidth = messageWidth;
        isVisible = false;
        cycle = false;
        pauseCycle = false;
        prefix = "";
        suffix = "";
        cyclePosition = 0;
        cyclePad = 0;
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
        return prefix;
    }

    public void setMessage1(String message) {

        String paddedMessage;
        int lengthNoChars = StringUtils.getLengthNoCharCodes(message);
        if (message.length() > 32 || StringUtils.getLengthNoCharCodes(message) > messageWidth) {
            paddedMessage = message + "    ";
            cycle = true;
            messageWidth = StringUtils.calaculateMinWidth(paddedMessage, messageWidth);
            cyclePad = StringUtils.cyclePad(message);
        } else {
            paddedMessage = message;
        }
        if (!this.prefix.equals(paddedMessage)) {

            if (type == MessageType.CYCLE_TOGETHER) {
                this.prefix = paddedMessage;
                if (cycle) {
                    String[] array = StringUtils.splitCycleWrap(0, cycle ? messageWidth - 2 : messageWidth, paddedMessage);
                    team.setPrefix(array[0]);
                    team.setSuffix(array[1]);
                } else {
                    String[] array = StringUtils.splitCycleNoWrap(0, messageWidth, paddedMessage);
                    team.setPrefix(array[0]);
                    team.setSuffix(array[1]);
                }
            }
        }
    }

    public void setMessage2(String message) {
        if (!team.getSuffix().equals(message)) {
            this.prefix = message;
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
        return cycle && !pauseCycle;
    }

    public void cycleMessage() {
        if (canCycle()) {
            if (type == MessageType.CYCLE_TOGETHER) {
                if (cyclePosition == 0) {
                    String[] args = StringUtils.splitCycleWrap(cyclePosition, cycle ? messageWidth - 3 : messageWidth, prefix);
                    team.setPrefix(args[0]);
                    team.setSuffix(args[1]);
                    pauseCycle = true;
                    AdvancedStats.getSingleton().getServer().getScheduler().runTaskLater(AdvancedStats.getSingleton(), () -> {
                        pauseCycle = false;
                    }, 40);
                } else {
                    String[] args = StringUtils.splitCycleWrap(cyclePosition, cycle ? messageWidth - 3 : messageWidth, prefix);
                    team.setPrefix(args[0]);
                    team.setSuffix(args[1]);
                }
                cyclePosition++;
                cyclePosition = (cyclePosition % StringUtils.getLengthNoCharCodes(prefix));
            } else {
                /*String updatedMessage = "";
                for (int i = 0; i < 16; i++) {
                    updatedMessage += prefix.charAt((cyclePosition + i) % prefix.length());
                }
                team.setPrefix(updatedMessage);
                cyclePosition++;*/
            }
        }
    }
}
