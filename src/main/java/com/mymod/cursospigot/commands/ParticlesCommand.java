package com.mymod.cursospigot.commands;

import com.mymod.cursospigot.CursoSpigot;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ParticlesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            World world = player.getWorld();

            //world.spawnParticle(Particle.FLAME,player.getLocation(),10,2,2,2);
            for(byte i=0;i<10;i++) {
                //Clonamos la posicion del jugador y la modificamos
                Location posPlayer = player.getLocation().clone().add(0,i,0);

                Bukkit.getScheduler().runTaskLater(CursoSpigot.getInstance(),() -> {
                    world.spawnParticle(Particle.FLAME,posPlayer,5);
                },i*10L); //Delay por ticks (20 ticks = 1s) Esto se ejecutara cada medio segundo
            }
        }

        return true;
    }
}
