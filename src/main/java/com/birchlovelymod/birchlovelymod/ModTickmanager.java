package com.birchlovelymod.birchlovelymod;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class ModTickmanager {
    private static final Predicate<Monster> PREDICATE_MOB = p_29453_ -> p_29453_ != null;
    private int tickcounter;
    public int doubleabilitystop;
    private int tickabilitystart =0;
    public Player player;
    public Level level;
    public List<Monster> list;
    public int listsize =0;
    public int lightningabilitycount=0;

    public void tick(Player player) {
        if (player.level.isClientSide) {
            Vec3 vec3 = player.getLookAngle();
            float x= player.getXRot();
            float y=player.getYRot();
            Component component = new TranslatableComponent(vec3.toString()+"x:"+x+"y:"+y).withStyle(ChatFormatting.AQUA);
            player.displayClientMessage(component, true);
        }
        ++this.tickcounter;
        //複数呼び出しは禁止
            switch (tickabilitystart) {
                case 1:
                    if (tickcounter > lightningabilitycount * 10) {
                        LightningBoltAbility(level, player, list, lightningabilitycount, listsize);
                    }
            }
    }
    //時間差雷呼び出し
    public void LightningBoltAbility(Level level ,Player player,List<Monster> list,int lightningabilitycount, int listsize ){
        Mob mob = list.get(lightningabilitycount);
        mob.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 5), player);
        LightningBolt lightningbolt = ModEntities.LIGHTNINGBOLT1.get().create(level);
        lightningbolt.moveTo(mob.position());
        lightningbolt.setVisualOnly(false);
        level.addFreshEntity(lightningbolt);
        this.lightningabilitycount+=1;
        if (listsize-1==lightningabilitycount){
            ResetValue();
        }
    }
    public void LightningBoltAbilityuse(Level p_41290_, Player p_41291_,List<Monster> list,int size ) {
        this.tickcounter=0;
        this.tickabilitystart =1;
        this.level=p_41290_;
        this.player=p_41291_;
        this.list =list;
        this.listsize =size;
        this.doubleabilitystop=1;
    }


    public void ResetValue(){
        this.doubleabilitystop=0;
        this.tickabilitystart =0;
        this.level=null;
        this.player=null;
        this.list =null;
        this.listsize =0;
        this.lightningabilitycount =0;
    }
}
