package com.example.myprofileapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class CuacaFragment extends Fragment {

    private TextView tvLocation, tvDate, tvTemperature, tvWeatherDesc;
    private TextView tvHumidity, tvWind, tvPressure, tvLastUpdate;
    private ImageView ivWeatherIcon;
    private Button btnRefresh;

    // Data cuaca statis (bisa diganti dengan data dinamis nanti)
    private String[] locations = {"Jakarta, Indonesia", "Bandung, Indonesia", "Surabaya, Indonesia"};
    private String[] weatherConditions = {"Cerah Berawan", "Hujan Ringan", "Cerah", "Berawan"};
    private int[] weatherIcons = {R.drawable.ic_sunny, R.drawable.ic_rainy, R.drawable.ic_sunny, R.drawable.ic_sunny_cloudy};

    private Random random = new Random();

    public CuacaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuaca, container, false);

        initViews(view);
        setupRefreshButton();
        loadWeatherData();

        return view;
    }

    private void initViews(View view) {
        tvLocation = view.findViewById(R.id.tv_location);
        tvDate = view.findViewById(R.id.tv_date);
        tvTemperature = view.findViewById(R.id.tv_temperature);
        tvWeatherDesc = view.findViewById(R.id.tv_weather_desc);
        tvHumidity = view.findViewById(R.id.tv_humidity);
        tvWind = view.findViewById(R.id.tv_wind);
        tvPressure = view.findViewById(R.id.tv_pressure);
        tvLastUpdate = view.findViewById(R.id.tv_last_update);
        ivWeatherIcon = view.findViewById(R.id.iv_weather_icon);
        btnRefresh = view.findViewById(R.id.btn_refresh);
    }

    private void setupRefreshButton() {
        btnRefresh.setOnClickListener(v -> {
            // Animasi tombol refresh
            animateRefreshButton();

            // Simulate loading delay
            new Handler().postDelayed(() -> {
                loadWeatherData();
                animateWeatherUpdate();
            }, 1000);
        });
    }

    private void loadWeatherData() {
        // Generate random weather data
        int locationIndex = random.nextInt(locations.length);
        int weatherIndex = random.nextInt(weatherConditions.length);

        String location = locations[locationIndex];
        String weatherCondition = weatherConditions[weatherIndex];
        int temperature = 25 + random.nextInt(15); // 25-40°C
        int humidity = 50 + random.nextInt(40); // 50-90%
        int windSpeed = 5 + random.nextInt(15); // 5-20 km/j
        int pressure = 1000 + random.nextInt(30); // 1000-1030 hPa

        // Update UI
        tvLocation.setText(location);
        tvDate.setText(getCurrentDate());
        tvTemperature.setText(temperature + "°");
        tvWeatherDesc.setText(weatherCondition);
        tvHumidity.setText(humidity + "%");
        tvWind.setText(windSpeed + " km/j");
        tvPressure.setText(pressure + " hPa");
        ivWeatherIcon.setImageResource(weatherIcons[weatherIndex]);

        // Update last refresh time
        String lastUpdate = "Updated: " + new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        tvLastUpdate.setText(lastUpdate);
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMM yyyy", new Locale("id", "ID"));
        return sdf.format(new Date());
    }

    private void animateRefreshButton() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(btnRefresh, "rotation", 0f, 360f);
        rotation.setDuration(500);
        rotation.start();
    }

    private void animateWeatherUpdate() {
        // Animasi untuk temperature change
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvTemperature, "scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(tvTemperature, "scaleY", 1f, 1.2f, 1f);

        // Animasi untuk weather icon
        ObjectAnimator iconScaleX = ObjectAnimator.ofFloat(ivWeatherIcon, "scaleX", 1f, 1.1f, 1f);
        ObjectAnimator iconScaleY = ObjectAnimator.ofFloat(ivWeatherIcon, "scaleY", 1f, 1.1f, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, iconScaleX, iconScaleY);
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }
}