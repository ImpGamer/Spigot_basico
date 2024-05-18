package com.mymod.cursospigot.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class HeadsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //Textura contextualizada en base64
        String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZlZjFjMjVmNTE2ZjJlN2Q2Zjc2Njc0MjBlMzNhZGNmM2NkZjkzOGNiMzdmOWE0MWE4YjM1ODY5ZjU2OWIifX19";

        //El tipo de material debe ser la cabeza de un jugador obligatoriamente
        ItemStack cabeza = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta propertiesHead = (SkullMeta) cabeza.getItemMeta();
        //Se crea un jugador falso (si existe en el contexto de Minecraft) pero dentro del juego no (Perfil falso)
        //UUID para identificar al jugador, y nombre del jugador
        PlayerProfile dummyProfile = Bukkit.createProfile(UUID.randomUUID(),"Hamburguesa");
        dummyProfile.setProperty(new ProfileProperty("textures",texture));
        propertiesHead.setPlayerProfile(dummyProfile);

        cabeza.setItemMeta(propertiesHead);
        if(sender instanceof Player player) {
            player.getInventory().addItem(cabeza);
            ItemStack armadura = leatherChesplater();
            player.getInventory().addItem(armadura);
            ItemStack fuegoArtificial = fireWorkCustom();
            player.getInventory().addItem(fuegoArtificial);
            //Nos permite reproducir un sonido
            /*Hay dos maneras de reproducir un sonido, de manera global y por entidad
            * 1.Global: Este tomara un location (x,y,z) como argumento y en ese location se reproducira el sonido
            * 2.Entidad: Especificaremos a la entidad o entidades que solamente escucharan el sonido*/

            /*Argumentos que tomara la funcion .playSound()
            * 1. Localizacion o entidades donde se escuchara el sonido,
            * 2. El efecto de sonido (del juego o personalizada)
            * 3. Categoria del sonido. En Minecraft existen muchas y podemos revisarlas en la configuracion del propio juego
            * 4. Volumen del sonido (float), que en realidad es la cantidad de bloques en la que se escuchara (default: 15)
            * 5. Pitch del sonido, entre mas alto se escuchara mas rapido el sonido */
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,SoundCategory.AMBIENT,10f,1f);
            //Agregar efectos de pociones a entidades
            /*Parametros para pocion
            * 1. Efecto de pocion que se le desea aplicar
            * 2. La duracion de la pocion (se coloca en ticks del juego)
            * 3. Poder o nivel de la pocion (valor deseado -1)
            * 4. Ambiente: Particulas del ambiente relacionadas con el efecto
            * 5. Particles: Si deseamos que las particulas sean visibles o no
            * 6. Icon: Si deseamos que el icono del efecto sea visible para el jugador (esquina superior derecha)*/
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,9,true,true,true));
            //Remover un efecto de pocion especifico
            player.removePotionEffect(PotionEffectType.POISON);
            //Si deseamos remover todos
            for(PotionEffect potionEffect: player.getActivePotionEffects()) { //Obtenemos los efectos que tiene activo el jugador
                player.removePotionEffect(potionEffect.getType());
            }
        }

        return true;
    }
    private ItemStack leatherChesplater() {
        ItemStack armadura = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta leatherMeta = (LeatherArmorMeta) armadura.getItemMeta();

        leatherMeta.setColor(Color.fromRGB(255,0,0));
        armadura.setItemMeta(leatherMeta);
        return armadura;
    }
    private ItemStack fireWorkCustom() {
        ItemStack fuegoArtificial = new ItemStack(Material.FIREWORK_ROCKET);
        FireworkMeta metaFireWork = (FireworkMeta) fuegoArtificial.getItemMeta();

        //Modo para darle efectos al fuego artificial
        metaFireWork.addEffect(
                FireworkEffect.builder().trail(true)
                        .withColor(Color.GREEN)
                        .with(FireworkEffect.Type.CREEPER)
                        .build()
        );
        fuegoArtificial.setItemMeta(metaFireWork);
        return fuegoArtificial;
    }
}
