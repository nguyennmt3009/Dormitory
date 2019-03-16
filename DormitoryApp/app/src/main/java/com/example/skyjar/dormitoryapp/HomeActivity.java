package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.BillDetailAdapter;
import com.example.skyjar.dormitoryapp.Entities.BillDetail;
import com.example.skyjar.dormitoryapp.Entities.BrandService;
import com.example.skyjar.dormitoryapp.Repositories.BillRepository;
import com.example.skyjar.dormitoryapp.Repositories.BillRepositoryImplement;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BillDetailAdapter billDetailAdapter;
    List<BillDetail> listBills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Trang Chủ");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initialView();

    }

    private void initialView(){
        BillRepository repository = new BillRepositoryImplement();

        repository.getBills(this, new CallBackData<List<BillDetail>>() {
            @Override
            public void onSuccess(List<BillDetail> billDetails) {
                Toast.makeText(HomeActivity.this, "Get data successful", Toast.LENGTH_SHORT).show();
                buildLayout(billDetails);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HomeActivity.this, "Get data failure: " + msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void buildLayout(List<BillDetail> result) {
        //Render bill list
        ListView listView = findViewById(R.id.listBill);

        listBills = result;
        billDetailAdapter = new BillDetailAdapter(this, R.layout.row_bill_detail, listBills);

        listView.setAdapter(billDetailAdapter);
    }

    public void clickBillDetail(View view) {
        Intent intent = new Intent(this, BillDetailActivity.class);
        Bundle bundle = new Bundle();
        int index = view.getId();
        bundle.putInt("BillDetailId", index);
        bundle.putSerializable("BillDetail", listBills.get(0));
        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_report) {
            intent = new Intent(this, CreateReportActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_contract) {

        } else if (id == R.id.nav_personal) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_change_password) {

        } else if (id == R.id.nav_logout) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
