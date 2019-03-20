package com.example.skyjar.dormitoryapp.utilsService;

public class ConfigApi {

    public static final String BASE_URL = "http://apartmentpp.azurewebsites.net/";
    public static final String SERVICE_URL = "http://apicrm.unicode.edu.vn/transaction";

    // Bill + Bill Detail + Payment
    public static final String GET_BILL_DETAIL = "mobile/all-bill";
    public static final String PAYMENT = "mobile/payment-bill";

    // User Login
    public static final String LOGIN = "token";
    public static final String GET_USER_DETAIL = "account/current-customer";
    public static final String CHANGE_PASSWORD = "account/change-password";

    // Report Problem
    public static final String GET_APARTMENT_REPORT = "mobile/report";
}
