package com.acorsetti.model.enums;

public enum PickResult {
    TO_BE_DEFINED, YES, NO;

    public static PickResult byName(String name){
        switch (name){
            case "TO_BE_DEFINED": return TO_BE_DEFINED;
            case "YES": return YES;
            case "NO": return NO;
            default: return TO_BE_DEFINED;
        }
    }
}
