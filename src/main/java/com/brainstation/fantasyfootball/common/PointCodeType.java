package com.brainstation.fantasyfootball.common;

public enum PointCodeType {
    GS("Goal Scored",4),
    AS("Assist", 3),
    MP("Match Played", 2),
    CS("Clean Sheet", 4),
    YC("Yellow Card", -1),
    RC("Red Card", -2),
    MOM("Man of the Match",3);


    private String label;
    private Integer PointScore;

    public Integer getPointScore() {
        return PointScore;
    }
    public String getLabel() {
        return label;
    }

    PointCodeType(String label, Integer pointScore) {
        this.label = label;
        PointScore = pointScore;
    }
}
