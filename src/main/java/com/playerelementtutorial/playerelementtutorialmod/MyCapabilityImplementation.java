package com.playerelementtutorial.playerelementtutorialmod;


import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;

public  class MyCapabilityImplementation implements MyCapabilityInterface {
    protected   ManaData manaData = new ManaData();
    private static final String NBT_KEY_DAMAGE_DEALT = "damageDealt";
    Minecraft minecraft = Minecraft.getInstance();
    public final Player player ;
    public final Level level ;
    private  String Value = "";


    public MyCapabilityImplementation(Player eventObject) {
        player=eventObject;
        level = eventObject.getLevel();

    }

    public MyCapabilityImplementation() {
        player = null;
        level = null;


    }

    @Override
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

