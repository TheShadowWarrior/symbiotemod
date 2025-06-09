package com.theshadowwarrior.symbiotemod.infection;

import com.theshadowwarrior.symbiotemod.symbiote_capability.SymbioteProvider;
import com.theshadowwarrior.symbiotemod.symbiote_capability.SymbioteData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "symbiotemod")
public class InfectionHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (!player.level().isClientSide) {
            SymbioteProvider.get(player).ifPresent(data -> {
                int stage = data.getInfectionStage();

                // Apply buffs based on infection stage
                switch (stage) {
                    case 1 -> {
                        apply(player, MobEffects.MOVEMENT_SPEED, 0);
                        apply(player, MobEffects.DIG_SPEED, 0);
                        apply(player, MobEffects.JUMP, 0);
                        apply(player, MobEffects.LUCK, 0);
                    }
                    case 2 -> {
                        apply(player, MobEffects.DAMAGE_BOOST, 4); // Strength 5
                        apply(player, MobEffects.DAMAGE_RESISTANCE, 0);
                        apply(player, MobEffects.REGENERATION, 0);
                        apply(player, MobEffects.SATURATION, 0);
                    }
                    case 3 -> {
                        apply(player, MobEffects.DAMAGE_RESISTANCE, 2);
                        apply(player, MobEffects.REGENERATION, 4);
                        apply(player, MobEffects.FIRE_RESISTANCE, 0);
                        apply(player, MobEffects.HEALTH_BOOST, 2);
                        apply(player, MobEffects.ABSORPTION, 2);
                    }
                    case 4 -> {
                        apply(player, MobEffects.DAMAGE_RESISTANCE, 4);
                        apply(player, MobEffects.REGENERATION, 9);
                        apply(player, MobEffects.HEALTH_BOOST, 4);
                        apply(player, MobEffects.ABSORPTION, 4);
                        apply(player, MobEffects.SATURATION, 4);
                        apply(player, MobEffects.WATER_BREATHING, 0);
                        apply(player, MobEffects.CONDUIT_POWER, 0);
                        apply(player, MobEffects.DOLPHINS_GRACE, 0);
                        // Night vision is toggleable — handled separately
                    }
                }
            });
        }
    }

    private static void apply(Player player, MobEffectInstance effect) {
        player.addEffect(new MobEffectInstance(effect.getEffect(), 220, effect.getAmplifier(), true, false));
    }

    private static void apply(Player player, net.minecraft.world.effect.MobEffect effect, int level) {
        player.addEffect(new MobEffectInstance(effect, 220, level, true, false));
    }
}
