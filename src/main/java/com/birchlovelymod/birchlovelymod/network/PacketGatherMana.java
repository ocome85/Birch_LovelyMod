package com.birchlovelymod.birchlovelymod.network;

import com.birchlovelymod.birchlovelymod.ModItems;
import com.birchlovelymod.birchlovelymod.PlayerAbilityManager;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import static com.birchlovelymod.birchlovelymod.PlayerAbilityManager.sa;

public class PacketGatherMana {

    public static final String MESSAGE_NO_MANA = "message.nomana";
    public  static Player player ;
    public PacketGatherMana() {

    }

    public PacketGatherMana(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are server side
            ServerPlayer player = ctx.getSender();
                Item playermainhanditem = player.getMainHandItem().getItem();
                Item items = ModItems.BIRCH_GUN.get();
                if (playermainhanditem == items) {
                    ItemStack itemstack = player.getMainHandItem();
                    player.level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (player.level.getRandom().nextFloat() * 0.4F + 0.8F));
                    player.getCooldowns().addCooldown(ModItems.BIRCH_GUN.get(), 20);
                    sa(player, itemstack,player);
                }
        });
        return true;
    }
}
