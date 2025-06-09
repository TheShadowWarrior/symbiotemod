package com.theshadowwarrior.symbiotemod;

import com.theshadowwarrior.symbiotemod.infection.InfectionHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SymbioteMod.MODID)
public class SymbioteMod {
    public static final String MODID = "symbiotemod";

    public SymbioteMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        InfectionHandler.register(); // 🔥 Register infection system
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Client-only init
    }
}
