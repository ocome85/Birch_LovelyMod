package com.birchlovelymod.birchlovelymod;

import com.birchlovelymod.birchlovelymod.network.Messages;
import com.birchlovelymod.birchlovelymod.network.PacketGatherMana;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.MovementInputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.PacketDistributor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

import static com.birchlovelymod.birchlovelymod.PlayerAbilityManager.sa;

// The value here should match an entry in the META-INF/mods.toml file

@Mod(BirchLovelyMod.MOD_ID)
public class BirchLovelyMod
{

    public static  final CreativeModeTab MOD_TAB = new CreativeTab("birchlovelymodtab");
    public static final String MOD_ID = "birchlovelymod";
    public static final Logger LOGGER = LogManager.getLogger();

    public BirchLovelyMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.clientConfig);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ManaOverlay.init();
        ModEntities.ENTITY_TYPE.register(eventBus);
        eventBus.addListener(this::registerCapabilities);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::processIMC);
        ModItems.ITEMS.register(eventBus);
        ModBlocks.BLOCKS.register(eventBus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new PlayerCapabilityAttacher());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener((TickEvent.PlayerTickEvent event) -> {
            if (event.phase == TickEvent.Phase.START) {
                PlayerCapability.tick(event.player);
            }
        });
        /*
        MinecraftForge.EVENT_BUS.addListener((PlayerInteractEvent event) -> {
            if (event instanceof PlayerInteractEvent.LeftClickEmpty || event instanceof PlayerInteractEvent.LeftClickBlock) {
                if (event.getPlayer().level.isClientSide) {
                    Messages.sendToServer(new PacketGatherMana());
                }
            }
        });

        MinecraftForge.EVENT_BUS.addListener((AttackEntityEvent event) -> {
            Messages.sendToServer(new PacketGatherMana());
        }); */

        MinecraftForge.EVENT_BUS.addListener((InputEvent.ClickInputEvent event) -> {
            Messages.sendToServer(new PacketGatherMana());
        });
    }

    public void registerCapabilities(RegisterCapabilitiesEvent event)
    {
        MyCapability.register(event);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        Messages.register();
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
    }

    private void processIMC(final InterModProcessEvent event)
    {
  }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        }
    }



}
