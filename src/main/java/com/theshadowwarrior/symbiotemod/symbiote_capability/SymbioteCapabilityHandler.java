package com.theshadowwarrior.symbiotemod.symbiote_capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "symbiotemod")
public class SymbioteCapabilityHandler {
    private static final ResourceLocation CAP_ID = new ResourceLocation("symbiotemod", "symbiote");

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(SymbioteData.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Player> event) {
        event.addCapability(CAP_ID, new SymbioteProvider());
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        event.getOriginal().getCapability(SymbioteProvider.SYMBIOTE_CAPABILITY).ifPresent(oldStore -> {
            event.getEntity().getCapability(SymbioteProvider.SYMBIOTE_CAPABILITY).ifPresent(newStore -> {
                CompoundTag tag = oldStore.saveNBT();
                newStore.loadNBT(tag);
            });
        });
    }
}
