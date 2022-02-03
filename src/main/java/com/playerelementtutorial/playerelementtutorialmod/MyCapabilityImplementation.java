package com.playerelementtutorial.playerelementtutorialmod;


import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

public  class MyCapabilityImplementation implements MyCapabilityInterface {
    protected   ManaData manaData = new ManaData();
    private static final String NBT_KEY_DAMAGE_DEALT = "damageDealt";
    public Player player ;
    public Level level;
    private String Value = "";
    public Minecraft minecraft;

    public MyCapabilityImplementation() {
    }

    public MyCapabilityImplementation(Player object) {
        player=object;
    }
    @Override
    public ManaData getManaData() {
        return this.manaData;
    }

    @Override
    public void tick() {
        if (!this.level.isClientSide) {
            this.manaData.tick(player);
        }
    }
    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putString(NBT_KEY_DAMAGE_DEALT, this.Value);
        return tag;
    }

@Override
    public void aiStep() {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && this.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION)) {
           // if (this.manaData.needsMana() && player.tickCount % 10 == 0) {
                this.manaData.setManaLevel(this.manaData.getManaLevel() + 1);
           // }
        }
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
