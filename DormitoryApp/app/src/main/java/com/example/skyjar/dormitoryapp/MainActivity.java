package com.example.skyjar.dormitoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.skyjar.dormitoryapp.Entities.LoginEntites.LoginResponse;
import com.example.skyjar.dormitoryapp.Entities.LoginEntites.LoginSessionService;
import com.example.skyjar.dormitoryapp.Entities.User;
import com.example.skyjar.dormitoryapp.Repositories.UserRepository;
import com.example.skyjar.dormitoryapp.utilsService.CallBackData;

public class MainActivity extends AppCompatActivity {
    EditText txtUsername;
    EditText txtPassword;
    UserRepository userRepository;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = findViewById(R.id.edtUsername);
        txtPassword = findViewById(R.id.edtPassword);
        progressBar.setVisibility(View.GONE);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle == null)
            checkLogin();
    }


    private void checkLogin() {


        LoginSessionService loginSession = new LoginSessionService(MainActivity.this);
        User currentUser = loginSession.getUserInfo();
        if (currentUser != null) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CurrentUser", currentUser);
            intent.putExtra("Bundle", bundle);
            startActivity(intent);
        }
    }

    public void clickToLogin(View view) {

        userRepository = new UserRepository();
        String username =  txtUsername.getText().toString();
        String passwrod =  txtPassword.getText().toString();
        userRepository.login(txtUsername.getText().toString(), txtPassword.getText().toString(),
                new CallBackData<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse response) {
                Toast.makeText(MainActivity.this, "cc", Toast.LENGTH_SHORT).show();
                getUserDetail(response.getAccess_token());
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(MainActivity.this, "Fail: " + msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getUserDetail(final String auth) {
        userRepository = new UserRepository();
        userRepository.getUserDetail(auth, new CallBackData<User>() {
            @Override
            public void onSuccess(User user) {
                LoginSessionService loginSession = new LoginSessionService(MainActivity.this);
                loginSession.insert(user);

                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentUser", user);
                intent.putExtra("Bundle", bundle);
                startActivity(intent);
            }

            @Override
            public void onFail(String msg) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
