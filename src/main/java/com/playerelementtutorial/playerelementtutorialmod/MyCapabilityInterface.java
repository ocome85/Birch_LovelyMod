package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.util.INBTSerializable;


public interface MyCapabilityInterface extends INBTSerializable<CompoundTag> {


    ManaData getManaData();
    void aiStep();
    void tick();

    String getValue();

    void setValue(String Value);
}
