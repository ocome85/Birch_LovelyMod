package com.birchlovelymod.birchlovelymod;

import net.minecraft.world.item.ItemStack;

public class ClientManaData {

    private static int player;
    private static ItemStack itemStack;

    public static void set(int playerMana, ItemStack itemStack) {
        ClientManaData.player = playerMana;
        ClientManaData.itemStack = itemStack;
    }

    public static int getPlayerMana() {
        return player;
    }

    public static ItemStack getChunkMana() {
        return itemStack;
    }
}
