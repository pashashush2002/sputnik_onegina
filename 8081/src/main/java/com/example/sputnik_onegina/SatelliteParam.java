package com.example.sputnik_onegina;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Getter;

@JsonTypeInfo(
use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "type"   // поле в JSON, которое определяет тип
)
@JsonSubTypes({
@JsonSubTypes.Type(value = CommunicationSatelliteParam.class, name = "COMMUNICATION"),
@JsonSubTypes.Type(value = ImagingSatelliteParam.class, name = "IMAGE")
})
public abstract class SatelliteParam {
    @Getter
    protected SatelliteType type;
    @Getter
    protected String name;
    @Getter
    protected double batteryLevel;
}
