package com.mymod.cursospigot.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HologramCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        Component[] textos = {
                Component.text("Soy un holograma. Linea 1 :D"),
                Component.text("Linea 2 :]"),
                Component.text("Linea 3 :|")
        };

        Location playerLoc = player.getLocation().clone();
        for(int i=textos.length-1;i>=0;i--) {
            ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(playerLoc,EntityType.ARMOR_STAND);

            armorStand.setVisible(false);
            armorStand.setInvulnerable(true);
            armorStand.setGravity(false);

            //Que el nombre del armor sea visible (true)
            armorStand.setCustomNameVisible(true);
            armorStand.customName(textos[i]);
            playerLoc.add(0,0.5,0);
        }

        return true;
    }
}
