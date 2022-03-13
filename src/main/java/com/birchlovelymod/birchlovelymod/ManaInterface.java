package com.birchlovelymod.birchlovelymod;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ManaInterface extends INBTSerializable<CompoundTag> {
    String getValue();
    int getManaLevel();

}
