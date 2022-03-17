package smsbackup.smsrestore.myapplib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import smsbackup.smsrestore.myapplication.InHouseAds;
import smsbackup.smsrestore.myapplication.LiveAds;
import smsbackup.smsrestore.myapplication.TestAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        LiveAds.getLiveAds(this, getPackageName());
        TestAds.getTestAds(this);

        InHouseAds.getInHouseAds(this);
    }

    public void Test(View view) {
        Toast.makeText(this, "" + InHouseAds.getModelAdsList().size(), Toast.LENGTH_SHORT).show();
    }
}