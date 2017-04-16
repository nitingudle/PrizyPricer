package com.nitin.prizy.rest.utils;

public interface APIConstant
{
    public static final String PRODUCT         = "/v1/products/{productCode}";
    public static final String PRODUCTS        = "/v1/products";

    public static final String INVALID_STORE   = "Store name not valid";
    public static final String INVALID_PRODUCT = "Product code not valid";
    public static final String INVALID_PRICE   = "Product price not valid";
    public static final String INVALID_LIMIT   = "Request number of product is not valid";
}
