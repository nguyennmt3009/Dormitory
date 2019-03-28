package com.example.skyjar.dormitoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.Entities.Apartment;
import com.example.skyjar.dormitoryapp.Entities.Room;
import com.example.skyjar.dormitoryapp.Entities.Service;
import com.example.skyjar.dormitoryapp.Entities.ServiceItem;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.ReportRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;
import com.example.skyjar.dormitoryapp.utilsService.ClientApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class CreateReportActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spnApartment, spnRoom, spnService, spnServiceItem;
    private EditText edtDescription;
    private List<Apartment> apartmentList = new ArrayList<>();
    Toolbar toolbar;
    User currentUser = null;

    List<Room> roomList = new ArrayList<>();
    List<Service> serviceList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        spnApartment.setOnItemSelectedListener(this);

    }

    public void initView() {
        Bundle bundle = getIntent().getBundleExtra("Bundle");
        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

        spnApartment = (Spinner) findViewById(R.id.spnApartment);
        spnRoom = (Spinner) findViewById(R.id.spnRoom);
        spnService = (Spinner) findViewById(R.id.spnService);
        spnServiceItem = (Spinner) findViewById(R.id.spnServiceItem);

        ReportRepository repository = new ReportRepository();
        repository.getApartmentReport(this, user.getId(), new CallBackData<List<Apartment>>() {
            @Override
            public void onSuccess(List<Apartment> apartments) {
                apartmentList = apartments;
                showData(apartments);
                Toast.makeText(CreateReportActivity.this, "got data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(CreateReportActivity.this, "no data", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void showData(List<Apartment> apartments) {
        final List<String> listApartment = new ArrayList<String>();
        for (int i = 0; i < apartmentList.size(); i++) {
            listApartment.add(apartmentList.get(i).getName());
        }
//        listApartment.add("tesst");
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, listApartment);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spnApartment.setAdapter(dataAdapter);
        ArrayAdapter<Apartment> dataAdapter = new ArrayAdapter<Apartment>(this,
                android.R.layout.simple_spinner_item, apartmentList);
        spnApartment.setAdapter(dataAdapter);
    }

    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        final List<String> listRoom = new ArrayList<String>();
//        final List<String> listService = new ArrayList<String>();

        switch (parent.getId()) {
            case R.id.spnApartment:
                // Do stuff for spinner1
                showToast("Spinner1: position=" + position + ", Value= " + parent.getItemAtPosition(position));

                roomList = apartmentList.get(position).getRooms();
                serviceList = apartmentList.get(position).getServices();
                ArrayAdapter<Room> dataAdapter1 = new ArrayAdapter<Room>(parent.getContext(),
                        android.R.layout.simple_spinner_item, roomList);
                dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnRoom.setAdapter(dataAdapter1);

                ArrayAdapter<Service> dataAdapter2 = new ArrayAdapter<Service>(parent.getContext(),
                        android.R.layout.simple_spinner_item, serviceList);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnService.setAdapter(dataAdapter2);
                spnService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                        final List<String> listServiceItem = new ArrayList<String>();
                        List<ServiceItem> serviceItemList = serviceList.get(position).getServiceItems();
                        showToast("In switch-statement for spinner2. Value=" + parent.getItemAtPosition(position));

                        ArrayAdapter<ServiceItem> dataAdapter3 = new ArrayAdapter<ServiceItem>(parent.getContext(),
                                android.R.layout.simple_spinner_item, serviceItemList);
                        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnServiceItem.setAdapter(dataAdapter3);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            case R.id.spnService:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "not select", Toast.LENGTH_LONG).show();
    }

    public void clickToSendReport(View view) {
        int roomId = ((Room) spnRoom.getSelectedItem()).getId();
        int  serviceItemId = ((ServiceItem) spnServiceItem.getSelectedItem()).getServiceItemId();
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        String description  = edtDescription.getText().toString().trim();
        ReportRepository repository = new ReportRepository();
        repository.createReport(roomId, serviceItemId, description, new CallBackData<String>() {
            @Override
            public void onSuccess(String s) {
                showToast(s);
                edtDescription.setText("");
            }

            @Override
            public void onFail(String msg) {
                showToast(msg);
            }
        });
    }
}
