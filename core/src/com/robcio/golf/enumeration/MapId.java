package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MapId {
    MAP1("Crystal cove", "crystalcove.tmx"),
    MAP2("Paramour", "paramour.tmx"),
    MAP3("Nine in nine", "nineinnine.tmx"),
    POOL("Pool", "pool.tmx"),
    SLOPES("Slopes", "slopes.tmx"),
    TEST_GROUND("Test grounds", "testground.tmx");

    private String name;
    private String filename;

}
