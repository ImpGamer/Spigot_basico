package com.mymod.cursospigot;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class PlayerListener implements Listener {

    //Anotacion para escuchar un evento
    /*@EventHandler
    //Parametro que nos dice que tipo de evento escuchara, en este caso cuando el jugador
    //Se mueva
    public void onPlayerMove(PlayerMoveEvent event) {
        *//*Manera de cancelar un evento, en este caso estamos cancelando el evento
         * de mover un jugador, quiere decir que no permitiremos que los jugadores
         * se muevan*//*
        event.setCancelled(true);

        //Obtener un jugador mediante el evento
        Player player = event.getPlayer();
        //mandarle un mensaje personalizado a dicho jugador
        player.sendMessage(
                Component.text("NO TE PUEDES MOVER")
                        .color(NamedTextColor.RED)
                        .decorate(TextDecoration.BOLD)
        );
    }*/


    //Evento que captura cuando el jugador se agache
    @EventHandler
    public void onPlayerSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        //Obtendremos la posicion actual del jugador e instanciaremos un zombie
        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(),
                EntityType.ZOMBIE);
        //Y ha este zombie le podemos cambiar propiedades, como que sea un bebe,
        //Si no se instanciara como uno normal
        zombie.setBaby();
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        /*Como primer parametro al crear un objeto Location (coordenadas) tenemos
        * que especificarle dos cosas.
        * 1. El mundo donde se tomaran esas coordenadas, pueden ser donde se encuentre
        * el jugador o uno en especifico
        * 2. Coordenadas x,y,z de ese mundo*/
        //Por ejemplo aqui tomamos el mundo donde se encuentra el jugador las coordenadas 0,0,0
        Location location = new Location(player.getWorld(),0,0,0);

        //Aqui por cada vez que se mueve el jugador le diremos que tan lejos se encuentra
        //De x:0,y:0,z:0
        player.sendMessage(Component.text("Distancia de 0 0 0 -> "+
                location.distance(player.getLocation())));

        //Bloques de un mundo
        /*Un bloque siempre estara presente en alguna coordenada ya sea aire,arenas,etc...*/

        //Obtenemos el bloque de la ubicacion especifica, en este caso 0,0,0
        Block block = location.getBlock();

        //Obtenemos la posicion del jugador, pero modificamos para que localizemos la posicion
        //del jugador con el vector y -1 (bajo del jugador) obtenemos el bloque y lo
        //cambiaremos por uno de diamante
        player.getLocation().add(0,-1,0).getBlock().setType(Material.DIAMOND_BLOCK);
    }
    @EventHandler
    public void disparar(PlayerInteractEvent event) {
        Player player= event.getPlayer();
        //Si el jugador posee un item y es de tipo (azada de diamante) lanzara una flecha
        if(event.hasItem() && event.getItem().getType() == Material.DIAMOND_HOE) {
            Arrow arrow = player.launchProjectile(Arrow.class);

            arrow.setDamage(40);
        }
    }
}