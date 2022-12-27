package com.sophos.hellobank.enuminterface;

import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StateAccount{

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    CANCELLED("Canceled");

    private final String stateAccount;

    private StateAccount(String stateAccount) {
        this.stateAccount = stateAccount;
    }

    @JsonCreator
    public static StateAccount decode(final String stateAccount){
        return Stream.of(StateAccount.values()).filter(targetEnum -> 
        targetEnum.stateAccount.equals(stateAccount)).findFirst().orElse(null);
    }

    @JsonValue
    public String getStateAccount(){
        return stateAccount;
    }
    
}
