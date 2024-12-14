package com.example.homework_2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editEmail = findViewById(R.id.edit_email);
        EditText editPassword = findViewById(R.id.edit_password);
        Button button = findViewById(R.id.button);
        LinearLayout mainLayout = findViewById(R.id.container);

        String correctEmail = "admin";
        int minPasswordLength = 6;

//   

        editEmail.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!editEmail.getText().toString().isEmpty()) {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.orange));
                } else {
                    button.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textEmail = editEmail.getText().toString();
                String textPassword = editPassword.getText().toString();

                if (textPassword.length() < minPasswordLength) {
                    Snackbar.make(mainLayout, "Пароль должен содержать не менее 6 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(textEmail.equals(correctEmail) && textPassword.length() >= minPasswordLength){
                    Snackbar.make(mainLayout, "Вы успешно зарегистрировались", Snackbar.LENGTH_SHORT).show();

                    mainLayout.removeAllViews();
                    findViewById(R.id.text_welcome).setVisibility(View.VISIBLE);
                    findViewById(R.id.view_text).setVisibility(View.INVISIBLE);
                }else{
                    Snackbar.make(mainLayout, "Неправильный логин и пароль", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }
}