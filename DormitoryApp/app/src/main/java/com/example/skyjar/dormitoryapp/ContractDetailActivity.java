package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.skyjar.dormitoryapp.CustomAdapters.ContractDetailAdapter;
import com.example.skyjar.dormitoryapp.Entities.Contract;

import java.util.List;

public class ContractDetailActivity extends AppCompatActivity {
    TextView txtContractId;
    TextView txtBrandName;
    TextView txtApartment;
    TextView txtRoom;
    TextView txtOwner;
    TextView txtListName;
    TextView txtDate;
    TextView txtDueDate;
    TextView txtDeposit;
    TextView txtMonthlyFee;
    Contract contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_choose_table);
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

        txtRoom = findViewById(R.id.txtRoom);
        txtDueDate = findViewById(R.id.txtDueDate);
        txtOwner = findViewById(R.id.txtOwner);
        txtApartment = findViewById(R.id.txtApartment);
        txtContractId = findViewById(R.id.txtContractId);
        txtBrandName = findViewById(R.id.txtBrandName);
        txtListName = findViewById(R.id.txtListName);
        txtDate = findViewById(R.id.txtDate);
        txtDeposit = findViewById(R.id.txtDeposit);
        txtMonthlyFee = findViewById(R.id.txtMonthlyFee);

        //Call Data
        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        contract = (Contract) bundle.getSerializable("Contract");

        buildLayout(contract);
    }

    private void buildLayout(Contract contract) {
        txtRoom.setText(contract.getRoomName());
        txtDueDate.setText(contract.getDueDate());
        txtOwner.setText(contract.getOwnerName());
        txtApartment.setText(contract.getApartmentName());
        txtContractId.setText("Mã số hợp đồng: " + contract.getContractId());
        txtBrandName.setText(contract.getBrandName());
        List<String> strArr = contract.getListCustomerName();
        String str = "";
        for (int i = 0; i < strArr.size(); i++) {
            if (i != strArr.size() - 1)
                str += ("\t\t\t" + (i + 1) + "\t\t\t" + strArr.get(i) + "\n");
            else str += ("\t\t\t" + (i + 1) + "\t\t\t" + strArr.get(i));
        }
        txtListName.setText(str);
        txtDate.setText(contract.getCreateDate());
        txtDeposit.setText(contract.getDeposit());
        txtMonthlyFee.setText(contract.getMonthlyFee());
    }
}
