package com.stratone.accounting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottom_navigation;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout = findViewById(R.id.frameLayout);
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


/*        bottom_navigation = findViewById(R.id.bottom_navigation);
        */

    /*    homeFragment = new HomeFragment();
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId())
                {
                    case R.id.nav_input:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_master:
                        selectedFragment = new DashboardFragment();
                        break;
                    case R.id.nav_output:
                        selectedFragment = new OutputFragment();
                        break;
                    case R.id.nav_setting:
                        selectedFragment = new SettingFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,selectedFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                return true;
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        tellFragments();
        super.onBackPressed();
    }

    private void tellFragments(){
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for(Fragment f : fragments){
            if(f != null && f instanceof BaseFragment)
                ((BaseFragment)f).onBackPressed();
        }
    }
}