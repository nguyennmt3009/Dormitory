package com.example.skyjar.dormitoryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.UserRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

public class ChangePasswordActivity extends AppCompatActivity {
    Toolbar toolbar;
    User currentUser = null;
    EditText edtOldPass;
    EditText edtNewPass;
    EditText edtNewPassAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = findViewById(R.id.toolbar_choose_table);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtOldPass = findViewById(R.id.edtOldPassword);
        edtNewPass = findViewById(R.id.edtNewPassword);
        edtNewPassAgain = findViewById(R.id.edtNewPasswordAgain);

    }

    public void clickToChangePass(View view) {
        String oldPass = edtOldPass.getText().toString();
        String newPass = edtNewPass.getText().toString();
        String newPassAgain = edtNewPassAgain.getText().toString();
        if (newPass != newPassAgain){
            edtNewPassAgain.setError("Nhập sai!");
        } else {
            UserRepository repository = new UserRepository();
            repository.changePassword(oldPass, newPass, new CallBackData<Object>() {
                @Override
                public void onSuccess(Object o) {
                    Toast.makeText(ChangePasswordActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFail(String msg) {
                    Toast.makeText(ChangePasswordActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
