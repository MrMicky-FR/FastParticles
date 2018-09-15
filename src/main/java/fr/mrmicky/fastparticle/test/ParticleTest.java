package fr.mrmicky.fastparticle.test;

import fr.mrmicky.fastparticle.FastParticle;
import fr.mrmicky.fastparticle.ParticleEnum;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParticleTest extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Usage: /fastparticle <player|world> <particle>");
                return true;
            }

            Player player = (Player) sender;

            ParticleEnum particle;
            Object data;

            /*try {
                particle = ParticleEnum.valueOf(args[1].toUpperCase());
            } catch (IllegalArgumentException e) {
                sender.sendMessage(ChatColor.RED + "Invalid particle");
                return true;
            }*/

            if (args[1].equalsIgnoreCase("test1")) {
                particle = ParticleEnum.BLOCK_CRACK;
                data = new MaterialData(Material.TNT, (byte) 0);
            } else if (args[1].equalsIgnoreCase("test2")) {
                particle = ParticleEnum.ITEM_CRACK;
                data = new ItemStack(Material.TNT);
            } else if (args[1].equalsIgnoreCase("test3")) {
                particle = ParticleEnum.REDSTONE;
                data = Color.BLACK;
            } else {
                sender.sendMessage("Unknow");
                return true;
            }

            if (args[0].equalsIgnoreCase("player")) {
                FastParticle.spawnParticle(player, particle, player.getLocation().add(1, 2, 1), 3, data);
            } else if (args[0].equalsIgnoreCase("world")) {
                FastParticle.spawnParticle(player.getWorld(), particle, player.getLocation().add(1, 2, 1), 3, data);
            } else {
                sender.sendMessage(ChatColor.RED + "Unknow argument");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("player", "world"), new ArrayList<>());
        } else if (args.length == 2) {
            //List<String> completions = Stream.of(ParticleEnum.values()).map(ParticleEnum::toString).map(String::toLowerCase).collect(Collectors.toList());
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("test1", "test2", "test3"), new ArrayList<>());
        }
        return Collections.emptyList();
    }
}
