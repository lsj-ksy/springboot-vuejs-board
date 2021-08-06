package com.sjsy.springvue.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    MALE("남"),
    FEMALE("여"),
    UNKNOWN("비공개");

    private final String value;
}
