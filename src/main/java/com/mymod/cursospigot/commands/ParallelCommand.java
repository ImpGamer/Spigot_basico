package com.mymod.cursospigot.commands;

import com.mymod.cursospigot.CursoSpigot;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/*Una tarea paralela o Scheduler dentro de Bukkit, es una accion concurrente, es decir una tarea que se
* ejecutara o usara otro hilo del servidor para ejecutar una tarea en segundo plano, por asi decirlo*/
public class ParallelCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        long ticksTotales = 10*20; //10 segundos
        for(int i=10;i>0;i--) {
            int numero = i;

            //El bucle al ejecutarse en un instante, le daremos el delay automaticamente en el segundo parametro
            Bukkit.getScheduler().runTaskLater(CursoSpigot.getInstance(),() -> {
                Bukkit.broadcast(Component.text("Cuenta atras: "+numero));
            }, ticksTotales - (i*20L));
        }

        return true;
    }
}
