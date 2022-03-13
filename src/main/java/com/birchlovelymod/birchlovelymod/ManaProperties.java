package com.birchlovelymod.birchlovelymod;


import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.function.Supplier;

public class ManaProperties {
    private final int nutrition;
    private final float saturationModifier;
    private final boolean isMeat;
    private final boolean canAlwaysEat;
    private final boolean fastFood;
    private final List<Pair<Supplier<MobEffectInstance>, Float>> effects;

    private ManaProperties(Builder builder) {
        this.nutrition = builder.nutrition;
        this.saturationModifier = builder.saturationModifier;
        this.isMeat = builder.isMeat;
        this.canAlwaysEat = builder.canAlwaysEat;
        this.fastFood = builder.fastFood;
        this.effects = builder.effects;
    }

    // Forge: Use builder method instead
    @Deprecated
    ManaProperties(int p_38730_, float p_38731_, boolean p_38732_, boolean p_38733_, boolean p_38734_, List<Pair<MobEffectInstance, Float>> p_38735_) {
        this.nutrition = p_38730_;
        this.saturationModifier = p_38731_;
        this.isMeat = p_38732_;
        this.canAlwaysEat = p_38733_;
        this.fastFood = p_38734_;
        this.effects = p_38735_.stream().map(pair -> Pair.<Supplier<MobEffectInstance>, Float>of(pair::getFirst, pair.getSecond())).collect(java.util.stream.Collectors.toList());
    }

    public int getNutrition() {
        return this.nutrition;
    }

    public float getSaturationModifier() {
        return this.saturationModifier;
    }

    public boolean isMeat() {
        return this.isMeat;
    }

    public boolean canAlwaysEat() {
        return this.canAlwaysEat;
    }

    public boolean isFastFood() {
        return this.fastFood;
    }

    public List<Pair<MobEffectInstance, Float>> getEffects() {
        return this.effects.stream().map(pair -> Pair.of(pair.getFirst() != null ? pair.getFirst().get() : null, pair.getSecond())).collect(java.util.stream.Collectors.toList());
    }

    public static class Builder {
        private int nutrition;
        private float saturationModifier;
        private boolean isMeat;
        private boolean canAlwaysEat;
        private boolean fastFood;
        private final List<Pair<Supplier<MobEffectInstance>, Float>> effects = Lists.newArrayList();

        public Builder nutrition(int p_38761_) {
            this.nutrition = p_38761_;
            return this;
        }

        public Builder saturationMod(float p_38759_) {
            this.saturationModifier = p_38759_;
            return this;
        }

        public Builder meat() {
            this.isMeat = true;
            return this;
        }

        public Builder alwaysEat() {
            this.canAlwaysEat = true;
            return this;
        }

        public Builder fast() {
            this.fastFood = true;
            return this;
        }

        public Builder effect(Supplier<MobEffectInstance> effectIn, float probability) {
            this.effects.add(Pair.of(effectIn, probability));
            return this;
        }

        // Forge: Use supplier method instead
        @Deprecated
        public Builder effect(MobEffectInstance p_38763_, float p_38764_) {
            this.effects.add(Pair.of(() -> p_38763_, p_38764_));
            return this;
        }

        public ManaProperties build() {
            return new ManaProperties(this);
        }
    }
}
