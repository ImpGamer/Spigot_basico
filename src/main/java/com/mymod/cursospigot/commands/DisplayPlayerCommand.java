package com.mymod.cursospigot.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DisplayPlayerCommand implements CommandExecutor {
    private BossBar bossBar;
    public DisplayPlayerCommand() {
        this.bossBar = Bukkit.createBossBar("Jefe Final!", BarColor.PURPLE, BarStyle.SEGMENTED_12);
        bossBar.setVisible(true);
        bossBar.setProgress(0.5);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Mostrar titulos
        sender.showTitle(Title.title(
                Component.text("Titulo chido") //Componente titulo (texto mas grande)
                        .color(NamedTextColor.AQUA)
                        .decorate(TextDecoration.ITALIC),
                Component.text("Con un subtitulo") //Componente subtitulo
                        .color(NamedTextColor.LIGHT_PURPLE),
                //Ahora de manera opcional podemos poner el tiempo de entrada y salida de ambos textos
                Title.Times.times(
                        Duration.ofSeconds(1), //Un segundo en entrar
                        Duration.ofSeconds(4), //4 segundos se queda en pantalla
                        Duration.ofSeconds(1) //En un 1 segundo desaparece
                )
        ));
        //Mostrar actionBar (texto que aparece encima de la hotbar)
        sender.sendActionBar(Component.text("Texto en el ActionBar").decorate(TextDecoration.BOLD));

        if(sender instanceof Player player) {
            bossBar.addPlayer(player); //Agregarle la bossbar al jugador
            tabListSet(sender);
        }
        return true;
    }
    //La TabList es aquella lista que de jugadores que se muestra al presionar "tab" en un servidor
    private void tabListSet(CommandSender sender) {
        sender.sendPlayerListHeaderAndFooter(
                Component.text("Bienvenidos al servidor!")
                        .color(NamedTextColor.RED)
                        .decorate(TextDecoration.BOLD),
                Component.text("Este es el footer de la tabList")
                        .color(NamedTextColor.DARK_PURPLE)
                        .clickEvent(ClickEvent.openUrl("https://ed.team"))
        );
    }


}
