package com.example.minim2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText usr;
    private EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button buttonLogin = findViewById(R.id.login_button);
        buttonLogin.setOnClickListener(this);
        usr = findViewById(R.id.user_edit);
        pwd = findViewById(R.id.pswd_edit);

    }
    @Override
    public void onClick(View v) {
        String savedUser = readSharedPreferences("user");
        String savedPwd = readSharedPreferences("password");

        if((savedUser.equals("user"))&&(savedPwd.equals("dsamola"))){
            Intent intentPanel = new Intent(this,PanelActivity.class);
            startActivity(intentPanel);
        }
        final String username = usr.getText().toString();
        final String userpwd = pwd.getText().toString();
        
        if((username.equals("user"))&&(userpwd.equals("dsamola"))){
            saveLoginSharedPreferences(username,userpwd);
            Intent intentPanel = new Intent(this,PanelActivity.class);
            startActivity(intentPanel);
        }
        else
            Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_SHORT).show();
    }

    private String readSharedPreferences(String clave) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(clave,"");
    }

    private void saveLoginSharedPreferences(String username, String userpwd) {

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user", username);
        editor.putString("password", userpwd);
        editor.apply();

    }
}
