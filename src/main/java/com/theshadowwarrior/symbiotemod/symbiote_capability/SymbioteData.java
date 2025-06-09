package com.theshadowwarrior.symbiotemod.symbiote_capability;

import net.minecraft.nbt.CompoundTag;

public class SymbioteData {
    private int infectionStage = 0; // 0–4
    private int bondLevel = 0;      // 0–100
    private boolean symbioteVisible = true; // true = deployed
    private boolean nightVisionOn = false;
    private boolean curedOnce = false;

    public int getInfectionStage() { return infectionStage; }
    public void setInfectionStage(int stage) { this.infectionStage = stage; }

    public int getBondLevel() { return bondLevel; }
    public void setBondLevel(int level) { this.bondLevel = level; }

    public boolean isSymbioteVisible() { return symbioteVisible; }
    public void setSymbioteVisible(boolean visible) { this.symbioteVisible = visible; }

    public boolean hasNightVision() { return nightVisionOn; }
    public void setNightVision(boolean on) { this.nightVisionOn = on; }

    public boolean hasBeenCured() { return curedOnce; }
    public void setCuredOnce(boolean flag) { this.curedOnce = flag; }

    public CompoundTag saveNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("InfectionStage", infectionStage);
        tag.putInt("BondLevel", bondLevel);
        tag.putBoolean("Visible", symbioteVisible);
        tag.putBoolean("NightVision", nightVisionOn);
        tag.putBoolean("CuredOnce", curedOnce);
        return tag;
    }

    public void loadNBT(CompoundTag tag) {
        infectionStage = tag.getInt("InfectionStage");
        bondLevel = tag.getInt("BondLevel");
        symbioteVisible = tag.getBoolean("Visible");
        nightVisionOn = tag.getBoolean("NightVision");
        curedOnce = tag.getBoolean("CuredOnce");
    }
}

