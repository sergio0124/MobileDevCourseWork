package com.travelagencycoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.travelagencycoursework.database.fireboxlogic.UserFirebaseLogic;
import com.travelagencycoursework.database.logics.UserLogic;
import com.travelagencycoursework.database.models.UserModel;

public class RegistrationActivity extends AppCompatActivity {

    Button buttonRegister;
    EditText editTextPassword;
    EditText editTextPasswordRepeat;
    EditText editTextEmail;
    CheckBox shareDataCheckBox;

    UserLogic logic;
    UserFirebaseLogic firebaseLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.mail);
        editTextPassword = findViewById(R.id.password);
        editTextPasswordRepeat = findViewById(R.id.passwordrepeat);
        shareDataCheckBox = findViewById(R.id.shareDataCheckBox);

        logic = new UserLogic(this);
        firebaseLogic = new UserFirebaseLogic();
    }

    public void buttonRegister_Click(View view) {
        if (!editTextPassword.getText().toString()
                .equals(editTextPasswordRepeat.getText().toString())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Пароли не совпадают");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "ОК",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        if (!shareDataCheckBox.isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("тредуется согласие на обработку данных");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "ОК",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        UserModel model = new UserModel();
        model.setLogin(editTextEmail.getText().toString());
        model.setPassword(editTextPassword.getText().toString());

        logic.open();

        UserModel user = logic.getElement(model);
        if (user!=null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
            builder.setMessage("Такой логин уже зарегистрирован");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "ОК",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        logic.insert(model);
        logic.close();
        firebaseLogic.syncUsers(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(RegistrationActivity.this);
        builder.setMessage("Регистрация прошла успешно");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "ОК",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert = builder.create();
        alert.show();

        Intent intent = new Intent(RegistrationActivity.this, EnterActivity.class);
        startActivity(intent);
    }
}