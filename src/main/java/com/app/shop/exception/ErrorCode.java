package com.app.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED(5000, HttpStatus.INTERNAL_SERVER_ERROR, "exception.UNCATEGORIZED"),
    // ----------------------------------
    // Related to AUTH
    // ----------------------------------
    AUTH_4000(4000, HttpStatus.FORBIDDEN, "exception.auth.AUTH_4000"),
    AUTH_4001(4001, HttpStatus.UNAUTHORIZED, "exception.auth.AUTH_4001"),
    AUTH_4002(4002, HttpStatus.UNAUTHORIZED, "exception.auth.AUTH_4002"),
    AUTH_4003(4003, HttpStatus.UNAUTHORIZED, "exception.auth.AUTH_4003"),
    AUTH_4004(4004, HttpStatus.UNAUTHORIZED, "exception.auth.AUTH_4004"),
    // ----------------------------------
    // Related to USER
    // ----------------------------------
    USER_3001(3001, HttpStatus.BAD_REQUEST, "exception.user.USER_3001"),
    USER_3002(3002, HttpStatus.NOT_FOUND, "exception.user.USER_3002"),
    // ----------------------------------
    // Related to ROLE
    // ----------------------------------
    ROLE_3001(3001, HttpStatus.BAD_REQUEST, "exception.role.ROLE_3001"),
    ROLE_3002(3002, HttpStatus.NOT_FOUND, "exception.role.ROLE_3002"),
    // ----------------------------------
    // Related to PERMISSION
    // ----------------------------------
    PERMISSION_3001(3001, HttpStatus.BAD_REQUEST, "exception.permission.PERMISSION_3001"),
    PERMISSION_3002(3002, HttpStatus.NOT_FOUND, "exception.permission.PERMISSION_3002"),
    // ----------------------------------
    // Related to CATEGORY
    // ----------------------------------
    CATEGORY_3001(3001, HttpStatus.BAD_REQUEST, "Category existed"),
    CATEGORY_3002(3002, HttpStatus.NOT_FOUND, "Category not found"),
    // ----------------------------------
    // Related to PRODUCT
    // ----------------------------------
    PRODUCT_3001(3001, HttpStatus.BAD_REQUEST, "Product existed"),
    PRODUCT_3002(3002, HttpStatus.NOT_FOUND, "Product not found"),
    // ----------------------------------
    // Related to ORDER
    // ----------------------------------
    ORDER_3001(3001, HttpStatus.BAD_REQUEST, "Order existed"),
    ORDER_3002(3002, HttpStatus.BAD_REQUEST, "Order not found"),
    // Related to ORDER
    // ----------------------------------
    ORDER_DETAIL_3001(3001, HttpStatus.BAD_REQUEST, "Order detail existed"),
    ORDER_DETAIL_3002(3002, HttpStatus.BAD_REQUEST, "Order detail not found"),
    // ----------------------------------
    // Related to FILE
    // ----------------------------------
    FILE_2001(2001, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "exception.file.FILE_2001"),
    FILE_2002(2002, HttpStatus.PAYLOAD_TOO_LARGE, "exception.file.FILE_2002"),
    FILE_2003(2003, HttpStatus.INTERNAL_SERVER_ERROR, "exception.file.FILE_2003"),
    // ----------------------------------
    // Related to VALIDATION
    // ----------------------------------
    VALID_1000(1000, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1000"),
    VALID_1001(1001, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1001"),
    VALID_1002(1002, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1002"),
    VALID_1003(1003, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1003"),
    VALID_1004(1004, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1004"),
    VALID_1005(1005, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1005"),
    VALID_1006(1006, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1006"),
    VALID_1007(1007, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1007"),
    VALID_1008(1008, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1008"),
    VALID_1009(1009, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1009"),
    VALID_1010(1010, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1010"),
    VALID_1011(1011, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1011"),
    VALID_1012(1012, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1012"),
    VALID_1013(1013, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1013"),
    VALID_1014(1014, HttpStatus.BAD_REQUEST, "exception.validation.VALID_1014"),
    ;
    private int code;
    private HttpStatus httpStatus;
    private String message;
}
