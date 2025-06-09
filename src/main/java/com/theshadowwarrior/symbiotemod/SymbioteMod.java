package com.theshadowwarrior.symbiotemod;

import com.theshadowwarrior.symbiotemod.infection.InfectionHandler;
import com.theshadowwarrior.symbiotemod.symbiote_capability.SymbioteCapabilityHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SymbioteMod.MODID)
@Mod.EventBusSubscriber(modid = SymbioteMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SymbioteMod {
    public static final String MODID = "symbiotemod";

    public SymbioteMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        InfectionHandler.register();
        MinecraftForge.EVENT_BUS.register(SymbioteCapabilityHandler.class); // ✅ Capability handler
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Client-only logic
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        SymbioteCommand.register(event.getServer().getCommands().getDispatcher()); // ✅ Register /symbiote_set_stage
    }
}
