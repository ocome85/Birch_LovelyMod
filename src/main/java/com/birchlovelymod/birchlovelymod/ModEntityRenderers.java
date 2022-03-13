package com.birchlovelymod.birchlovelymod;


import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.LightningBoltRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = BirchLovelyMod.MOD_ID,
                value = {Dist.CLIENT},
                bus = Mod.EventBusSubscriber.Bus.MOD
)

public class ModEntityRenderers {

    public static ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(BirchLovelyMod.MOD_ID, "layer"), "layer");

    //registry list
    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIGHTNINGBOLT1.get(), LightningBoltRenderer::new);

    }
    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }
}
