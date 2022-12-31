package com.sophos.hellobank.enuminterface;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum TypeMovement{

    DEBIT("Debit"),
    CREDIT("Credit");

    public final String typeMovement;

    private TypeMovement(String typeMovement) {
        this.typeMovement = typeMovement;
    }

    @JsonCreator
    public static TypeMovement decode(final String typeMovement){
        return Stream.of(TypeMovement.values()).filter(targetEnum -> 
        targetEnum.typeMovement.equals(typeMovement)).findFirst().orElse(null);
    }

    @JsonValue
    public String getTypeMovement(){
        return typeMovement;
    }
}