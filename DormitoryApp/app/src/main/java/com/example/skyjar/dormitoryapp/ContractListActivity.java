package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.ContractAdapter;
import com.example.skyjar.dormitoryapp.Entities.Contract;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.ContractRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.List;

public class ContractListActivity extends AppCompatActivity {
    Toolbar toolbar;
    ContractAdapter contractApdater;
    List<Contract> listContract;
    User currentUser = null;
    TextView txtNoContract;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_list);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtNoContract = (TextView) findViewById(R.id.txtNoContract);
        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

        ContractRepository repository = new ContractRepository();
        repository.getContracts(this, user.getId(), new CallBackData<List<Contract>>() {
            @Override
            public void onSuccess(List<Contract> contracts) {
                Toast.makeText(ContractListActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                if (contracts.size() == 0)
                    txtNoContract.setVisibility(View.VISIBLE);
                else
                    buildLayout(contracts);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ContractListActivity.this, "Get data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buildLayout(List<Contract> result) {

        //Render contract list
        ListView listView = findViewById(R.id.listContract);

        listContract = result;
        contractApdater = new ContractAdapter(this, R.layout.row_contract, listContract);



        listView.setAdapter(contractApdater);
    }

    public void clickToContractDetail(View view) {
        Intent intent = new Intent(this, ContractDetailActivity.class);
        Bundle bundle = new Bundle();
        int index = view.getId();
        boolean f = false;
        for (Contract b : listContract) {
            if (b.getContractId() == index){
                bundle.putSerializable("Contract", b);
                f = true;
            }
        }
        if(!f) bundle.putSerializable("Contract", null);
        bundle.putInt("ContractId", index);

        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }
}
