package com.sophos.hellobank.enuminterface;

import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeTransaction{

    CONSIGNMENT("Consigment"),
    RETIREMENT("Retirement"),
    TRANSFERINTOACCOUNTS("Transfer into accounts");

    private final String typeTransaction;

    private TypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    @JsonCreator
    public static TypeTransaction decode(final String typeTransaction){
        return Stream.of(TypeTransaction.values()).filter(targetEnum -> 
        targetEnum.typeTransaction.equals(typeTransaction)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStateAccount(){
        return typeTransaction;
    }
}


    
