package com.pramu.mymovies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pramu.mymovies.Favorit.MyFavoritFragment;
import com.pramu.mymovies.Movies.MyMoviesFragment;
import com.pramu.mymovies.TV.MyTVFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;

            switch (menuItem.getItemId()) {
                case R.id.navigation_movies:
                    fragment = new MyMoviesFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_tv:
                    fragment = new MyTVFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_favorit:
                    fragment = new MyFavoritFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment,fragment,fragment.getClass().getSimpleName()).commit();
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movies);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.changeLanguage) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure Want to Exit?")
                .setCancelable(true)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        finish();
                    }
                })
                //jika pilih no
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}
