package over2craft.worldguarddisablecollisions;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Collisions extends FlagValueChangeHandler<Boolean> {

    public static final Collisions.Factory FACTORY = new Collisions.Factory();
    public static class Factory extends Handler.Factory<Collisions> {
        @Override
        public Collisions create(Session session) {
            return new Collisions(session);
        }
    }

    public Collisions(Session session) {
        super(session, WorldGuardDisableCollisions.COLLISIONS_FLAG);
    }

    @Override
    protected void onInitialValue(LocalPlayer player, ApplicableRegionSet set, Boolean value) {
    }

    @Override
    protected boolean onSetValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Boolean currentValue, Boolean lastValue, MoveType moveType) {

        Player playerBukkit = Objects.requireNonNull(Bukkit.getPlayer(player.getUniqueId()));
        if (currentValue) {
            TeamHandler.addPlayer(playerBukkit);
        } else if (TeamHandler.containsPlayer(playerBukkit)) {
            TeamHandler.removePlayer(playerBukkit);
        }
        return true;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer player, Location from, Location to, ApplicableRegionSet toSet, Boolean lastValue, MoveType moveType) {

        Player playerBukkit = Objects.requireNonNull(Bukkit.getPlayer(player.getUniqueId()));
        if (TeamHandler.containsPlayer(playerBukkit)) {
            TeamHandler.removePlayer(playerBukkit);
        }
        return true;
    }

}