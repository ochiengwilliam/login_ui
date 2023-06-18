package com.example.nlog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private Button button;
    ImageView twitter, google, facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);

        twitter = findViewById(R.id.twit);
        google = findViewById(R.id.go);
        facebook = findViewById(R.id.FaceBook);

        allClicklistener();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openMainActivity2();


            }
        });


        TextView Username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        MaterialButton loginbtn = findViewById(R.id.btnLogin);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Login Fail Please Try Again", Toast.LENGTH_SHORT).show();


            }
        });


    }

    public void openMainActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void allClicklistener() {
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  sharingToSocialMedia("com.twitter.android");

            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  sharingToSocialMedia("com.facebook.android");

            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharingToSocialMedia("com.google.android");

            }
        });


    }

    private void sharingToSocialMedia(String application) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/Plain");
        intent.putExtra(Intent.EXTRA_TEXT, "www.google,com");
        boolean installed = checkappInstall(application);

        if (installed) {

            intent.setPackage(application);
            startActivity(intent);

        } else {
            Toast.makeText(this, "Install app first", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean checkappInstall(String uri) {
        PackageManager pm = getPackageManager();

        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;

        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return false;
    }
}