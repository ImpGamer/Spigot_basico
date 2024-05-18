package com.mymod.cursospigot.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ScoreCommand implements CommandExecutor {
    private HashMap<UUID,Integer> counter = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if(counter.containsKey(player.getUniqueId())) {
                int newCounter = counter.get(player.getUniqueId())+1;
                counter.put(player.getUniqueId(),newCounter);

                player.getScoreboard().getTeam("Aqua").suffix(Component.text(newCounter));
            } else {
                //Creacion del scoreboard
                Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                Objective objective = scoreboard.registerNewObjective("board", Criteria.DUMMY, Component.text("Titulo"));
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                //Creacion de un team y su nombre (el nombre de los team, deben ser unicos)
                Team aqua_team = scoreboard.registerNewTeam("Aqua");
                aqua_team.addEntry("count");
                aqua_team.prefix(Component.text("Contador").color(NamedTextColor.AQUA));
                aqua_team.suffix(Component.text("0"));

                objective.getScore("count").setScore(3);
                objective.getScore("count").customName(Component.text(""));
                objective.getScore(" ").setScore(2);
                objective.getScore("Web: prueba.com").setScore(1);
                counter.put(player.getUniqueId(),0);
                player.setScoreboard(scoreboard);
            }

        }

        return true;
    }
}
