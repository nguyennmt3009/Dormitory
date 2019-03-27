package com.example.skyjar.dormitoryapp.utilsService;


import com.example.skyjar.dormitoryapp.utilsService.OurService.BillService;
import com.example.skyjar.dormitoryapp.utilsService.OurService.ContractService;
import com.example.skyjar.dormitoryapp.utilsService.OurService.ReportService;
import com.example.skyjar.dormitoryapp.utilsService.OurService.TransactionService;
import com.example.skyjar.dormitoryapp.utilsService.OurService.UserService;

public class ClientApi extends BaseAPI {

    public BillService billService() {
        return this.getService(BillService.class, ConfigApi.BASE_URL);
    }

    public UserService userService() {
        return this.getService(UserService.class, ConfigApi.BASE_URL);
    }

    public ReportService reportService(){
        return  this.getService(ReportService.class, ConfigApi.BASE_URL);
    }

    public ContractService contractService() {
        return this.getService(ContractService.class, ConfigApi.BASE_URL);
    }

    public TransactionService transactionService(){
        return this.getService(TransactionService.class, ConfigApi.BASE_URL);
    }
}
