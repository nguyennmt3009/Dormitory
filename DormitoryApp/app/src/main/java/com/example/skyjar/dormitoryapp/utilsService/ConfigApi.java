package com.example.skyjar.dormitoryapp.utilsService;

public class ConfigApi {

    public static final String BASE_URL = "http://apartmentpp.azurewebsites.net/";

    // Bill + Bill Detail + Payment
    public static final String GET_BILL_DETAIL = "mobile/all-bill";
    public static final String PAYMENT = "mobile/payment-bill";

    // User Login
    public static final String LOGIN = "token";
    public static final String GET_USER_DETAIL = "account/current-customer";

    // Report Problem
    public static final String GET_APARTMENT_REPORT = "mobile/report";
}
