package com.sophos.hellobank.enuminterface;

public enum TypeTransactionEnum implements TypeTransactionInterface{
    CONSIGNMENT{
        @Override
        public String types(String consignment, String retirement, String transferIntoAccount){
            return consignment;
        }
    },

     RETIREMENT{
        @Override
        public String types(String consignment,  String retirement, String transferIntoAccount){
            return retirement;
        }
    },

     TRANSFERINTOACCOUNT{
        public String types(String consignment,  String retirement, String transferIntoAccount){
            return transferIntoAccount;
        }
     }

    
}