package com.example.alexey.weatherinformer.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.alexey.weatherinformer.R;
import com.example.alexey.weatherinformer.fragments.ListWeatherFragment;
import com.example.alexey.weatherinformer.fragments.WeatherInfoFragment;
import com.example.alexey.weatherinformer.models.WeatherObject;

public class MainActivity extends AppCompatActivity implements ListWeatherFragment.OnCitySelectedListener{

    public static final String TAG = MainActivity.class.getName();

    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.containerFragment);
        if(fragment == null) {
            fragment = new ListWeatherFragment().newInstance();
            mFragmentManager.beginTransaction()
                    .add(R.id.containerFragment, fragment)
                    .commit();
        }
    }

    @Override
    public void onSelectCity(WeatherObject weatherObject) {
        mFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, WeatherInfoFragment.newInstance(weatherObject))
                .addToBackStack(null)
                .commit();
    }
}
