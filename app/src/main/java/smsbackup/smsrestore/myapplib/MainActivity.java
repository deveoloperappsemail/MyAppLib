package smsbackup.smsrestore.myapplib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import smsbackup.smsrestore.myapplication.LiveAds;
import smsbackup.smsrestore.myapplication.TestAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiveAds.getLiveAds(this, getPackageName());
        TestAds.getTestAds(this);
    }
}