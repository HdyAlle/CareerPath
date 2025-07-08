package com.example.careerpath;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.careerpath.fragments.HomeFragment;
import com.example.careerpath.fragments.MessageFragment;
import com.example.careerpath.fragments.ProfileFragment;
import com.example.careerpath.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNav bottomNav;
    private int lastMenuId = BottomNav.MENU_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNav = findViewById(R.id.bottom_nav);

        // Tampilkan HomeFragment pertama kali
        if (savedInstanceState == null) {
            showFragment(new HomeFragment(), BottomNav.MENU_HOME);
            bottomNav.setActiveMenu(BottomNav.MENU_HOME);
        }

        // Listener untuk perpindahan menu di BottomNav
        bottomNav.setOnMenuSelectedListener(menuId -> {
            Fragment fragment = null;
            switch (menuId) {
                case BottomNav.MENU_HOME:
                    fragment = new HomeFragment();
                    break;
                case BottomNav.MENU_SEARCH:
                    fragment = new SearchFragment();
                    break;
                case BottomNav.MENU_MESSAGE:
                    fragment = new MessageFragment();
                    break;
                case BottomNav.MENU_PROFILE:
                    fragment = new ProfileFragment();
                    break;
            }
            if (fragment != null) {
                showFragment(fragment, menuId);
                bottomNav.setActiveMenu(menuId);
            }
        });
    }

    // Method bantu untuk mengganti fragment di fragment_container dengan animasi
    private void showFragment(Fragment fragment, int menuId) {
        int enterAnim, exitAnim, popEnterAnim, popExitAnim;
        if (menuId > lastMenuId) {
            enterAnim = R.anim.slide_in_right;
            exitAnim = R.anim.slide_out_left;
            popEnterAnim = R.anim.slide_in_left;
            popExitAnim = R.anim.slide_out_right;
        } else if (menuId < lastMenuId) {
            enterAnim = R.anim.slide_in_left;
            exitAnim = R.anim.slide_out_right;
            popEnterAnim = R.anim.slide_in_right;
            popExitAnim = R.anim.slide_out_left;
        } else {
            enterAnim = exitAnim = popEnterAnim = popExitAnim = 0;
        }
        lastMenuId = menuId;

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}