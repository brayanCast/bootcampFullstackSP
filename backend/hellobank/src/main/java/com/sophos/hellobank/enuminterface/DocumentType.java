package com.sophos.hellobank.enuminterface;


import java.util.stream.Stream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



public enum DocumentType{
    DRIVERLICENSE("Driver License"),
    IDENTITYCARD("Identity Card"),
    SOCIALSECURITYCARD("Social Security"),
    PASSPORTCARD("Passport Card"),
    NIT("NIT");

    private final String documentType;

    DocumentType(String documentType) {
        this.documentType = documentType;
    }

    @JsonCreator
    public static DocumentType decode(final String documentType){
        return Stream.of(DocumentType.values()).filter(targetEnum -> 
        targetEnum.documentType.equals(documentType)).findFirst().orElse(null);
    }

    @JsonValue
    public String getDocumentType(){
        return documentType;
    }

}
