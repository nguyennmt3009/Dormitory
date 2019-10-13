package com.example.skyjar.dormitoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.BillDetailAdapter;
import com.example.skyjar.dormitoryapp.Entities.Bill;
import com.example.skyjar.dormitoryapp.Entities.User;
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
    Button btnPay;
    User currentUser = null;
    

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

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");

        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

        listView = findViewById(R.id.lstBrandService);
        txtCreatedDate = findViewById(R.id.txtBillDate);
        txtId = findViewById(R.id.txtBillId);
        txtAmount = findViewById(R.id.txtBillAmount);
        txtStatus = findViewById(R.id.txtBillStatus);
        btnPay = findViewById(R.id.btnPayment);
        
        //Call Data
        billDetail = (Bill) bundle.getSerializable("BillDetail");

        buildLayout(billDetail);
    }
    
    private void buildLayout(Bill result) {
        txtStatus.setText(result.getStatus());
        if(result.getStatus().contains("ã")) {
            txtStatus.setTextColor(Color.rgb(49, 183, 34));
            findViewById(R.id.btnPayment).setVisibility(View.GONE);
        }
        txtAmount.setText(String.format("%,.0f", result.getAmount()) + "");
        txtCreatedDate.setText(result.getCreatedDate());
        txtId.setText("Mã hóa đơn: " + result.getId());
//        btnPay.setVisibility(View.VISIBLE);

        brandServiceAdapter = new BillDetailAdapter(this, result.getBillDetails()); // layout at Adapter class
        listView.setAdapter(brandServiceAdapter);
    }

    public void clickToPay(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BillDetailActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Thanh toán");
        builder.setMessage("Tổng hóa đơn là: " + billDetail.getAmount() + ". Bạn có muốn thanh toán?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                BillRepository repository = new BillRepository();
                repository.paymentBill(currentUser.getId(), billDetail.getId(), new CallBackData<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(BillDetailActivity.this, "Payment Success", Toast.LENGTH_SHORT).show();
                        initialView();
                        Intent intent = new Intent(BillDetailActivity.this, HomeActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("CurrentUser", currentUser);
                        intent.putExtra("Bundle", bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(String msg) {
//                        Toast.makeText(BillDetailActivity.this, "Fail pay " + msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.show();

    }
}
