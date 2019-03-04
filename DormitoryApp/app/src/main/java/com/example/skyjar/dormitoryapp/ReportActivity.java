package com.example.skyjar.dormitoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;


import com.example.skyjar.dormitoryapp.CustomAdapters.ReportAdapter;
import com.example.skyjar.dormitoryapp.Entities.ProblemReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    ReportAdapter reportAdapter;
    Toolbar toolbar;

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
        ListView listView = findViewById(R.id.listReport);

        List<ProblemReport> listReport = new ArrayList<ProblemReport>();
        listReport.add(new ProblemReport("Phòng 205 - Chung cư Hưng Ngân"
                , "Sự cố internet", "Lorem ipsum", false, new Date(), null));

        listReport.add(new ProblemReport("Phòng 209 - Chung cư Hưng Ngân"
                , "Sự cố internet", "Lorem ipsum", false, new Date(), null));

        reportAdapter = new ReportAdapter(this, R.layout.row_report, listReport);

        listView.setAdapter(reportAdapter);
    }
}
