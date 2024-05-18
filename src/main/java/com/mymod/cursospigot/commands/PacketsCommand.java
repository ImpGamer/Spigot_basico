package com.mymod.cursospigot.commands;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.mymod.cursospigot.CursoSpigot;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/*Un paquete es la informacion que se manda del cliente al servidor y viceseversa, esto para comunicarse entre
* si, e indicarle el movimiento de todos los jugadores, y que los demas tambien puedan verlos*/
public class PacketsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player player)) return false;

        PacketContainer packetContainer = new PacketContainer(PacketType.Play.Server.ANIMATION);
        packetContainer.getIntegers().write(0,player.getEntityId());
        packetContainer.getIntegers().write(1,0);

        ProtocolLibrary.getProtocolManager().sendServerPacket(player,packetContainer);
        //Obtiene el valor "movement" del archivo de configuracion "config.yml"
        player.sendMessage(Component.text(CursoSpigot.getInstance().getConfig().getString("movement")));

        return true;
    }
}
