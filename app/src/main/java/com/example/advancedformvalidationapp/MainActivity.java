package com.example.advancedformvalidationapp;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText mail = (EditText) findViewById(R.id.mail);
        EditText phoneNum = (EditText) findViewById(R.id.phoneNum);
        EditText password = (EditText) findViewById(R.id.password);
        EditText passwordConf = (EditText) findViewById(R.id.passwordConf);

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstNameS = firstName.getText().toString();
                String lastNameS = lastName.getText().toString();
                String mailS = mail.getText().toString();
                String phoneNumS = phoneNum.getText().toString();
                String passwordS = password.getText().toString();
                String passwordConfS = passwordConf.getText().toString();
                int taskCount = 0;

                //First/Last-name validation
                if(!(firstNameS.isEmpty()) && !(lastNameS.isEmpty())){
                    Log.d("OK", firstNameS + " " + lastNameS);
                    taskCount++;
                } else {
                    if(firstNameS.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Fisrtname is empty", Toast.LENGTH_SHORT).show();
                    }
                    if(lastNameS.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Lastname is empty", Toast.LENGTH_SHORT).show();
                    }
                }

                //Mail validation
                if(Patterns.EMAIL_ADDRESS.matcher(mailS).matches()){
                    Log.d("OK", mailS);
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Error with mail validation", Toast.LENGTH_SHORT).show();
                }

                //Phone Number validation
                int num = 0;
                for(int i=0; i<phoneNumS.length(); i++){
                    num++;
                }
                if(num>=9){
                    Log.d("OK", String.valueOf(num));
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Phone number cannot be less then 9", Toast.LENGTH_SHORT).show();
                }

                //Password validation
                int pasNum = 0;
                for(int i=0; i<passwordS.length(); i++){
                    pasNum++;
                }
                if(pasNum>=6){
                    Log.d("OK", String.valueOf(pasNum));
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Password cannot be less then 6", Toast.LENGTH_SHORT).show();
                }

                //Password identify validation
                if(passwordS.equals(passwordConfS)){
                    Log.d("OK", passwordS+" "+passwordConfS);
                    taskCount++;
                } else {
                    Toast.makeText(getApplicationContext(), "Both passwords must be identical", Toast.LENGTH_SHORT).show();
                }

                if(taskCount==5){
                    Toast.makeText(getApplicationContext(), "Sent", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}