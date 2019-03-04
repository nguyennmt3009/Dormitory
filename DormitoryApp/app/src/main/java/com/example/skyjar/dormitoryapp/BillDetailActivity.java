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

import com.example.skyjar.dormitoryapp.CustomAdapters.BrandServiceAdapter;
import com.example.skyjar.dormitoryapp.Entities.BillDetail;
import com.example.skyjar.dormitoryapp.Entities.BrandService;
import com.example.skyjar.dormitoryapp.Repositories.BillRepository;
import com.example.skyjar.dormitoryapp.Repositories.BillRepositoryImplement;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.ArrayList;
import java.util.List;

public class BillDetailActivity extends AppCompatActivity {
    BillDetail billDetail;
    BrandServiceAdapter brandServiceAdapter;
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
        billDetail = (BillDetail) bundle.getSerializable("BillDetail");

        int index = bundle.getInt("BillDetailId");

        BillRepository repository = new BillRepositoryImplement();

        repository.getBillDetail(this, index, new CallBackData<BillDetail>() {
            @Override
            public void onSuccess(BillDetail billDetail) {
                Toast.makeText(BillDetailActivity.this, "Get data success", Toast.LENGTH_SHORT).show();
                buildLayout(billDetail);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(BillDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void buildLayout(BillDetail result) {
        txtStatus.setText(result.getStatus());
        if(result.getStatus().contains("ã")) {
            txtStatus.setTextColor(Color.rgb(49, 183, 34));
            findViewById(R.id.btnPayment).setVisibility(View.GONE);
        }
        txtAmount.setText(result.getAmount() + "");
        txtCreatedDate.setText(result.getCreatedDate());
        txtId.setText("Mã hóa đơn: " + result.getId());

        brandServiceAdapter = new BrandServiceAdapter(this, result.getServiceList()); // layout at Adapter class
        listView.setAdapter(brandServiceAdapter);
    }

    public void clickToPay(View view) {
    }
}
