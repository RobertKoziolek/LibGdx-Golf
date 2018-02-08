package com.robcio.golf.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MapId {
    EMPTY("empty/menu", "empty.tmx"),
    MAP1("Crystal cove", "crystalcove.tmx"),
    MAP2("Paramour", "paramour.tmx"),
    MAP3("Nine in nine", "nineinnine.tmx"),
    //TODO bardzo potrzebny damping predkosci, plywaja po stole
    //TODO wzbogacic tilemapa o ladniejsze luzy
    POOL("Pool", "pool.tmx");

    private String name;
    private String filename;

}
