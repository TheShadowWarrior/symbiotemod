package com.theshadowwarrior.symbiotemod.symbiote_capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SymbioteProvider implements ICapabilityProvider {
    public static Capability<SymbioteData> SYMBIOTE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private SymbioteData backend = new SymbioteData();
    private LazyOptional<SymbioteData> optional = LazyOptional.of(() -> backend);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == SYMBIOTE_CAPABILITY ? optional.cast() : LazyOptional.empty();
    }

    public CompoundTag save() {
        return backend.saveNBT();
    }

    public void load(CompoundTag tag) {
        backend.loadNBT(tag);
    }

    public static SymbioteData get(Player player) {
        return player.getCapability(SYMBIOTE_CAPABILITY).orElse(new SymbioteData());
    }
}
