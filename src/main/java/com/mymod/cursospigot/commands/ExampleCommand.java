package com.mymod.cursospigot.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ExampleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("cursosspigot.commands.ejemplo")) {
            sender.sendMessage(Component.text("No posees permisos"));
            return true;
        }

        //Inspeccionamos si hay argumentos dentro del comando
        if(args.length > 0) {
            //Accedemos al primer argumento
            String name = args[0];
            sender.sendMessage(Component.text("Buenas! "+name));
        } else {
            //Validamos si el que ejecuto el comando es un jugador
            if(sender instanceof Player player) {
                sender.sendMessage(Component.text("Buenas! ").append(player.displayName()));
                //En el caso que se ejecute en un bloque de comando o terminal
            } else {
                sender.sendMessage(Component.text("Buenas desconocido"));
            }
        }
        return false;
    }
}
