package com.example.myprofileapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new BiodataFragment())
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.nav_biodata);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_biodata) {
                selectedFragment = new BiodataFragment();
            } else if (itemId == R.id.nav_kontak) {
                selectedFragment = new KontakFragment();
            } else if (itemId == R.id.nav_kalkulator) {
                selectedFragment = new KalkulatorFragment();
            } else if (itemId == R.id.nav_cuaca) {
                selectedFragment = new CuacaFragment();
            } else if (itemId == R.id.nav_berita) {
                selectedFragment = new BeritaFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}