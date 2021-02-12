package over2craft.worldguarddisablecollisions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamHandler {

    private static Team team;

    private static final String team_name = "no_collision";

    public static void loadTeam() {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard board = sm.getMainScoreboard();
        team = board.registerNewTeam(team_name);
        team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
    }

    public static void unregister() {
        team.unregister();
    }

    public static void addPlayer(Player p) {
        team.addEntry(p.getName());
    }

    public static void removePlayer(Player p) {
        team.removeEntry(p.getName());
    }

    public static boolean containsPlayer(Player p) {
        return team.getEntries().contains(p.getName());
    }



}