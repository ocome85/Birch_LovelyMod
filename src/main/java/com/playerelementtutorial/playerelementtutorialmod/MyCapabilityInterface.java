package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;


public interface MyCapabilityInterface extends INBTSerializable<CompoundTag> {


    MyCapabilityInterface tick(Player player);
    void aiStep();

    String getValue();
    public ManaData getManaData();
    void setValue(String Value);
}
