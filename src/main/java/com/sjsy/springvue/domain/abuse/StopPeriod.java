package com.sjsy.springvue.domain.abuse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StopPeriod {

    DAYS_3(259200L, "3일"),
    DAYS_7(604800L, "7일"),
    DAYS_14(1209600L, "14일"),
    DAYS_30(2592000L, "30일");

    private final Long sec;
    private final String title;
}
