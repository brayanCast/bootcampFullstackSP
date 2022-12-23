package com.sophos.hellobank.enuminterface;

public enum DocumentType implements DocumentTypeInterface {
    DRIVERLICENSE {
        @Override
        public String documentType(String driverLicense, String identityCard, String socialSecurityCard,
                String passportCard) {
            return driverLicense;
        }
    },
    IDENTITYCARD {
        @Override
        public String documentType(String driverLicense, String identityCard, String socialSecurityCard,
                String passportCard) {
            return identityCard;
        }

    },
    SOCIALSECURITYCARD {
        @Override
        public String documentType(String driverLicense, String identityCard, String socialSecurityCard,
                String passportCard) {
            return socialSecurityCard;
        }
    },
    PASSPORTCARD {
        @Override
        public String documentType(String driverLicense, String identityCard, String socialSecurityCard,
                String passportCard) {
            return passportCard;
        }
    }
}
