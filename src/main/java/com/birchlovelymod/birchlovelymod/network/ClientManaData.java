package com.birchlovelymod.birchlovelymod.network;

public class ClientManaData {

    private static int playerMana;
    private static int chunkMana;

    public static void set(int playerMana) {
        ClientManaData.playerMana = playerMana;

    }

    public static int getPlayerMana() {
        return playerMana;
    }

    public static int getChunkMana() {
        return chunkMana;
    }
}
