package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.travelagencycoursework.R;
import com.travelagencycoursework.database.logics.UserLogic;
import com.travelagencycoursework.database.models.UserModel;

import java.util.List;

public class EnterActivity extends AppCompatActivity {

    TextView button_to_register_activity;
    Button button_enter;
    EditText editTextLogin;
    EditText editTextPassword;
    UserLogic logic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        button_to_register_activity = findViewById(R.id.textView);
        button_enter = findViewById(R.id.buttonEntry);
        editTextLogin = findViewById(R.id.login);
        editTextPassword = findViewById(R.id.password);

        button_enter.setOnClickListener(v->{
            UserModel model = new UserModel();
            model.setLogin(editTextLogin.getText().toString());
            model.setPassword(editTextPassword.getText().toString());
            logic.open();
            UserModel user = logic.getElement(model);
            if (user!=null && user.getLogin().equals(model.getLogin()) && user.getPassword().equals(model.getPassword())) {
                logic.close();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("userId", user.getId());
                intent.putExtra("userLogin", user.getLogin());
                startActivity(intent);
                return;
            }
            logic.close();
            AlertDialog.Builder builder = new AlertDialog.Builder(EnterActivity.this);
            builder.setMessage("Пароль введен неверно или такой логин не зарегистрирован");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "ОК",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
        });

        logic = new UserLogic(this);
    }

    public void textViewRegistration_Click(View view) {
        Intent intent = new Intent(EnterActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}