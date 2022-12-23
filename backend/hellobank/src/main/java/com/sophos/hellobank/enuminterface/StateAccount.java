package com.sophos.hellobank.enuminterface;

public enum StateAccount implements StateAccountInterface{


    ACTIVATE{
        @Override
        public String states(String activateState, String inactivateState, String cancelledState){
            return activateState;
        }
        
    },

     INACTIVE{
        @Override
        public String states(String activateState,  String inactivateState, String cancelledState){
            return inactivateState;
        }
    },

     CANCELLED{
        public String states(String activateState,  String inactivateState, String cancelledState){
            return cancelledState;
        }
     }
}
