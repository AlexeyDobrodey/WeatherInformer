package com.example.alexey.weatherinformer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexey.weatherinformer.OpenWeatherController;
import com.example.alexey.weatherinformer.R;
import com.example.alexey.weatherinformer.models.WeatherObject;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WeatherInfoFragment extends Fragment {

    @BindView(R.id.nameCityTxtView) TextView mNameCity;

    @BindView(R.id.imgStateWeather) ImageView mImgStateWeather;

    @BindView(R.id.generalTempTxtView) TextView mTempTxtView;

    @BindView(R.id.minTempTxtView) TextView mMinTempTxtView;

    @BindView(R.id.maxTempTxtView) TextView mMaxTempTxtView;

    @BindView(R.id.pressureTxtView) TextView mPressureTxtView;

    @BindView(R.id.humidityTxtView) TextView mHunidityTxtView;

    private Unbinder mUnbinder;

    private static final String ARG_WEATHER_OBJECT = "ARGS_WEATHER_OBJECT";
    private WeatherObject mWeatherObject = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mWeatherObject = (WeatherObject)getArguments().getSerializable(ARG_WEATHER_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_weather, container, false);

        mUnbinder = ButterKnife.bind(this, view);

        mNameCity.setText(mWeatherObject.getName());
        mTempTxtView.setText(getString(R.string.format_temp, mWeatherObject.getMain().getTemp()));
        mMinTempTxtView.setText(getString(R.string.format_temp, mWeatherObject.getMain().getMinTemp()));
        mMaxTempTxtView.setText(getString(R.string.format_temp, mWeatherObject.getMain().getMaxTemp()));
        mPressureTxtView.setText(mWeatherObject.getMain().getPressure() + "");
        mHunidityTxtView.setText(mWeatherObject.getMain().getHumidity() + "");

        Picasso.with(getContext())
                .load(OpenWeatherController.URL_ICO + mWeatherObject.getWeather().get(0).getIcon() + ".png")
                .into(mImgStateWeather);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public static WeatherInfoFragment newInstance(WeatherObject weatherObject) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_WEATHER_OBJECT, weatherObject);

        WeatherInfoFragment fragment = new WeatherInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
