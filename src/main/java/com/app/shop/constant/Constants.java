package com.app.shop.constant;

public class Constants {
    public interface Common {
        int MAXIMUM_IMAGES_PER_PRODUCT = 5;
    }

    public interface Pattern {
        String TIME = "HH:mm:ss yyyy-MM-dd";
        String DATE = "yyMMddHHmmSS";
        String DOB = "yyyy-MM-dd";
    }

    public interface PreDefineRole {
        String ROLE_USER = "USER";
        String ROLE_ADMIN = "ADMIN";
    }

    public interface ADMIN_ACCOUNT {
        String ADMIN_USERNAME = "admin";
        String ADMIN_PASSWORD = "admin";
    }

}
