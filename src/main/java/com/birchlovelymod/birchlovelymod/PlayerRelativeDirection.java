package com.birchlovelymod.birchlovelymod;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class PlayerRelativeDirection {
    public static Vec3 setvec3= null;

//プレイヤーからの前後左右の相対位置を取得します
    // Player プレイヤー　Vec3 プレイヤーの向いている方角,ベクトル leftright, updown, frontbackそれぞれplayer位置から取得したいポジションの距離
    // ooot         p=player t=target position
    // opoo        この場合はx=2 y=0  z=1と設定
    // oooo
    public static Vec3 PlayerDirectionCheck(Player player,Vec3 vec3, double leftright, double updown, double frontback){
        Direction directionx = player.getDirection();
        switch (directionx){
            case EAST -> {
               setvec3=vec3.add(frontback,updown,leftright);
            }
            case WEST -> {
                setvec3=vec3.add(-frontback,updown,-leftright);
            }
            case SOUTH -> {
                setvec3=vec3.add(-leftright,updown,frontback);
            }
            case NORTH -> {
                setvec3=vec3.add(leftright,updown,-frontback);
            }
        }
        return setvec3;
    }


    public static Vec3 PlayerVectorPosition(Player player, float xrot, float yrot) {
        return player.calculateViewVector(player.getXRot()+xrot, player.getYRot()+yrot);
    }
}
