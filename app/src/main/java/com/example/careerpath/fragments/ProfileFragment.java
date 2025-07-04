package com.example.careerpath.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.careerpath.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    // User data (bisa dari SharedPreferences, database, atau API)
    private String currentUser = "Arsieruuu";
    private String currentDate = getCurrentDate();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Setup autofill data
        setupAutofillData(view);

        // Setup click listeners untuk setiap icon plus
        setupPlusIconListeners(view);

        return view;
    }

    private void setupAutofillData(View view) {
        // Update nama user
        TextView nameText = view.findViewById(R.id.user_name);
        if (nameText != null) {
            nameText.setText(currentUser);
        }

        // Update lokasi berdasarkan user
        TextView locationText = view.findViewById(R.id.user_location);
        if (locationText != null) {
            locationText.setText(getUserLocation());
        }

        // Update follower count (simulasi berdasarkan username)
        TextView followerText = view.findViewById(R.id.follower_count);
        if (followerText != null) {
            followerText.setText(getFollowerCount());
        }

        // Update following count
        TextView followingText = view.findViewById(R.id.following_count);
        if (followingText != null) {
            followingText.setText(getFollowingCount());
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private String getUserLocation() {
        // Generate lokasi berdasarkan username
        switch (currentUser.toLowerCase()) {
            case "arsieruuu":
                return "Jakarta, Indonesia";
            case "admin":
                return "New York, USA";
            default:
                return "Jakarta, Indonesia";
        }
    }

    private String getFollowerCount() {
        // Generate follower count berdasarkan username length
        int baseCount = currentUser.length() * 10;
        return baseCount + "k";
    }

    private String getFollowingCount() {
        // Generate following count berdasarkan username
        int baseCount = currentUser.length() * 5;
        return baseCount + "k";
    }

    private void setupPlusIconListeners(View view) {
        // About Me plus icon
        ImageView aboutMePlus = view.findViewById(R.id.plus_about_me);
        aboutMePlus.setOnClickListener(v -> openAboutMeForm());

        // Work Experience plus icon
        ImageView workPlus = view.findViewById(R.id.plus_work);
        workPlus.setOnClickListener(v -> openWorkExperienceForm());

        // Education plus icon
        ImageView educationPlus = view.findViewById(R.id.plus_education);
        educationPlus.setOnClickListener(v -> openEducationForm());

        // Skill plus icon
        ImageView skillPlus = view.findViewById(R.id.plus_skill);
        skillPlus.setOnClickListener(v -> openSkillForm());

        // Language plus icon
        ImageView languagePlus = view.findViewById(R.id.plus_language);
        languagePlus.setOnClickListener(v -> openLanguageForm());

        // Appreciation plus icon
        ImageView appreciationPlus = view.findViewById(R.id.plus_appreciation);
        appreciationPlus.setOnClickListener(v -> openAppreciationForm());
    }

    private void openAboutMeForm() {
        String autofillText = "Software Developer passionate about creating innovative solutions. " +
                "Joined since " + currentDate.substring(0, 10) + ". " +
                "Currently based in " + getUserLocation();
        Toast.makeText(getContext(), "About Me: " + autofillText, Toast.LENGTH_LONG).show();
    }

    private void openWorkExperienceForm() {
        String autofillText = "Senior Developer at Tech Corp\n" +
                "Jan 2020 - Present\n" +
                "Led development team for mobile applications\n" +
                "Updated: " + currentDate;
        Toast.makeText(getContext(), "Work Experience: " + autofillText, Toast.LENGTH_LONG).show();
    }

    private void openEducationForm() {
        String autofillText = "Computer Science\n" +
                "University of Indonesia\n" +
                "Sep 2016 - Aug 2020 • 4 Years\n" +
                "Profile created: " + currentDate;
        Toast.makeText(getContext(), "Education: " + autofillText, Toast.LENGTH_LONG).show();
    }

    private void openSkillForm() {
        String autofillText = "Skills for " + currentUser + ":\n" +
                "• Java Programming\n" +
                "• Android Development\n" +
                "• UI/UX Design\n" +
                "• Team Leadership\n" +
                "Last updated: " + currentDate;
        Toast.makeText(getContext(), "Skills: " + autofillText, Toast.LENGTH_LONG).show();
    }

    private void openLanguageForm() {
        String autofillText = "Languages:\n" +
                "• Indonesian (Native)\n" +
                "• English (Professional)\n" +
                "• Japanese (Beginner)\n" +
                "User: " + currentUser;
        Toast.makeText(getContext(), "Languages: " + autofillText, Toast.LENGTH_LONG).show();
    }

    private void openAppreciationForm() {
        String autofillText = "Achievements for " + currentUser + ":\n" +
                "• Best Developer Award 2023\n" +
                "• Innovation Excellence\n" +
                "• Team Player Recognition\n" +
                "Achieved on: " + currentDate.substring(0, 10);
        Toast.makeText(getContext(), "Appreciation: " + autofillText, Toast.LENGTH_LONG).show();
    }
}