package com.birchlovelymod.birchlovelymod;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ModPlayer extends Player {


    public ModPlayer(Level p_36114_, BlockPos p_36115_, float p_36116_, GameProfile p_36117_) {
        super(p_36114_, p_36115_, p_36116_, p_36117_);
    }

    public Vec3 getLookAngle() {
        return this.calculateViewVector(this.getXRot()+30, this.getYRot());
    }


    @Override
    protected void defineSynchedData() {

    }
    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return null;
    }
}
