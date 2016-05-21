package com.example.rak.kappa;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.lang.*;

public class MainActivity extends AppCompatActivity
        {
Button button;
            EditText login;
            EditText password;

            public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        button = (Button)findViewById(R.id.enter_button);
        login = (EditText)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JSONObject jo = new JSONObject();
                try {
                    jo.put("login", String.valueOf(login.getText()));
                    jo.put("pwd", String.valueOf(password.getText()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String response = null;
                try {
                    response = Conn.getResponce(jo, "http://test.egeshki.ru/canteen/main/log_in");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    id = Integer.parseInt(response);

                    if (id == 0) {
                        Toast.makeText(MainActivity.this, "Неверные данные ввода", Toast.LENGTH_LONG).show();
                    } else {

                        Intent intent = new Intent(MainActivity.this, FragmentActivity.class);

                        MainActivity.this.startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Отсутствует соединение с сервером", Toast.LENGTH_LONG).show();
                }
            }

        });
    }




}
