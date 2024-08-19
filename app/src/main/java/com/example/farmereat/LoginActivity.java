package com.example.farmereat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize EditText fields
        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);

        // Set onClickListener for the Login button
        findViewById(R.id.loginScreen_loginBtn).setOnClickListener(v -> performLogin());
    }

    private void performLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Create an instance of LoginRequestModel with user input
        LoginRequestModel requestModel = new LoginRequestModel(email, password);

        // Create the Retrofit instance and ApiService
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<LoginResponseModel> call = apiService.loginUser(requestModel);

        // Execute the API call
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    // Handle the successful response
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                    // Navigate to LoginSuccessfulScreen
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessfulScreen.class);
                    startActivity(intent);
                    finish();  // Optional: to close this activity
                } else {
                    // Handle the error response
                    Toast.makeText(LoginActivity.this, "Login Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                // Handle the failure (e.g., network error)
                Toast.makeText(LoginActivity.this, "Login Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
