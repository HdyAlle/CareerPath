package com.example.careerpath;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.careerpath.activities.AddJobActivity;
import com.example.careerpath.fragments.HomeFragment;
import com.example.careerpath.fragments.MessageFragment;
import com.example.careerpath.fragments.ProfileFragment;
import com.example.careerpath.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNav bottomNav;
    private int lastMenuId = BottomNav.MENU_HOME;

    // ✅ Current user info - Updated UTC: 2025-07-08 16:25:02
    private String currentUser = "Arsieruuu";
    private String currentDateTime = "2025-07-08 16:25:02";

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

        // ✅ Enhanced listener untuk perpindahan menu di BottomNav - UTC: 2025-07-08 16:25:02
        bottomNav.setOnMenuSelectedListener(menuId -> {
            Fragment fragment = null;
            switch (menuId) {
                case BottomNav.MENU_HOME:
                    fragment = new HomeFragment();
                    break;
                case BottomNav.MENU_SEARCH:
                    fragment = new SearchFragment();
                    break;
                case BottomNav.MENU_ADD: // ✅ Handle ADD button - Current UTC: 2025-07-08 16:25:02
                    navigateToAddJob();
                    return; // Don't change fragment, just navigate
                case BottomNav.MENU_MESSAGE:
                    fragment = new MessageFragment();
                    break;
                case BottomNav.MENU_PROFILE:
                    fragment = new ProfileFragment();
                    break;
                default:
                    // Handle unexpected menuId
                    android.util.Log.w("MainActivity", "Unknown menuId: " + menuId + " at " + getCurrentTimeString());
                    return;
            }
            if (fragment != null) {
                showFragment(fragment, menuId);
                bottomNav.setActiveMenu(menuId);
            }
        });
    }

    // ✅ Method untuk navigate ke AddJobActivity - Current UTC: 2025-07-08 16:25:02
    private void navigateToAddJob() {
        Intent intent = new Intent(MainActivity.this, AddJobActivity.class);
        intent.putExtra("current_user", currentUser);
        intent.putExtra("current_time", getCurrentTimeString()); // Real-time timestamp
        intent.putExtra("action_type", "add_new_job");
        intent.putExtra("source", "main_navigation_plus_button");
        intent.putExtra("user_login", "Arsieruuu"); // Explicit user login
        intent.putExtra("session_start", currentDateTime);

        // ✅ Log navigation for debugging
        android.util.Log.d("MainActivity", "Navigating to AddJob by " + currentUser + " at " + getCurrentTimeString());

        startActivityForResult(intent, 1001); // Request code untuk handle result

        // Add smooth transition animation
        overridePendingTransition(R.anim.slide_up_enter, R.anim.fade_out);
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

    // ✅ Enhanced handle result dari AddJobActivity dengan current timestamp
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == RESULT_OK && data != null) {
            // Job berhasil di-submit oleh Arsieruuu
            boolean jobSubmitted = data.getBooleanExtra("job_submitted", false);
            String jobName = data.getStringExtra("job_name");
            String submittedBy = data.getStringExtra("submitted_by");
            String submissionTime = data.getStringExtra("submission_time");

            if (jobSubmitted) {
                // ✅ Enhanced success feedback dengan current timestamp
                String successMessage = "✅ Job '" + jobName + "' successfully added by " + submittedBy +
                        " at " + submissionTime.substring(11, 16) + " UTC";

                android.widget.Toast.makeText(this, successMessage, android.widget.Toast.LENGTH_LONG).show();

                // ✅ Log successful job submission
                android.util.Log.i("MainActivity", "Job submitted: " + jobName + " by " + submittedBy + " at " + submissionTime);

                // Optional: Refresh current fragment jika Home untuk show new job
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (currentFragment instanceof HomeFragment) {
                    // Refresh HomeFragment to show new job
                    showFragment(new HomeFragment(), BottomNav.MENU_HOME);
                    android.util.Log.d("MainActivity", "HomeFragment refreshed after job submission");
                }

                // ✅ Update bottomNav to show Home as active setelah kembali dari AddJob
                bottomNav.setActiveMenu(BottomNav.MENU_HOME);
            }
        } else if (requestCode == 1001 && resultCode == RESULT_CANCELED) {
            // ✅ Handle user cancelled AddJob
            android.util.Log.d("MainActivity", "AddJob cancelled by " + currentUser + " at " + getCurrentTimeString());
        }

        // Reset navigation animation saat kembali dari AddJob
        overridePendingTransition(R.anim.fade_in, R.anim.slide_down_exit);
    }

    // ✅ Method untuk update timestamp ketika activity resumed - Real-time tracking
    @Override
    protected void onResume() {
        super.onResume();
        // Update current time untuk real-time features - Current: 2025-07-08 16:25:02
        currentDateTime = getCurrentTimeString();

        // ✅ Log activity resume dengan current user
        android.util.Log.d("MainActivity", "Activity resumed by " + currentUser + " at " + currentDateTime);
    }

    // ✅ Method untuk handle when activity paused
    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.d("MainActivity", "Activity paused by " + currentUser + " at " + getCurrentTimeString());
    }

    // ✅ Helper method untuk get current timestamp dalam format UTC
    private String getCurrentTimeString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    // ✅ Method untuk get formatted time untuk UI display
    private String getFormattedTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    // ✅ Public method untuk get current user (bisa dipanggil dari fragments)
    public String getCurrentUser() {
        return currentUser;
    }

    // ✅ Public method untuk get current timestamp (bisa dipanggil dari fragments)
    public String getCurrentDateTime() {
        return getCurrentTimeString();
    }

    // ✅ Handle hardware back button
    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        // Jika bukan di HomeFragment, kembali ke Home
        if (!(currentFragment instanceof HomeFragment)) {
            showFragment(new HomeFragment(), BottomNav.MENU_HOME);
            bottomNav.setActiveMenu(BottomNav.MENU_HOME);
            android.util.Log.d("MainActivity", "Back pressed: navigated to Home by " + currentUser);
        } else {
            // Jika sudah di Home, keluar dari app
            android.util.Log.d("MainActivity", "Back pressed: exiting app by " + currentUser + " at " + getCurrentTimeString());
            super.onBackPressed();
        }
    }
}