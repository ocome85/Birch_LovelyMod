package com.birchlovelymod.birchlovelymod;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BirchLovelyMod.MOD_ID);

//

    public static final RegistryObject<Item> TEST_SWORD = ITEMS.register( "test_lightning_sword",
            () -> new Test_Sword(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> TEST_SWORD1 = ITEMS.register("test_lightning_sword1",
            () -> new Test_Sword1(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> TEST_SWORD2 = ITEMS.register("test_lightning_sword2",
            () -> new Test_Sword2(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

//TOOL
    public static final RegistryObject<Item> BIRCH_SHOVEL = ITEMS.register("birch_shovel",
            () -> new Birch_Shovel(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_PICKAXE = ITEMS.register("birch_pickaxe",
            () -> new Birch_Pickaxe(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));


    public static final RegistryObject<Item> BIRCH_AXE= ITEMS.register("birch_axe",
            () -> new Birch_Axe(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_HOE = ITEMS.register("birch_hoe",
            () -> new Birch_Hoe(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));


    //WEAPON

    public static final RegistryObject<Item> BIRCH_SWORD = ITEMS.register("birch_sword",
            () -> new Birch_Sword(ModTiers.BIRCH, 3, -2.4F, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_GUN = ITEMS.register("birch_gun",
            () -> new Birch_Gun(new Item.Properties().tab(BirchLovelyMod.MOD_TAB).stacksTo(1).rarity(Rarity.UNCOMMON)));

   // public static final RegistryObject<Item> BIRCH_GUN = ITEMS.register("birch_gun",
    //        () -> new Birch_Gun(new Item.Properties().tab(BirchLovelyMod.MOD_TAB).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> BIRCH_ROD_DEFENSE = ITEMS.register("birch_rod_defense",
            () -> new Birch_Rod_Defense(new Item.Properties().tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_ROD_WEAPON = ITEMS.register("birch_rod_weapon",
            () -> new Birch_Rod_Weapon(new Item.Properties().tab(BirchLovelyMod.MOD_TAB)));

    //ITEM

    public static final RegistryObject<Item> BIRCH_BUCKET = ITEMS.register("birch_bucket",
            () -> new Birch_Bucket(Fluids.EMPTY, (new Item.Properties()).stacksTo(16).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_BUCKET_FULL = ITEMS.register("birch_bucket_full",
            () -> new Birch_Bucket(Fluids.EMPTY, (new Item.Properties()).stacksTo(16).tab(BirchLovelyMod.MOD_TAB)));

    //ARMOR

    public static final RegistryObject<Item> BIRCH_HELMET= ITEMS.register("birch_helmet",
            () -> new Birch_Helmet(ModArmorMaterials.BIRCH, EquipmentSlot.HEAD, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_CHESTPLATE = ITEMS.register("birch_chestplate",
            () -> new Birch_Chestplate(ModArmorMaterials.BIRCH, EquipmentSlot.CHEST, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_LEGGINGS = ITEMS.register("birch_leggings",
            () -> new Birch_Leggings(ModArmorMaterials.BIRCH, EquipmentSlot.LEGS, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));

    public static final RegistryObject<Item> BIRCH_BOOTS = ITEMS.register("birch_boots",
            () -> new Birch_Boots(ModArmorMaterials.BIRCH, EquipmentSlot.FEET, (new Item.Properties()).tab(BirchLovelyMod.MOD_TAB)));





}