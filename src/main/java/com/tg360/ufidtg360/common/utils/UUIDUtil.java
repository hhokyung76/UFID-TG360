package com.tg360.ufidtg360.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.regex.Pattern;


@Slf4j
public class UUIDUtil {
    public static final String UUID_V4_STRING =
            "^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$";
    public Pattern UUID_REGEX_PATTERN;

    private String myId;


    public String getMyId() {
        return myId;
    }

    public UUIDUtil() {
        myId = UUID.randomUUID().toString();
        log.info("myId: "+myId);
        UUID_REGEX_PATTERN = Pattern.compile(UUID_V4_STRING);
    }

    public boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }
}