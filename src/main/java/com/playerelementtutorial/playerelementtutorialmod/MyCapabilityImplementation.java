package com.playerelementtutorial.playerelementtutorialmod;


import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public  class MyCapabilityImplementation implements MyCapabilityInterface {
    protected static ManaData manaData = new ManaData();
    private static final String NBT_KEY_DAMAGE_DEALT = "damageDealt";
    Minecraft minecraft = Minecraft.getInstance();
    public final Player player ;
    public final Level level ;
    private  String Value = "";


    public MyCapabilityImplementation(Player eventObject ) {
        player=eventObject;
        level = eventObject.getLevel();
    }

    public MyCapabilityImplementation() {
        player = null;
        level = null;


    }

    @Override
    public ManaData getManaData() {
        return this.manaData;
    }


    public static void tick(Player player) {
                 MyCapabilityImplementation.manaData.tick(player);
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putString(NBT_KEY_DAMAGE_DEALT, this.Value);
        return tag;
    }


    @Override
    public String getValue() {
        return this.Value;
    }

    @Override
    public void setValue(String Value) {
        this.Value = Value;
    }



    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.Value = nbt.getString(NBT_KEY_DAMAGE_DEALT);
    }


}

