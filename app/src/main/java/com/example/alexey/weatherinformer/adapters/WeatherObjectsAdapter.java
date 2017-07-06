package com.example.alexey.weatherinformer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexey.weatherinformer.OpenWeatherController;
import com.example.alexey.weatherinformer.R;
import com.example.alexey.weatherinformer.fragments.ListWeatherFragment;
import com.example.alexey.weatherinformer.models.WeatherObject;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherObjectsAdapter extends RecyclerView.Adapter<WeatherObjectsAdapter.CityViewHolder> {
    private List<WeatherObject> mWeatherObjects;
    private Context mContext;
    private ListWeatherFragment.OnCitySelectedListener onCitySelectedListener;

    public WeatherObjectsAdapter(Context context, List<WeatherObject> weatherObjects,
                                 ListWeatherFragment.OnCitySelectedListener onCitySelectedListener) {
        this.mContext = context;
        this.mWeatherObjects = weatherObjects;
        this.onCitySelectedListener = onCitySelectedListener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_list_weather, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bindData(mWeatherObjects.get(position));
    }

    @Override
    public int getItemCount() {
        return mWeatherObjects.size();
    }


    public class CityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.generalTempTxtView) TextView mTempTxtView;
        @BindView(R.id.imgStateWeather) ImageView mImgStateWeather;
        @BindView(R.id.nameCityTxtView) TextView mNameCityTxtView;

        View mItemView;

        public CityViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            mItemView = itemView;
        }

        public void bindData(final WeatherObject weatherObject) {
            mNameCityTxtView.setText(weatherObject.getName());
            mTempTxtView.setText(mContext.getString(R.string.format_temp, weatherObject.getMain().getTemp()));

            String urlImageIco = OpenWeatherController.URL_ICO + weatherObject.getWeather().get(0).getIcon() + ".png";
            Picasso.with(mContext)
                    .load(urlImageIco)
                    .into(mImgStateWeather);


            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCitySelectedListener.onSelectCity(weatherObject);
                }
            });
        }
    }
}
