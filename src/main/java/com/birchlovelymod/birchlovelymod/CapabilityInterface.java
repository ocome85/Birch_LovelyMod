package com.birchlovelymod.birchlovelymod;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;


public interface CapabilityInterface extends INBTSerializable<CompoundTag> {


    ManaData getManaData();
    ModTickmanager getTickData();

    String getValue();

    void setValue(String Value);
}
