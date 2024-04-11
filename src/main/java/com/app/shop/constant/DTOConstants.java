package com.app.shop.constant;

public class DTOConstants {
    public interface Product {
        int MIN_SIZE = 3;
        int MAX_SIZE = 200;
        String NAME_MSG = "Title must be between 3 and 200 characters";
        int MIN_VALUE = 0;
        int MAX_VALUE = 10000000;
        String MIN_MSG = "Price must be greater than or equal to 0";
        String MAX_MSG = "Price must be less than or equal to 10.000.000";
    }
    public interface Category {
        String EMPTY_MSG = "Category' name can not be empty";
    }
}
