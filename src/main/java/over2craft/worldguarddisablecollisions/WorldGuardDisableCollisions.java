package over2craft.worldguarddisablecollisions;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.BukkitWorldGuardPlatform;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.BooleanFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.session.SessionManager;
import com.sk89q.worldguard.session.handler.NotifyEntryFlag;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldGuardDisableCollisions extends JavaPlugin {

    public static BooleanFlag COLLISIONS_FLAG;

    @Override
    public void onLoad() {

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        BooleanFlag flag = new BooleanFlag("disable-collisions");
        registry.register(flag);
        COLLISIONS_FLAG = flag;


    }

    @Override
    public void onEnable() {
        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(Collisions.FACTORY, null);
        TeamHandler.loadTeam();
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
    }

    @Override
    public void onDisable() {
        TeamHandler.unregister();
    }
}
