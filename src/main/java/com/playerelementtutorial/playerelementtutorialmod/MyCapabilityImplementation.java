package com.playerelementtutorial.playerelementtutorialmod;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public  class MyCapabilityImplementation implements MyCapabilityInterface {
    protected   ManaData manaData = new ManaData();
    private static final String NBT_KEY_DAMAGE_DEALT = "damageDealt";

    public Level level;
    private String Value = "";

     public ManaData getManaData() {
        return this.manaData;
    }

    @Override
    public void tick(Player player) {
           this.manaData.tick(player);
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
