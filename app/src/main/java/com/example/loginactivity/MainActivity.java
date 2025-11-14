package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText username, pwd;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String userpwd = pwd.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!isValidPassword(userpwd)) {
                    Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, Signin.class);
                intent.putExtra("email", email);
                intent.putExtra("userpwd", userpwd);
                startActivity(intent);
            }
        });
    }

    // Password validation rules
    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern special = Pattern.compile("^.*[@#$%^&*(){}.,;:/].*$");

    private boolean isValidPassword(String userpwd) {
        if (userpwd.length() < 8) return false;
        if (!lowercase.matcher(userpwd).matches()) return false;
        if (!uppercase.matcher(userpwd).matches()) return false;
        if (!number.matcher(userpwd).matches()) return false;
        if (!special.matcher(userpwd).matches()) return false;

        return true;
    }
}
