package com.birchlovelymod.birchlovelymod;


import com.google.common.collect.ImmutableSet;
import com.mrcrayfish.framework.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.BiFunction;


public class ModEntities<T extends Entity>  extends EntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITIES, BirchLovelyMod.MOD_ID);

    public static final RegistryObject<EntityType<LightningBolt>> LIGHTNINGBOLT1 =
            ENTITY_TYPE.register("lightning_bolt1",
            ()-> EntityType.Builder.of(LightningBolt::new,
                    MobCategory.MISC).noSave().sized(0.0F, 0.0F)
                    .clientTrackingRange(5)
                    .build(new ResourceLocation(BirchLovelyMod.MOD_ID, "als_almal").toString())
            );

    public ModEntities(EntityFactory p_20574_, MobCategory p_20575_, boolean p_20576_, boolean p_20577_, boolean p_20578_, boolean p_20579_, ImmutableSet p_20580_, EntityDimensions p_20581_, int p_20582_, int p_20583_) {
        super(p_20574_, p_20575_, p_20576_, p_20577_, p_20578_, p_20579_, p_20580_, p_20581_, p_20582_, p_20583_);
    }


}
