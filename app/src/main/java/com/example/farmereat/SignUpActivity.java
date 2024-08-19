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

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameEditText, emailEditText, phoneEditText, passwordEditText, reTypePasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize EditText fields
        fullNameEditText = findViewById(R.id.full_name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        reTypePasswordEditText = findViewById(R.id.re_type_password);

        // Set onClickListener for the Continue button
        findViewById(R.id.signUp_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
    }

    private void performRegistration() {
        String fullName = fullNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String reTypePassword = reTypePasswordEditText.getText().toString().trim();

        if (password.equals(reTypePassword)) {
            // Create an instance of RegisterRequestModel with user input
            RegisterRequestModel requestModel = new RegisterRequestModel(fullName, email, phone, password);

            // Create the Retrofit instance and ApiService
            ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
            Call<RegisterResponseModel> call = apiService.registerUser(requestModel);

            // Execute the API call
            call.enqueue(new Callback<RegisterResponseModel>() {
                @Override
                public void onResponse(Call<RegisterResponseModel> call, Response<RegisterResponseModel> response) {
                    if (response.isSuccessful()) {
                        // Registration successful
                        Intent intent = new Intent(SignUpActivity.this, SignUpSuccessfulScreen.class);
                        startActivity(intent);
                        finish(); // Close the current activity
                    } else {
                        // Handle the error response
                        Toast.makeText(SignUpActivity.this, "Registration Failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                    // Handle the failure (e.g., network error)
                    Toast.makeText(SignUpActivity.this, "Registration Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Handle password mismatch
            Toast.makeText(SignUpActivity.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }
    }
}
