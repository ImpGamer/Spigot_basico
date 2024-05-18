package com.mymod.cursospigot.commands;

import com.mymod.cursospigot.GUI_Listener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import javax.inject.Named;
import java.util.List;

public class InventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            Inventory inventory = player.getInventory();
            ItemStack espada = new ItemStack(Material.DIAMOND_SWORD);
            espada.addEnchantment(Enchantment.DAMAGE_ALL,3);

            inventory.clear();
            inventory.addItem(espada);
            return customGUI(sender);
        }

        return true;
    }
    private boolean customGUI(CommandSender sender) {
        if(!(sender instanceof Player player)) return false;
        //Creacion del inventario
        /*
        * 1.Parametro: Dueno del inventario (en este caso no deseamos que sea alguien por eso null)
        * 2. La cantidad de slots que tendra este inventario (tendra que ser multiplo de 9) visitar MC Utils
        * 3.Parametro: El titulo del inventario*/
        Inventory inventory = Bukkit.createInventory(null, 54, Component.text("Menu"));

        ItemStack hierro = new ItemStack(Material.IRON_INGOT);
        ItemMeta hierroMeta = hierro.getItemMeta();

        //Creacion de los objetos con sus respectivos atributos
        hierroMeta.displayName(Component.text("Hierro :)").color(NamedTextColor.DARK_GRAY));
        hierroMeta.lore(
                List.of(
                        Component.text("Volver al menu").color(NamedTextColor.RED)
                )
        );
        hierro.setItemMeta(hierroMeta);

        ItemStack oro = new ItemStack(Material.GOLD_INGOT);
        ItemMeta oroMeta = hierro.getItemMeta();

        oroMeta.displayName(Component.text("Hierro :)").color(NamedTextColor.DARK_GRAY));
        oroMeta.lore(
                List.of(
                        Component.text("Volver al menu").color(NamedTextColor.RED)
                )
        );
        oro.setItemMeta(oroMeta);

        ItemStack diamante = new ItemStack(Material.DIAMOND);
        ItemMeta diamanteMeta = hierro.getItemMeta();

        diamanteMeta.displayName(Component.text("Hierro :)").color(NamedTextColor.DARK_GRAY));
        diamanteMeta.lore(
                List.of(
                        Component.text("Volver al menu").color(NamedTextColor.RED)
                )
        );
        diamante.setItemMeta(diamanteMeta);

        //Se agregan en los slots indicados los items que acabamos de crear
        inventory.setItem(11,hierro);
        inventory.setItem(13,oro);
        inventory.setItem(15,diamante);

        //Le abre el inventario al jugador
        player.openInventory(inventory);
        GUI_Listener.playersOpenedMenu.add(player.getUniqueId());
        return true;
    }
}
