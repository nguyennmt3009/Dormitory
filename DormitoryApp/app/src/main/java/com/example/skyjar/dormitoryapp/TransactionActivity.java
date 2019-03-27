package com.example.skyjar.dormitoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.TransactionAdapter;
import com.example.skyjar.dormitoryapp.Entities.Contract;
import com.example.skyjar.dormitoryapp.Entities.Transaction;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.TransactionRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoTransaction;
    List<Transaction.Trans> listTransaction;
    User currentUser = null;
    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtNoTransaction = (TextView) findViewById(R.id.txtNoTransaction);
        initView();
    }

    public void initView(){
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

//        Date fromDate = this.getDate("1/1/2019", "dd/MM/yyyy");
//        Date toDate = this.getDate("12/12/2019", "dd/MM/yyyy");

//        Date fromDate = new Date(2019,1,1);
//        Date toDate = new Date(2019,12,12);
//        Dat
//        String fromDate
        TransactionRepository repository = new TransactionRepository();
        repository.getTransactionHistory(this, user.getId(), "01/01/2019", "12/12/2019" , new CallBackData<List<Transaction.Trans>>() {
            @Override
            public void onSuccess(List<Transaction.Trans> transactions) {
                Toast.makeText(TransactionActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                if (transactions.size() == 0)
                    txtNoTransaction.setVisibility(View.VISIBLE);
                else
                    buildLayout(transactions);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(TransactionActivity.this, "Get data Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private Date getDate(String mess, String pattern)
    {
        try
        {
            DateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);

            return df.parse(mess);
        } catch (Exception e)
        {
        }
        return new Date(mess);
    }

    private void buildLayout(List<Transaction.Trans> transactions) {
        ListView listView = findViewById(R.id.listTransaction);

        listTransaction = transactions;
        adapter = new TransactionAdapter(this, R.layout.row_transaction, listTransaction);

        listView.setAdapter(adapter);
    }
}
