package io.github.exellanix.advancedstats.scoreboard;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Mac on 5/16/2016.
 */
public class BoardManager {
    private HashMap<Player, PlayerBoards> boardMap;

    public BoardManager() {
        boardMap = new HashMap<>();
    }

    public PlayerBoards createBoard(Player player) {
        return boardMap.put(player, new PlayerBoards(player));
    }

    public PlayerBoards deleteBoard(Player player) {
        return boardMap.remove(player);
    }

    public PlayerBoards getBoard(Player player) {
        return boardMap.get(player);
    }
}
