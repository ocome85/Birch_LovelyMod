package com.birchlovelymod.birchlovelymod;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;

public class PlayerMouseClick {
        public static void tick(Player player , PlayerInteractEvent event) {
            Item pl=player.getMainHandItem().getItem();
            Component component = new TranslatableComponent(pl.toString()).withStyle(ChatFormatting.AQUA);
            player.sendMessage(component, Util.NIL_UUID);
            component = new TranslatableComponent(event.toString()).withStyle(ChatFormatting.BOLD);
            player.sendMessage(component, Util.NIL_UUID);

        }


}

