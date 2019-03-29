package com.example.skyjar.dormitoryapp;

import android.app.DatePickerDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txtNoTransaction;
    List<Transaction.Trans> listTransaction;
    User currentUser = null;
    TransactionAdapter adapter;
    SwipeRefreshLayout pullToRefresh;
    TextView txtFromDate, txtToDate;
    DatePickerDialog datePickerDialog;
    TransactionRepository repository = new TransactionRepository();
    ListView lstTransaction;

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

        txtFromDate = (TextView) findViewById(R.id.txtFromDate);
        txtToDate = (TextView) findViewById(R.id.txtToDate);
        lstTransaction = (ListView) findViewById(R.id.listTransaction);

        txtFromDate.setText((new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime()));
        txtToDate.setText((new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime()));

        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initView();
                pullToRefresh.setRefreshing(false);
            }
        });
        onclickDatepicker();
        txtNoTransaction = (TextView) findViewById(R.id.txtNoTransaction);
        initView();
    }

    public void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;
        repository.getTransactionHistory(this, user.getId(), txtFromDate.getText().toString().trim(),
                txtToDate.getText().toString().trim(), new CallBackData<List<Transaction.Trans>>() {
                    @Override
                    public void onSuccess(List<Transaction.Trans> transactions) {
//                        Toast.makeText(TransactionActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                        if (transactions.size() == 0) {
                            txtNoTransaction.setVisibility(View.VISIBLE);
                            listTransaction.clear();
//                            buildLayout(null);
                        } else {
                            txtNoTransaction.setVisibility(View.GONE);
                            buildLayout(transactions);
                        }
                    }

                    @Override
                    public void onFail(String msg) {
//                        Toast.makeText(TransactionActivity.this, "Get data Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void onclickDatepicker() {
        txtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TransactionActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        txtFromDate.setText(Date(date, "dd/MM/yyyy"));
                        initView();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        txtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(TransactionActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                        txtToDate.setText(Date(date, "dd/MM/yyyy"));
                        initView();
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private String Date(String mess, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy");
            Date tempDate = simpleDateFormat.parse(mess);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(pattern);
            return outputDateFormat.format(tempDate);
        } catch (Exception e) {
        }
        return mess;
    }

    private void buildLayout(List<Transaction.Trans> transactions) {
        ListView listView = findViewById(R.id.listTransaction);

        listTransaction = transactions;
        adapter = new TransactionAdapter(this, R.layout.row_transaction, listTransaction);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
