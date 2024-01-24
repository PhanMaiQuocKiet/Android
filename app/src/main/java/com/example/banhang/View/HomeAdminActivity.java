package com.example.banhang.View;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.banhang.Login.KhungDangNhapActivity;
import com.example.banhang.R;
import com.google.android.material.navigation.NavigationView;

public class HomeAdminActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_admin);
        AnhXa();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuID = item.getItemId();
                if (menuID == R.id.miHome) {
                    Intent intent = new Intent(HomeAdminActivity.this, HomeAdminActivity.class);
                    startActivity(intent);
                    return true;

                } else if (menuID == R.id.miProfile) {
                    Toast.makeText(HomeAdminActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                    return true;

                } else if (menuID == R.id.miRecommend) {
                    Toast.makeText(HomeAdminActivity.this, "Recommend", Toast.LENGTH_SHORT).show();
                    return true;

                }else if (menuID == R.id.mnAbout) {
                    Toast.makeText(HomeAdminActivity.this, "mnAbout", Toast.LENGTH_SHORT).show();
                    return true;

                }else if (menuID == R.id.mnLogout) {
                    Intent intent = new Intent(HomeAdminActivity.this, KhungDangNhapActivity.class);
                    startActivity(intent);
                    return true;

                }else if (menuID == R.id.mnShare) {
                    Toast.makeText(HomeAdminActivity.this, "mnShare", Toast.LENGTH_SHORT).show();
                    return true;

                }else if (menuID == R.id.mnRateUs) {
                    Toast.makeText(HomeAdminActivity.this, "mnRateUs", Toast.LENGTH_SHORT).show();
                    return true;

                }
                else {
                    return true;

                }
            }
        });
    }
    void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
