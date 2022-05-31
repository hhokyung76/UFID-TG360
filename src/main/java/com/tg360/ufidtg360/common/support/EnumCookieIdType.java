package com.tg360.ufidtg360.common.support;

import java.util.Arrays;

public enum EnumCookieIdType {

    MUID("_tg_mid"),
    TUID("_tg_id"),
    STID("_tg_st"),
    ETC("");

    private final String code;

    EnumCookieIdType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static EnumCookieIdType find(String code) {
        return Arrays.stream(values())
                .filter(enumCookieIdType -> enumCookieIdType.code.equals(code))
                .findAny()
                .orElse(ETC);
    }

}
