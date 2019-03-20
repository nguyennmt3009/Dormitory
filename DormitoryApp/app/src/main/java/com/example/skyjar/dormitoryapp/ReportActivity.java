package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.skyjar.dormitoryapp.CustomAdapters.ReportAdapter;
import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.Entities.ProblemReport;
import com.example.skyjar.dormitoryapp.Entities.Room;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.ReportRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    Toolbar toolbar;
    ReportAdapter reportAdapter;
    List<ProblemReport> listReport;
    User currentUser = null;
    Spinner spnApartment, spnStatus;
    ArrayAdapter<String> dataAdapter1;
    ArrayAdapter<String> dataAdapter2;
    List<ProblemReport> problemReportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Render bill list

        initView();
//        reportAdapter = new ReportAdapter(this, R.layout.row_report, listReport);

//        listView.setAdapter(reportAdapter);
    }

    public void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;
        final TextView txtNoReport = findViewById(R.id.txtNoReport);
        ReportRepository repository = new ReportRepository();
        repository.getReportList(this, user.getId(), new CallBackData<List<ProblemReport>>() {
            @Override
            public void onSuccess(List<ProblemReport> problemReports) {
                Toast.makeText(ReportActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                if (problemReports.size() == 0)
                    txtNoReport.setVisibility(View.VISIBLE);
                else
                    buildLayout(problemReports);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ReportActivity.this, "Get data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buildLayout(List<ProblemReport> problemReports) {
        ListView listView = findViewById(R.id.listReport);
        spnApartment = findViewById(R.id.spnApartment);
        spnStatus = findViewById(R.id.spnStatus);
//        listReport = filteredList(problemReports);
        listReport = problemReports;
//        setSpinnerAdapter(problemReports);
        reportAdapter = new ReportAdapter(this, R.layout.row_report, listReport);

//        listView.setAdapter(reportAdapter);
        setSpinnerAdapter(problemReports);

    }

    public void setSpinnerAdapter(List<ProblemReport> problemReports) {
        List<String> apartmentList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        boolean checkName = false;
        boolean checkStatus = false;
//        apartmentList.add("Tất cả");
//        statusList.add("Tất cả");
        for (ProblemReport pr : problemReports) {
            if (apartmentList != null) {
                for (String name : apartmentList) {
                    if (pr.getApartmentName().equalsIgnoreCase(name)) checkName = true;
                }
                if (!checkName) apartmentList.add(pr.getApartmentName()); else checkName = false;
            } else apartmentList.add(pr.getApartmentName());
            if (statusList != null) {
                for (String status : statusList) {
                    if (pr.getStatusName().equalsIgnoreCase(status)) checkStatus = true;
                }
                if (!checkStatus) statusList.add(pr.getStatusName()); else checkStatus = false;
            } else statusList.add(pr.getStatusName());

        }

        dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, apartmentList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnApartment.setAdapter(dataAdapter1);


        dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, statusList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStatus.setAdapter(dataAdapter2);

        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ReportActivity.this.reportAdapter.getFilter().filter(spnStatus.getSelectedItem().toString());
                ListView listView =(ListView) findViewById(R.id.listReport);
                listView.setAdapter(reportAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
//
//    public List<ProblemReport> filteredList(final List<ProblemReport> problemReports) {
//
//        setSpinnerAdapter(problemReports);
//
//        spnApartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (spnApartment.getSelectedItem().toString().equalsIgnoreCase("tất cả")
//                        && spnStatus.getSelectedItem().toString().equalsIgnoreCase("tất cả"))
//                    problemReportList = problemReports;
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                for (ProblemReport pr: problemReports) {
//                    if (pr.getApartmentName().equalsIgnoreCase(spnApartment.getSelectedItem().toString())
//                            && pr.getStatusName().equalsIgnoreCase(spnStatus.getSelectedItem().toString())){
//                        problemReportList.add(pr);
//                    }
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        return problemReportList;
//    }

    public void clickToCreateReport(View view) {
        Intent intent = new Intent(this, CreateReportActivity.class);
        startActivity(intent);
    }
}
