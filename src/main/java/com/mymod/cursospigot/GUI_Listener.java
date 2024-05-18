package com.mymod.cursospigot;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GUI_Listener implements Listener {
    //Lista de los jugadores que poseen el menu (inventario) abierto
    public static List<UUID> playersOpenedMenu = new ArrayList<>();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        playersOpenedMenu.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    //Escuchara el evento cuando alguien se vaya
    public void onInventoryOpen(PlayerQuitEvent event) {
        playersOpenedMenu.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        //Si el jugador que se encuentra en la lista clickea el inventario
        if(!playersOpenedMenu.contains(event.getWhoClicked().getUniqueId())) {
            return;
        }

        event.setCancelled(true);
        ItemStack itemGet = event.getCurrentItem();

        if(itemGet == null || itemGet.getType() == Material.AIR) {
            return;
        }
        //Obtenemos la entidad del jugador que ha clicado un item diferente a nulo o al aire
        Player player = (Player) event.getWhoClicked();

        switch (itemGet.getType()) {
            case Material.IRON_INGOT -> {
                player.getInventory().addItem(new ItemStack(Material.IRON_INGOT));
                player.closeInventory();
            }
            case Material.GOLD_INGOT -> {
                player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT));
                player.closeInventory();
            }
            case Material.DIAMOND -> {
                player.getInventory().addItem(new ItemStack(Material.DIAMOND));
                player.closeInventory();
            }
        }
    }
}
