package com.sophos.hellobank.enuminterface;

import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeAccount{
    CURRENT_ACCOUNT("Current Account"),
    SAVINGS_ACCOUNT("Savings Account");

    private final String typeAccount;

    private TypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    @JsonCreator
    public static TypeAccount decode(final String typeAccount){
        return Stream.of(TypeAccount.values()).filter(targetEnum -> 
        targetEnum.typeAccount.equals(typeAccount)).findFirst().orElse(null);
    }

    @JsonValue
    public String getTypeAccount(){
        return typeAccount;
    }



}