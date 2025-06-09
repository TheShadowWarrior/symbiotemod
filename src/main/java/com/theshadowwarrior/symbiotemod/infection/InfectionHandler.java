package com.theshadowwarrior.symbiotemod.infection;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "symbiotemod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InfectionHandler {
    public static void register() {
        MinecraftForge.EVENT_BUS.register(InfectionHandler.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        // TEMP: always apply Stage 1 infection effects (replace later)
        if (!player.level().isClientSide) {
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.MOVEMENT_SPEED, 220, 0, true, false));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.DIG_SPEED, 220, 0, true, false));
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(MobEffects.JUMP, 220, 0, true, false));
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // For keeping infection data on death (we'll expand this later)
    }
}
