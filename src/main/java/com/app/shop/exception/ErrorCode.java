package com.app.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED(5000, "uncategorized exception"),
    DATA_NOT_FOUND(5001, "Data not found"),
    USER_EXISTED(3001, "user existed"),
    // FILE
    FILE_FORMAT_NOT_SUPPORT(2002, "file format not supported"),
    FILE_PAYLOAD_TOO_LARGE(2003, "Payload Too Large"),
    FILE_UNSUPPORTED_MEDIA_TYPE(2004, "Unsupported Media Type"),
    FILE_NUMBER_LIMIT(2005, "Maximum image price is five"),

    // VALIDATION
    INVALID_ID(1000, "Id must be greater than 0"),
    INVALID_NAME(1001, "Full name is required"),
    INVALID_PHONE_NUMBER(1002, "Phone number is required"),
    INVALID_PASSWORD(1003, "Password is required"),

    INVALID_PRODUCT_IMAGE_URL(1004, "Image url must be between 3 and 200 characters"),

    INVALID_CATEGORY_NAME(1005, "Category' name can not be empty"),

    INVALID_PRODUCT_NAME(1006, "Title must be between 3 and 200 characters"),
    INVALID_PRODUCT_PRICE(1007, "Product price must be between 0 and 10.000.000"),

    INVALID_ORDER_SHIP_METHOD(1008, "Shipping method is required"),
    INVALID_ORDER_SHIP_ADDRESS(1008, "Shipping address is required"),
    INVALID_ORDER_PAYMENT_METHOD(1008, "Payment method is required"),
    INVALID_ORDER_NUMBER_PRODUCT(1009, "Number of product must be greater than 1"),
    ;
    private int code;
    private String message;
}
