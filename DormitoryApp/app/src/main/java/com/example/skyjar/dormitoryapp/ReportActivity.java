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
import com.example.skyjar.dormitoryapp.Entities.ReportStatus;
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
    ArrayAdapter<Apartment> dataAdapter1;
    ArrayAdapter<ReportStatus> dataAdapter2;
    List<ProblemReport> problemReportList;
    TextView txtNoReport;

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
    }

    public void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;
        txtNoReport = findViewById(R.id.txtNoReport);
        ReportRepository repository = new ReportRepository();
        repository.getReportList(this, user.getId(), new CallBackData<List<ProblemReport>>() {
            @Override
            public void onSuccess(List<ProblemReport> problemReports) {
                Toast.makeText(ReportActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                if (problemReports.size() == 0)
                    txtNoReport.setVisibility(View.VISIBLE);
                else {
                    setSpinnerAdapter(problemReports);
                }

            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(ReportActivity.this, "Get data failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buildLayout(List<ProblemReport> problemReports) {
        findViewById(R.id.txtNoReport).setVisibility(View.GONE);
        ListView listView = findViewById(R.id.listReport);
        listReport = problemReports;
        if (listReport.size() == 0)
            findViewById(R.id.txtNoReport).setVisibility(View.VISIBLE);
        reportAdapter = new ReportAdapter(this, R.layout.row_report, listReport);
//        setSpinnerAdapter(problemReports);
        listView.setAdapter(reportAdapter);


    }

    public void setSpinnerAdapter(final List<ProblemReport> problemReports) {
        final List<Apartment> apartmentList = new ArrayList<>();
        final List<ReportStatus> statusList = new ArrayList<>();
        boolean checkName = false;

        //add data for apartment
        apartmentList.add(new Apartment(-1,"Tất cả",null,null));
        for (ProblemReport pr : problemReports) {
            if (apartmentList != null) {
                for (Apartment a : apartmentList) {
                    if (pr.getApartment().getId() == a.getId()) checkName = true;
                }
                if (!checkName) apartmentList.add(pr.getApartment()); else checkName = false;
            } else apartmentList.add(pr.getApartment());
        }
        //add data for status
        statusList.add(new ReportStatus(-1, "Tất cả"));
        statusList.add(new ReportStatus(1, "Chờ nhân xử lý"));
        statusList.add(new ReportStatus(2, "Đang xử lý"));
        statusList.add(new ReportStatus(3, "Hoàn thành"));
        statusList.add(new ReportStatus(4, "Hủy bỏ"));
        statusList.add(new ReportStatus(5, "Tạo mới"));
        statusList.add(new ReportStatus(6, "Chờ hủy"));
        statusList.add(new ReportStatus(7, "Chờ xác nhân hoàn thành"));

        //init spinner
        spnApartment = findViewById(R.id.spnApartment);
        spnStatus = findViewById(R.id.spnStatus);

        dataAdapter1 = new ArrayAdapter<Apartment>(this,
                android.R.layout.simple_spinner_item, apartmentList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnApartment.setAdapter(dataAdapter1);


        dataAdapter2 = new ArrayAdapter<ReportStatus>(this,
                android.R.layout.simple_spinner_item, statusList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnStatus.setAdapter(dataAdapter2);

        AdapterView.OnItemSelectedListener myListener=new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Apartment apartment = (Apartment) spnApartment.getSelectedItem();
                ReportStatus reportStatus =(ReportStatus) spnStatus.getSelectedItem();
                switch (parent.getId()) {
                    case R.id.spnApartment:
                        apartment = apartmentList.get(position);
                        if (apartment.getId() == -1 && reportStatus.getId() == -1) buildLayout(problemReports);
                            else if (apartment.getId() == -1) buildLayout(filter(problemReports,reportStatus));
                                else if (reportStatus.getId() == -1) buildLayout(filter(problemReports,apartment));
                                    else buildLayout(filter(problemReports,apartment,reportStatus));
                        break;
                    case R.id.spnStatus:
                        reportStatus = statusList.get(position);
                        if (apartment.getId() == -1 && reportStatus.getId() == -1) buildLayout(problemReports);
                            else if (apartment.getId() == -1) buildLayout(filter(problemReports,reportStatus));
                                else if (reportStatus.getId() == -1) buildLayout(filter(problemReports,apartment));
                                    else buildLayout(filter(problemReports,apartment,reportStatus));
                                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spnApartment.setOnItemSelectedListener(myListener);
        spnStatus.setOnItemSelectedListener(myListener);

    }

//    public  void filter(final List<Apartment>apartmentList, final List<ReportStatus> statusList, final List<ProblemReport> problemReports) {
//        problemReportList = new ArrayList<>();
//        spnApartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                problemReportList = null;
//                if (apartmentList.get(position).getId() == -1)
//                    spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            if (statusList.get(position).getId() == -1)
//                                buildLayout(problemReports);
//                            else
//                            {
//                                for (ProblemReport pr: problemReports) {
//                                    if (pr.getStatus().getId() == (statusList.get(position).getId()))
//                                        problemReportList.add(pr);
//                                }
//                                buildLayout(problemReportList);
//                            }
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                else
//                    spnStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                            problemReportList = null;
//                            if (statusList.get(position).getId() == -1)
//                                for (ProblemReport pr: problemReports) {
//                                    if (pr.getApartment().getId() == (apartmentList.get(position).getId())
//                                            && pr.getStatus().getId() == (statusList.get(position).getId()))
//                                        problemReportList.add(pr);
//                                }
//                            else
//                            {
//                                for (ProblemReport pr: problemReports) {
//                                    if (pr.getApartment().getId() == (apartmentList.get(position).getId()))
//                                        problemReportList.add(pr);
//                                }
//                            }
//
//                            buildLayout(problemReportList);
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parent) {
//
//                        }
//                    });
//                for (ProblemReport pr: problemReports) {
//                    if (pr.getApartment().getId() == (apartmentList.get(position).getId())
//                            && pr.getStatus().getId() == statusList.get(position).getId()) problemReportList.add(pr);
//                }
//                buildLayout(problemReports);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }

    public List<ProblemReport> filter(List<ProblemReport> problemReports,Apartment a, ReportStatus rs) {
        List<ProblemReport> list = new ArrayList<>();
        for (ProblemReport p: problemReports) {
            if (a.getId() == p.getApartment().getId()
                    && rs.getId() == p.getStatus().getId()) list.add(p);
        }
        return list;
    }

    public List<ProblemReport> filter(List<ProblemReport> problemReports,Apartment a) {
        List<ProblemReport> list = new ArrayList<>();
        for (ProblemReport p: problemReports) {
            if (a.getId() == p.getApartment().getId()) list.add(p);
        }
        return list;
    }

    public List<ProblemReport> filter(List<ProblemReport> problemReports,ReportStatus rs) {
        List<ProblemReport> list = new ArrayList<>();
        for (ProblemReport p: problemReports) {
            if (rs.getId() == p.getStatus().getId()) list.add(p);
        }
        return list;
    }
    public void clickToCreateReport(View view) {
        Intent intent = new Intent(this, CreateReportActivity.class);
        startActivity(intent);
    }
}
