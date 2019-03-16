package com.example.skyjar.dormitoryapp.utilsService;


public class ClientApi extends BaseAPI {

    public BillService billService() {
        return  this.getService(BillService.class, ConfigApi.BASE_URL);
    }

    public ReportService reportService(){
        return  this.getService(ReportService.class, ConfigApi.BASE_URL);
    }
}
