package com.birchlovelymod.birchlovelymod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;


public class ManaData extends FoodData {
    private int manaLevel = 0;
    private int maxmanaLevel = 100;

    private float saturationLevel;
    private float exhaustionLevel;
    private int tickTimer;
    private int lastManaLevel = 0;

    public ManaData() {
        this.saturationLevel = 5.0F;
    }
@Override
    public void eat(int p_38708_, float p_38709_) {
        this.manaLevel = Math.min(p_38708_ + this.manaLevel, 100);
        this.saturationLevel = Math.min(this.saturationLevel + (float)p_38708_ * p_38709_ * 2.0F, (float)this.manaLevel);
    }

@Override
    public void readAdditionalSaveData(CompoundTag p_38716_) {
        if (p_38716_.contains("manaLevel", 99)) {
            this.manaLevel = p_38716_.getInt("manaLevel");
            this.tickTimer = p_38716_.getInt("manaTickTimer");
            this.saturationLevel = p_38716_.getFloat("manaSaturationLevel");
            this.exhaustionLevel = p_38716_.getFloat("manaExhaustionLevel");
        }

    }

    @Override
    public void tick(Player p_38711_) {
        Difficulty difficulty = p_38711_.level.getDifficulty();
        this.lastManaLevel = this.manaLevel;
        boolean flag = p_38711_.level.getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION);
        if (flag && this.saturationLevel > 0.0F ) {
            ++this.tickTimer;
            if (this.tickTimer >= 100) {
                if (this.manaLevel < maxmanaLevel)
                this.manaLevel += maxmanaLevel/20;
                this.tickTimer = 0;
            }
        }
    }
    
    public void addAdditionalSaveData(CompoundTag p_38720_) {
        p_38720_.putInt("manaLevel", this.manaLevel);
        p_38720_.putInt("manaTickTimer", this.tickTimer);
        p_38720_.putFloat("manaSaturationLevel", this.saturationLevel);
        p_38720_.putFloat("manaExhaustionLevel", this.exhaustionLevel);
    }

    public int getManaLevel() {
        return this.manaLevel;
    }
    public int getManaMaxLevel() {
        return this.maxmanaLevel;
    }

    public  boolean ManaLevelCheck(int manacost , boolean adddivcheck) {
        //実行できない場合はマナを返します
        if (adddivcheck==true){
            this.manaLevel += manacost;
            return true;
        }
        if (this.manaLevel>=manacost){
            if (adddivcheck==false){
                this.manaLevel -= manacost;
                return true;
            }
        }
        return  false;
    }



    public int getLastManaLevel() {
        return this.lastManaLevel;
    }

    public boolean needsMana() {
        return this.manaLevel < 20;
    }

    public void addExhaustion(float p_38704_) {
        this.exhaustionLevel = Math.min(this.exhaustionLevel + p_38704_, 40.0F);
    }

    public float getExhaustionLevel() {
        return this.exhaustionLevel;
    }

    public float getSaturationLevel() {
        return this.saturationLevel;
    }

    public void setManaLevel(int p_38706_) {
        this.manaLevel = p_38706_;
    }

    public void setSaturation(float p_38718_) {
        this.saturationLevel = p_38718_;
    }

    public void setExhaustion(float p_150379_) {
        this.exhaustionLevel = p_150379_;
    }


}