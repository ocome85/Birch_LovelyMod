package com.playerelementtutorial.playerelementtutorialmod;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class Test_Sword extends SwordItem {

    private static final int MANA_COST= 20;

    public Test_Sword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41290_, Player p_41291_, InteractionHand p_41292_) {
        ItemStack itemstack = p_41291_.getItemInHand(p_41292_);

        LazyOptional<MyCapabilityInterface> stats1 = p_41291_.getCapability(MyCapability.INSTANCE);
        MyCapabilityInterface myCapabilityInterface=stats1.orElseThrow(IllegalStateException::new);
        ManaData manaData= myCapabilityInterface.getManaData();
         boolean manacheck= manaData.ManaLevelCheck(MANA_COST);
         if (manacheck==true){
             //ここにAbility
         }else {
             new TranslatableComponent("deathScreen.title.hardcore");
         }
        return InteractionResultHolder.sidedSuccess(itemstack, p_41290_.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {

    }

}
