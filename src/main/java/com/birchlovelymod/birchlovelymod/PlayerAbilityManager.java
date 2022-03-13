package com.birchlovelymod.birchlovelymod;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class PlayerAbilityManager {
    public static void tick(Player player) {
    }
    public static void sa(Player player, ItemStack itemstack,ServerPlayer serverPlayer) {
        if (!player.level.isClientSide) {
            Vec3 vec3 = player.getViewVector(1.0F);
            double d2 ; double d3 ; double d4;
          d2 = vec3.x*4.0D;
          d3 = vec3.y*4.0D;
          d4 = vec3.z*4.0D;
            SmallFireball largefireball = new SmallFireball(player.level, player, d2, d3, d4);
            SmallFireball largefireball2 = new SmallFireball(player.level, player, d2, d3, d4);
            SmallFireball largefireball3 = new SmallFireball(player.level, player, d2, d3, d4);
            largefireball.setPos(player.getX()+vec3.x * 4.0D , player.getY(0.5D)+0.5D, player.getZ()+ vec3.z * 4.0D);
            player.level.addFreshEntity(largefireball);
            if (player.isCrouching()){
                largefireball2.setPos(player.getX() , player.getY(0.5D)+0.5D, player.getZ());
                player.level.addFreshEntity(largefireball2);
            }else if (player.isInWater()){
                largefireball3.setPos(player.getX() , player.getY(0.5D)+0.5D, player.getZ());
                player.level.addFreshEntity(largefireball3);
            }

        }
    }
}