package io.github.exellanix.advancedstats;

import io.github.exellanix.advancedstats.commands.Stats;
import io.github.exellanix.advancedstats.events.PlayerJoin;
import io.github.exellanix.advancedstats.scoreboard.BoardManager;
import io.github.exellanix.advancedstats.scoreboard.BoardUtils;
import io.github.exellanix.advancedstats.tasks.ScoreboardUpdate;
import me.exellanix.kitpvp.KitPvPAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Exellanix on 5/15/2016.
 */
public class AdvancedStats extends JavaPlugin {

    private static AdvancedStats instance;

    private Economy econ = null;

    private KitPvPAPI kit = null;

    private BoardManager berdManger;

    private boolean setupKitPvPAPI() {
        RegisteredServiceProvider<KitPvPAPI> kitpvpapi = getServer().getServicesManager().getRegistration(KitPvPAPI.class);
        if (kitpvpapi != null) {
            kit = kitpvpapi.getProvider();
        }

        return (kit != null);
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider;
        economyProvider = getServer().getServicesManager()
                .getRegistration(Economy.class);
        if (economyProvider != null) {
            econ = economyProvider.getProvider();
        }

        return (econ != null);
    }

    public void onEnable() {

        if (!setupEconomy()) {
            getLogger().warning("Could not setup the economy!");
            getLogger().warning("Make sure you have an economy plugin installed!");
            getServer().getPluginManager().disablePlugin(this);

        } else if(!setupKitPvPAPI()) {
            getLogger().warning("Could not find KitPvP!");
            getLogger().warning("Install KitPvP");
            getServer().getPluginManager().disablePlugin(this);

        } else {
            PluginDescriptionFile pdfFile = getDescription();
            Logger logger = Logger.getLogger("Minecraft");

            registerEvents();
            registerCommands();
            instance = this;
            berdManger = new BoardManager();
            getServer().getScheduler().runTaskTimer(this, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    BoardUtils.updateScoreboard(berdManger.getBoard(p));
                }
            }, 0, 10);
            getServer().getScheduler().runTaskTimer(this, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    BoardUtils.swapObjectives(berdManger.getBoard(p));
                }
            }, 5, 10);
            logger.info(pdfFile.getName() + " has been enabled! (V." + pdfFile.getVersion());
        }
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = Logger.getLogger("Minecraft");

        logger.info(pdfFile.getName() + " has been disabled! (V." + pdfFile.getVersion());

    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerJoin(), this);
    }

    public void registerCommands() {

        getCommand("stats").setExecutor(new Stats());
    }

    public static AdvancedStats getSingleton() {
        return instance;
    }

    public Economy getEcon() {
        return econ;
    }

    public KitPvPAPI getKit() {
        return kit;
    }

    public BoardManager getBoardManager() {
        return berdManger;
    }
}
