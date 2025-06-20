package com.example.careerpath;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.EditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent; // tambahkan ini

import androidx.constraintlayout.widget.ConstraintLayout;

public class LoginActivity extends Activity {

    private EditText email, password;
    private ImageView ivShowPass;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // SET WARNA "Welcome Back!" DI SINI
        TextView welcomeTitle = findViewById(R.id.welcome_title);
        String text = "<font color='#0056D6'><b>Welcome</b></font> <font color='#8E98A7'><b>Back!</b></font>";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            welcomeTitle.setText(android.text.Html.fromHtml(text, android.text.Html.FROM_HTML_MODE_LEGACY));
        } else {
            welcomeTitle.setText(android.text.Html.fromHtml(text));
        }

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        ivShowPass = findViewById(R.id.iv_show_pass);

        ivShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    password.setInputType(129); // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD
                    ivShowPass.setImageResource(R.drawable.ic_eye);
                } else {
                    password.setInputType(145); // TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    ivShowPass.setImageResource(R.drawable.ic_eye_off);
                }
                isPasswordVisible = !isPasswordVisible;
                password.setSelection(password.getText().length());
            }
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser = email.getText().toString().trim();
                String inputPass = password.getText().toString().trim();

                // Hardcode username dan password
                if(inputUser.equals("user123") && inputPass.equals("pass123")) {
                    // Login berhasil, masuk ke dashboard
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Login gagal
                    Toast.makeText(LoginActivity.this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayout btnGoogle = findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Google SignIn Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}