package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.Entities.ResponseUniType;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.TransactionRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

public class PersonalActivity extends AppCompatActivity {
    TextView txtFullname;
    TextView txtBirthdate;
    TextView txtPhone;
    TextView txtEmail;
    TextView txtSex;
    TextView txtAccountAmount;
    User currentUser = null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtFullname = findViewById(R.id.txtFullname);
        txtSex = findViewById(R.id.txtSex);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        txtBirthdate = findViewById(R.id.txtBirthdate);
        txtAccountAmount = findViewById(R.id.txtAccountAmount);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");

        User user = (User) bundle.getSerializable("CurrentUser");
        currentUser = user;

        txtFullname.setText(user.getFullname());
        txtBirthdate.setText(user.getBirthdate());
        txtEmail.setText(user.getEmail());
        txtPhone.setText(user.getPhone());
        txtSex.setText(user.getSex());
        

        TransactionRepository repository = new TransactionRepository();
        repository.getAccountAmount(this, currentUser.getId(), new CallBackData<ResponseUniType>() {
            @Override
            public void onSuccess(ResponseUniType responseUniType) {
                txtAccountAmount.setText(String.format("%,.0f",responseUniType.getData().getAccountItem().getAmount()) + " đồng");
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(PersonalActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}
