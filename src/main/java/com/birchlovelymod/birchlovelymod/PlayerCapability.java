package com.birchlovelymod.birchlovelymod;


import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public  class PlayerCapability implements CapabilityInterface {
    protected static ManaData manaData = new ManaData();
    protected static ModTickmanager tickmanager = new ModTickmanager();
    private static final String NBT_KEY_DAMAGE_DEALT = "damageDealt";
    public final Player player ;
    public final Level level ;
    private  String Value = "";


    public PlayerCapability(Player eventObject ) {
        player=eventObject;
        level = eventObject.getLevel();
    }

    public PlayerCapability() {
        player = null;
        level = null;


    }

    @Override
    public ManaData getManaData() {
        return this.manaData;
    }

    @Override
    public ModTickmanager getTickData() {
        return this.tickmanager;
    }

    public static void tick(Player player) {
                 PlayerCapability.manaData.tick(player);
                 PlayerCapability.tickmanager.tick(player);
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putString(NBT_KEY_DAMAGE_DEALT, this.Value);
        return tag;
    }


    @Override
    public String getValue() {
        return this.Value;
    }

    @Override
    public void setValue(String Value) {
        this.Value = Value;
    }



    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.Value = nbt.getString(NBT_KEY_DAMAGE_DEALT);
    }


}

