package com.birchlovelymod.birchlovelymod;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Birch_Hoe extends HoeItem {
    public Birch_Hoe(Tier p_41336_, int p_41337_, float p_41338_, Properties p_41339_) {
        super(p_41336_, p_41337_, p_41338_, p_41339_);
    }


    public InteractionResult useOn(UseOnContext p_41341_) {
        Level level = p_41341_.getLevel();
        BlockPos blockpos = p_41341_.getClickedPos();
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = TILLABLES.get(level.getBlockState(blockpos).getBlock());
        int hook = net.minecraftforge.event.ForgeEventFactory.onHoeUse(p_41341_);
        if (hook != 0) return hook > 0 ? InteractionResult.SUCCESS : InteractionResult.FAIL;
        if (p_41341_.getClickedFace() != Direction.DOWN && level.isEmptyBlock(blockpos.above())) {
            if (pair == null) {
                return InteractionResult.PASS;
            } else {
                Predicate<UseOnContext> predicate = pair.getFirst();
                Consumer<UseOnContext> consumer = pair.getSecond();
                if (predicate.test(p_41341_)) {
                    Player player = p_41341_.getPlayer();
                    level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!level.isClientSide) {
                        consumer.accept(p_41341_);
                        if (player != null) {
                            p_41341_.getItemInHand().hurtAndBreak(1, player, (p_150845_) -> {
                                p_150845_.broadcastBreakEvent(p_41341_.getHand());
                            });
                        }
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
