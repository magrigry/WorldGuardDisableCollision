# WorldGuardDisableCollision
This is a simple spigot/bukkit plugin that disable players collision by region using WorldGuard

## How does it works
A team nammed `no-collision` (on which collision rules are set to never) is created when the plugin is loaded. Players are added to the team when they enter on a region on which a the flag `disable-collision` set to true. They are removed from the team if they leave this region and join a region on which collision are not disabled.
Team is unregistered when the plugin is disabled. Player are removed from the team when they disconnect. Players are added to the team when they reconnect inside a region on which collision are disabled. 

## Download 
