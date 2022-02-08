package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MyCapability {

    public static final Capability<MyCapabilityInterface> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});
    @SubscribeEvent
    public static void register(RegisterCapabilitiesEvent event) {
        event.register(MyCapabilityInterface.class);
    }

    public MyCapability() {
    }

}
