package com.brainstation.fantasyfootball.common;

public enum PlayerPositionType {
    GK("Goalkeeper"),
    DF("Defender"),
    MF("Midfielder"),
    FW("Forward");

    private String label;

    public String getLabel() {
        return label;
    }

    PlayerPositionType(String label) {
        this.label = label;
    }
}
