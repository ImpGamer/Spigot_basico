package com.mymod.cursospigot.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class SwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Instanciar un item stackeable (aquellos items que pueden acumularse)
        ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
        //Para modificar las propiedades de un Item se usa
        ItemMeta properties = espada.getItemMeta(); //Se obtiene las propiedades de la espada
        properties.displayName(Component.text("Espada pro")
                .color(NamedTextColor.RED)
                .decorate(TextDecoration.BOLD));
        properties.addEnchant(Enchantment.SWEEPING_EDGE,4,true);

        //Una vez modificada sus propiedades (ItemMeta) debemos agregarselas al item
        espada.setItemMeta(properties);
        //Verificamos si el que realizo el comando es un jugador
        if(sender instanceof Player player) {
            player.getInventory().addItem(espada);
        }
        return true;
    }
}
