package com.birchlovelymod.birchlovelymod;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class  ManaOverlay extends Gui implements IIngameOverlay
{

    private Minecraft mc;
    private Font font = null;
    public int left_height = 39;
    public int right_height = 49;
    private RenderGameOverlayEvent eventParent;
    public  final ResourceLocation GUI_MANA_LOCATION = new ResourceLocation(BirchLovelyMod.MOD_ID, "textures/gui/icons.png");
    public static final ManaOverlay INSTANCE = new ManaOverlay(Minecraft.getInstance());


    public  ManaOverlay(Minecraft p_93005_) {
        super(p_93005_);

        this.mc = Minecraft.getInstance();
        this.font = mc.font;


    }

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(INSTANCE);

    }

    public final IIngameOverlay MANA_LEVEL_ELEMENT = OverlayRegistry.registerOverlayTop("Mana ", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        boolean isMounted = gui.minecraft.player.getVehicle() instanceof LivingEntity;
        if (!isMounted && !gui.minecraft.options.hideGui && gui.shouldDrawSurvivalElements())
        {
            setupOverlayRenderState(true, false);
            renderMana(screenWidth, screenHeight, mStack);
        }
    });



    public void renderMana(int width, int height, PoseStack mStack)
    {
        minecraft.getProfiler().push("mana");

        Player player = (Player)this.minecraft.getCameraEntity();
        RenderSystem.enableBlend();
        int left = width / 2 + 91;
        int top = height - right_height ;
        //空気が減った場合バーの位置をずらす
        if (player.getAirSupply()<300) {
            top -= 10;
        }


        boolean unused = false;

        LazyOptional<CapabilityInterface> stats1 = minecraft.player.getCapability(MyCapability.INSTANCE);
        CapabilityInterface myCapabilityInterface=stats1.orElseThrow(IllegalStateException::new);
        ManaData manaData= myCapabilityInterface.getManaData();
        int level = manaData.getManaLevel();
        int maxlevel = manaData.getManaMaxLevel();
        int checkmax = maxlevel/20;


        for (int i = 0; i < 10; ++i)
        {
            int idx = i * (2*checkmax) + 1;
            int x = left - i * 8 - 9;
            int y = top;
            int icon = 16;
            byte background = 0;

            blit(mStack, x, y, 16 + background * 9, 27, 9, 9);

            if (idx < level)
                blit(mStack, x, y, icon + 9, 27, 9, 9);
            else if (idx == level)
                blit(mStack, x, y, icon + 18, 27, 9, 9);
        }
        RenderSystem.disableBlend();
        minecraft.getProfiler().pop();
    }



    public void setupOverlayRenderState(boolean blend, boolean depthText)
    {
        setupOverlayRenderState(blend, depthText, GUI_MANA_LOCATION);
    }

    public void setupOverlayRenderState(boolean blend, boolean depthTest, @Nullable ResourceLocation texture)
    {
        if (blend)
        {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
        }
        else
        {
            RenderSystem.disableBlend();
        }

        if (depthTest)
        {
            RenderSystem.enableDepthTest();
        }
        else
        {
            RenderSystem.disableDepthTest();
        }

        if (texture != null)
        {
            RenderSystem.enableTexture();
            bind(texture);
        }
        else
        {
            RenderSystem.disableTexture();
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
    }


    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {
        /*
        this.screenWidth = this.minecraft.getWindow().getGuiScaledWidth();
        this.screenHeight = this.minecraft.getWindow().getGuiScaledHeight();
        eventParent = new RenderGameOverlayEvent(mStack, partialTicks, this.minecraft.getWindow());

        if (pre(ALL, mStack)) return;

        font = minecraft.font;

        this.random.setSeed(tickCount * 312871L);

        OverlayRegistry.orderedEntries().forEach(entry -> {
            try
            {
                if (!entry.isEnabled()) return;
                IIngameOverlay overlay = entry.getOverlay();
                if (pre(overlay, mStack)) return;
               //overlay.render(this, mStack, partialTicks, screenWidth, screenHeight);
                post(overlay, mStack);
            }
            catch(Exception e)
            {

            }
        });

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        post(ALL, mStack);
        */
    }



    private boolean pre(RenderGameOverlayEvent.ElementType type, PoseStack mStack)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(mStack, eventParent, type));
    }
    private void post(RenderGameOverlayEvent.ElementType type, PoseStack mStack)
    {
        MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(mStack, eventParent, type));
    }
    private boolean pre(IIngameOverlay overlay, PoseStack mStack)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.PreLayer(mStack, eventParent, overlay));
    }
    private void post(IIngameOverlay overlay, PoseStack mStack)
    {
        MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.PostLayer(mStack, eventParent, overlay));
    }
    private void bind(ResourceLocation res)
    {
        RenderSystem.setShaderTexture(0, res);
    }

}
