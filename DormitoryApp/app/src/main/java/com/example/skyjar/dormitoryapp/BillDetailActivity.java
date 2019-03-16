package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.BillDetailAdapter;
import com.example.skyjar.dormitoryapp.Entities.Bill;
import com.example.skyjar.dormitoryapp.Repositories.BillRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

public class BillDetailActivity extends AppCompatActivity {
    Bill billDetail;
    BillDetailAdapter brandServiceAdapter;
    Toolbar toolbar;
    ListView listView;
    TextView txtId;
    TextView txtCreatedDate;
    TextView txtStatus;
    TextView txtAmount;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initialView();
    }

    private void initialView() {
        //Mapping

        listView = findViewById(R.id.lstBrandService);
        txtCreatedDate = findViewById(R.id.txtBillDate);
        txtId = findViewById(R.id.txtBillId);
        txtAmount = findViewById(R.id.txtBillAmount);
        txtStatus = findViewById(R.id.txtBillStatus);
        
        //Call Data
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        billDetail = (Bill) bundle.getSerializable("BillDetail");

        buildLayout(billDetail);
    }
    
    private void buildLayout(Bill result) {
        txtStatus.setText(result.getStatus());
        if(result.getStatus().contains("ã")) {
            txtStatus.setTextColor(Color.rgb(49, 183, 34));
            findViewById(R.id.btnPayment).setVisibility(View.GONE);
        }
        txtAmount.setText(result.getAmount() + "");
        txtCreatedDate.setText(result.getCreatedDate());
        txtId.setText("Mã hóa đơn: " + result.getId());

        brandServiceAdapter = new BillDetailAdapter(this, result.getBillDetails()); // layout at Adapter class
        listView.setAdapter(brandServiceAdapter);
    }

    public void clickToPay(View view) {

        BillRepository repository = new BillRepository();
        repository.paymentBill(1, 5, new CallBackData<String>() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(BillDetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(BillDetailActivity.this, "Fail pay " + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
