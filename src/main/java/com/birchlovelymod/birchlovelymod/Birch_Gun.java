package com.birchlovelymod.birchlovelymod;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class Birch_Gun  extends Item {


    public Birch_Gun(Properties properties) {
        super(properties);
    }
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return true;
    }


    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {

        Component component = new TranslatableComponent(p_41433_.toString()).withStyle(ChatFormatting.RED);
        p_41433_.sendMessage(component, Util.NIL_UUID);
        return InteractionResultHolder.pass(p_41433_.getItemInHand(p_41434_));
    }
}
