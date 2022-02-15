package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> TEST_SWORD = ITEMS.register("test_sword",
            () -> new Test_Sword(Tiers.IRON, 3, -2.4F, (new Item.Properties()).tab(ExampleMod.MOD_TAB)));

    public static final RegistryObject<Item> TEST_SWORD1 = ITEMS.register("test_sword1",
            () -> new Test_Sword1(Tiers.IRON, 3, -2.4F, (new Item.Properties()).tab(ExampleMod.MOD_TAB)));

    public static final RegistryObject<Item> TEST_SWORD2 = ITEMS.register("test_sword2",
            () -> new Test_Sword2(Tiers.IRON, 3, -2.4F, (new Item.Properties()).tab(ExampleMod.MOD_TAB)));

}