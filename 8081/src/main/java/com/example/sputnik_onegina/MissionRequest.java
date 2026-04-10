package com.example.sputnik_onegina;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MissionRequest {
    @Getter
    private final String constellationName;
    @Getter
    private final String satelliteName;
    @Getter
    private final boolean constellation;
}
