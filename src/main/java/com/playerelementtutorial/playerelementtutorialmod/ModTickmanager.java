package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Predicate;

import static com.playerelementtutorial.playerelementtutorialmod.MyCapabilityImplementation.manaData;

public class ModTickmanager {
    private int tickcounter;
    private boolean lightningbolt;
    private static final Predicate<Monster> PREDICATE_MOB = p_29453_ -> p_29453_ != null;

    public void tick() {
        ++this.tickcounter;
    }
    public void TickReset(){
        this.tickcounter=1;
    }
    public int getTickcount(){
       return this.tickcounter;
    }
}
