package over2craft.worldguarddisablecollisions;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(e.getPlayer());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(localPlayer.getLocation());
        Boolean value = set.queryValue(localPlayer, WorldGuardDisableCollisions.COLLISIONS_FLAG);
        if (value != null && value) {
            TeamHandler.addPlayer(e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (TeamHandler.containsPlayer(e.getPlayer())) {
            TeamHandler.removePlayer(e.getPlayer());
        }
    }

}
