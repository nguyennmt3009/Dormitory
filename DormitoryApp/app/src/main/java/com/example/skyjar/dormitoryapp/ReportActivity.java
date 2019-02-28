package com.example.skyjar.dormitoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.skyjar.dormitoryapp.CustomAdapters.ReportAdapter;
import com.example.skyjar.dormitoryapp.Entities.ProblemReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("Báo cáo sự cố");

        //Render bill list
        ListView listView = findViewById(R.id.listReport);

        List<ProblemReport> listReport = new ArrayList<ProblemReport>();
        listReport.add(new ProblemReport("Phòng 205 - Chung cư Hưng Ngân"
                , "Sự cố internet", "Lorem ipsum", false, new Date(), null));

        reportAdapter = new ReportAdapter(this, R.layout.report_row, listReport);

        listView.setAdapter(reportAdapter);
    }
}
