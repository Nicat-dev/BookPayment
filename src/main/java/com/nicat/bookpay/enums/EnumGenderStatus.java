package com.nicat.bookpay.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumGenderStatus {
    MALE(1),FEMALE(0);
    private int value;

    private static final Map<Integer,EnumGenderStatus> VALUES = new HashMap<Integer,EnumGenderStatus>();

    static {
        for (EnumGenderStatus type : EnumGenderStatus.values()){
            VALUES.put(type.value,type);
        }
    }

    private EnumGenderStatus(int enumValue){
        this.value=enumValue;
    }

    public Integer getValue(){
        return value;
    }

    public static EnumGenderStatus getEnum(Integer value){
        if (value==null)
            return null;

        return VALUES.get(value.intValue());
    }
}
