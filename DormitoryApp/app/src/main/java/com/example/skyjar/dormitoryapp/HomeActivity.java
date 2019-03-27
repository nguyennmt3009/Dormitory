package com.example.skyjar.dormitoryapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.CustomAdapters.BillAdapter;
import com.example.skyjar.dormitoryapp.Entities.Bill;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.BillRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BillAdapter billDetailAdapter;
    List<Bill> listBills;
    TextView txtWelcome;
    TextView txtStatusNull;
    User currentUser = null;
    RadioGroup rdGroup;
    TextView txtUserMenu;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Trang Chủ");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtWelcome = findViewById(R.id.txtWelcome);
        txtStatusNull = findViewById(R.id.txtStatusNull);
        txtUserMenu = findViewById(R.id.txtMenuTitle);
        progressBar = findViewById(R.id.progressBar);

        initialView();

    }




    private void initialView(){
        Bundle bundle = getIntent().getBundleExtra("Bundle");

        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

        txtWelcome.setText("Xin chào, " + user.getFullname());

        BillRepository repository = new BillRepository();
        repository.getBills(this, "status=false", user.getId(), new CallBackData<List<Bill>>() {
            @Override
            public void onSuccess(List<Bill> billDetails) {
                if (billDetails.size() == 0)
                    txtStatusNull.setVisibility(View.VISIBLE);
                else
                    buildLayout(billDetails);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HomeActivity.this, "Get data failure: " + msg, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void buildLayout(List<Bill> result) {

        //Render bill list
        ListView listView = findViewById(R.id.listBill);

        listBills = result;
        billDetailAdapter = new BillAdapter(this, R.layout.row_bill_detail, listBills);

        listView.setAdapter(billDetailAdapter);
        progressBar.setVisibility(View.GONE);

    }

    public void clickBillDetail(View view) {
        Intent intent = new Intent(this, BillDetailActivity.class);
        Bundle bundle = new Bundle();
        int index = view.getId();
        boolean f = false;
        for (Bill b : listBills) {
            if (b.getId() == index){
                bundle.putSerializable("BillDetail", b);
                bundle.putSerializable("CurrentUser", currentUser);
                f = true;
            }
        }
        if(!f) bundle.putSerializable("BillDetail", null);
        bundle.putInt("BillDetailId", index);

        intent.putExtra("Bundle", bundle);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
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
            Toast.makeText(this, "Noti click", Toast.LENGTH_SHORT).show();
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
        Bundle bundle;

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_history) {
            intent = new Intent(HomeActivity.this, TransactionActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_report) {
            intent = new Intent(HomeActivity.this, ReportActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_contract) {
            intent = new Intent(HomeActivity.this, ContractListActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_personal) {
            intent = new Intent(this, PersonalActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_change_password) {
            intent = new Intent(this, ChangePasswordActivity.class);
            bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            intent = new Intent(this, MainActivity.class);
            bundle = new Bundle();
            bundle.putInt("Logout", 1);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Dialog dialog;

    public void clickToSortBill(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_info);

        dialog.show();

        TextView btnDismiss = dialog.findViewById(R.id.dlDismiss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnFilter = dialog.findViewById(R.id.btnFilter);
        rdGroup = dialog.findViewById(R.id.rdGroupPayment);

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checked = rdGroup.getCheckedRadioButtonId();
                RadioButton chosenOne = dialog.findViewById(checked);

                if (chosenOne.getText().toString().contains("ã"))
                    filter(1);
                else if (chosenOne.getText().toString().contains("ưa"))
                    filter(2);
                else
                    filter(3);

                dialog.dismiss();
            }
        });

    }

    private void filter(int sortValue) {
        progressBar.setVisibility(View.VISIBLE);
        String sortBy = "null";


        switch (sortValue) {
            case 1:
                sortBy = "status=true";
                break;
            case 2:
                sortBy = "status=false";
                break;
            default:
                break;
        }

        txtStatusNull.setVisibility(View.GONE);
//
        BillRepository repository = new BillRepository();
        repository.getBills(this, sortBy, currentUser.getId(), new CallBackData<List<Bill>>() {
            @Override
            public void onSuccess(List<Bill> billDetails) {
                if (billDetails.size() == 0)
                    txtStatusNull.setVisibility(View.VISIBLE);
                else
                    buildLayout(billDetails);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(HomeActivity.this, "Get data failure: " + msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
