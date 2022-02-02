package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class MyCapabilityAttacher {


    public class MyCapabilityProviderEntity implements ICapabilityProvider, INBTSerializable<CompoundTag> {
        public static final ResourceLocation IDENTIFIER = new ResourceLocation(ExampleMod.MOD_ID, "tutorial");
        private final MyCapabilityInterface backend = new MyCapabilityImplementation() ;
        private final LazyOptional<MyCapabilityInterface> optionalData = LazyOptional.of(() -> backend);

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
    public void  onAttachingCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
        final MyCapabilityProviderEntity provider = new MyCapabilityProviderEntity();

       boolean iof =  event.getObject()  instanceof Player;
       if (iof ==true) {
           Player eventObject = (Player) event.getObject();
           event.addCapability(MyCapabilityProviderEntity.IDENTIFIER, provider);
           new MyCapabilityImplementation().tick(eventObject);
       }
    }

    public MyCapabilityAttacher() {
    }
}
