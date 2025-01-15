package com.brainstation.fantasyfootball.common;

/**
 * @author Shahinur Beg
 * created date: 10/20/2022
 */
public enum LineFormationEnum {
    F433("4-3-3"),F343("3-4-3"),F442("4-4-2"),F352("3-5-2"),F451("4-5-1");
    private final String formation;
    private LineFormationEnum(String formation)
    {
        this.formation=formation;
    }
}
