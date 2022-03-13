package com.birchlovelymod.birchlovelymod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.birchlovelymod.birchlovelymod.PlayerCapabilityAttacher.MyCapabilityProviderEntity.*;

@Mod.EventBusSubscriber(modid = BirchLovelyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerCapabilityAttacher {

    public class MyCapabilityProviderEntity implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(BirchLovelyMod.MOD_ID, "manasystem");
        private final CapabilityInterface backend = new PlayerCapability() ;
        private final LazyOptional<CapabilityInterface> optionalData = LazyOptional.of(() -> backend);

        public MyCapabilityProviderEntity(Player eventObject) {
            new PlayerCapability(eventObject);
        }



        @NotNull
        @Override
        public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return MyCapability.INSTANCE.orEmpty(cap, this.optionalData);
        }

        void invalidate() {
            this.optionalData.invalidate();
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    @SubscribeEvent
    public void  onAttachingCapabilitiesEntity(final AttachCapabilitiesEvent event) {
         boolean iof =  event.getObject()  instanceof Player;
        if (iof ==true) {
            Player eventObject = (Player) event.getObject();
            final MyCapabilityProviderEntity provider = new MyCapabilityProviderEntity(eventObject);
            event.addCapability(IDENTIFIER, provider);
        }
    }
    public PlayerCapabilityAttacher() {
    }
}
