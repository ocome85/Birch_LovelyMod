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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.playerelementtutorial.playerelementtutorialmod.MyCapabilityAttacher.MyCapabilityProviderEntity.*;


public class MyCapabilityAttacher {


    public class MyCapabilityProviderEntity implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(ExampleMod.MOD_ID, "tutorial");
        private final MyCapabilityInterface backend = new MyCapabilityImplementation() ;
        private final LazyOptional<MyCapabilityInterface> optionalData = LazyOptional.of(() -> backend);

        public MyCapabilityProviderEntity(Player eventObject) {
            new MyCapabilityImplementation(eventObject);
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
    public void  onAttachingCapabilitiesEntity(final AttachCapabilitiesEvent<Entity> event) {
         boolean iof =  event.getObject()  instanceof Player;
        if (iof ==true) {
            Player eventObject = (Player) event.getObject();
            final MyCapabilityProviderEntity provider = new MyCapabilityProviderEntity(eventObject);
            event.addCapability(IDENTIFIER, provider);
       }
    }

    @SubscribeEvent
    public  void onPlayerPreTick(final TickEvent.PlayerTickEvent  event ,final AttachCapabilitiesEvent<Entity> event1){
      Player player = event.player;
      boolean iof = player  instanceof Player;
        if (iof ==true) {
            LazyOptional<MyCapabilityInterface> stats1 = player.getCapability(MyCapability.INSTANCE);
            MyCapabilityInterface myCapabilityInterface = stats1.orElseThrow(IllegalStateException::new);
            myCapabilityInterface.tick(event.player);
            final MyCapabilityProviderEntity provider = new MyCapabilityProviderEntity(player);
            event1.addCapability(IDENTIFIER, provider);

      /*
        if (iof ==true) {
            LazyOptional<MyCapabilityInterface> stats1 = player.getCapability(MyCapability.INSTANCE);
            try {
                MyCapabilityInterface myCapabilityInterface = stats1.orElseThrow(IllegalStateException::new);
                myCapabilityInterface.tick(event.player);
            } catch (IllegalStateException e) {
                return;
            }

       */
           //MyCapabilityInterface myCapabilityInterface = stats1.orElseThrow(IllegalStateException::new);
           // myCapabilityInterface.tick(event.player);
        }
    }


    public MyCapabilityAttacher() {
    }
}
