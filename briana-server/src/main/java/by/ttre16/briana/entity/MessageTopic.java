package by.ttre16.briana.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageTopic {
    EMPLOYEE("employee:"),
    POSITION("position:"),
    CLIENT("client:"),
    CATEGORY("category:"),
    PRODUCT("product:"),
    ORDER("order:"),
    PHOTO("photo:");

    private final String name;
}
