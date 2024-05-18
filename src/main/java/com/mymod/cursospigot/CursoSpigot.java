package com.mymod.cursospigot;

import com.mymod.cursospigot.commands.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CursoSpigot extends JavaPlugin {
    private static CursoSpigot instance;
    //Funcion que se ejecutara al arrancar el servidor o activar el plugin
    @Override
    public void onEnable() {
        instance = this;
        //Como parametro le pasamos el nombre de la carpeta donde se almacenara el mundo
        //La carpeta se creara dentro de la carpeta del servidor (run)
        WorldCreator worldCreator= new WorldCreator("curso");
        //Que no genere estructuras
        worldCreator.generateStructures(false);
        World world= Bukkit.createWorld(worldCreator);

        //Obtenemos la carpeta de (run) del overworld
        World overWorld = Bukkit.getWorld("world");

        //Set de configuraciones
        getConfig().options().copyDefaults();
        //Carga la configuracion que se encuentra en el archivo "config.yml"
        saveDefaultConfig();

        //Inicializar que ahora podremos escuchar eventos
        Bukkit.getPluginManager().registerEvents(new PlayerListener(),this);
        Bukkit.getPluginManager().registerEvents(new GUI_Listener(),this);
        /*Primero se coloca el nombre colocado al comando (del plugin.yml)
        * y posterior el .setExecutor y una instancia de la clase donde se encuentra
        * la ejecucion de dicho comando*/
        getCommand("ejemplo").setExecutor(new ExampleCommand());
        Objects.requireNonNull(getCommand("espada")).setExecutor(new SwordCommand());
        getCommand("head").setExecutor(new HeadsCommand());
        getCommand("displayTitles").setExecutor(new DisplayPlayerCommand());
        getCommand("scores").setExecutor(new ScoreCommand());
        getCommand("projectile").setExecutor(new ProjectileCommand());
        getCommand("parallel").setExecutor(new ParallelCommand());
        getCommand("particles").setExecutor(new ParticlesCommand());
        getCommand("Inventory").setExecutor(new InventoryCommand());
        getCommand("package").setExecutor(new PacketsCommand());
        getCommand("SetHologram").setExecutor(new HologramCommand());

        Component texto = Component
                .text("Hola a todos!")
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD)
                .clickEvent(ClickEvent.openUrl("https://ed.team"))
                .append(Component.text("Texto agregado"));
        getServer().sendMessage(texto);
        System.out.println("El plugin se ha cargado!");
    }
    //F
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static CursoSpigot getInstance() {
        return instance;
    }
}