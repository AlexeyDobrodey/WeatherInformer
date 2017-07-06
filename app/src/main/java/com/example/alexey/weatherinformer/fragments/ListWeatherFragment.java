package com.example.alexey.weatherinformer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexey.weatherinformer.OpenWeatherAPI;
import com.example.alexey.weatherinformer.OpenWeatherController;
import com.example.alexey.weatherinformer.R;
import com.example.alexey.weatherinformer.activities.MainActivity;
import com.example.alexey.weatherinformer.adapters.WeatherObjectsAdapter;
import com.example.alexey.weatherinformer.models.ListWeatherObject;
import com.example.alexey.weatherinformer.models.WeatherObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListWeatherFragment extends Fragment implements Callback<ListWeatherObject>{

    public static final String API_KEY_OPENWEATHER = "f594ae859309017879afab44865ae6f8";
    private OpenWeatherAPI apiOpenWeather = null;

    private WeatherObjectsAdapter mWeatherObjectsAdapter;
    private List<WeatherObject> mWeatherObjects;

    public interface OnCitySelectedListener{
        void onSelectCity(WeatherObject weatherObject);
    }

    private OnCitySelectedListener onCitySelectedListener = null;



    @BindView(R.id.cityWeatherRV) RecyclerView cityWeatherRV;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnCitySelectedListener) {
            onCitySelectedListener = (OnCitySelectedListener) context;

        }else{
            throw new RuntimeException(context.toString() + "must implements OnCitySelectedListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiOpenWeather = OpenWeatherController.getAPIOpenWeather();
        mWeatherObjects = new ArrayList<>();
    }

    private void getListCityWeather() {
        apiOpenWeather.getListWeatherObjects(getResources().getString(R.string.list_cities), "metric",
                API_KEY_OPENWEATHER)
                .enqueue(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_weather, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mWeatherObjectsAdapter = new WeatherObjectsAdapter(getContext(), mWeatherObjects, onCitySelectedListener);

        cityWeatherRV.setLayoutManager(new LinearLayoutManager(getContext()));
        cityWeatherRV.setAdapter(mWeatherObjectsAdapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getListCityWeather();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onResponse(Call<ListWeatherObject> call, Response<ListWeatherObject> response) {
        if(response.isSuccessful()) {
            Log.d(MainActivity.TAG, response.body().toString());
            if(!mWeatherObjects.isEmpty()) {
                mWeatherObjects.clear();
            }
            mWeatherObjects.addAll(response.body().getWeatherObjects());
            mWeatherObjectsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<ListWeatherObject> call, Throwable t) {
        Log.d(MainActivity.TAG, t.getMessage());
    }

    public ListWeatherFragment newInstance() {
        ListWeatherFragment fragment = new ListWeatherFragment();
        return fragment;
    }
}
