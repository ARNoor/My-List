package com.example.mystack;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class home_activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        openDialogue();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    //return false;
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_movies:
                            selectedFragment = new MoviesFragment();
                            break;
                        case R.id.nav_series:
                            selectedFragment = new SeriesFragment();
                            break;
                        case R.id.nav_foods:
                            selectedFragment = new FoodsFragment();
                            break;
                        case R.id.nav_books:
                            selectedFragment = new BooksFragment();
                            break;
                        case R.id.nav_places:
                            selectedFragment = new PlacesFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    public void openDialogue(){
        OpeningDialogue openingDialogue = new OpeningDialogue();
        openingDialogue.show(getSupportFragmentManager(), "example dialogue");
    }
}
