package com.example.careerpath.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.careerpath.ProfileActivity;
import com.example.careerpath.JobDetailActivity;
import com.example.careerpath.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup profile image click
        ImageView profileImage = view.findViewById(R.id.profile_image);
        if (profileImage != null) {
            profileImage.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), ProfileActivity.class);
                startActivity(intent);
            });
        }

        // Setup Join Now button
        Button btnJoin = view.findViewById(R.id.btn_join);
        if (btnJoin != null) {
            btnJoin.setOnClickListener(v -> {
                Toast.makeText(requireContext(), "Join Now clicked!", Toast.LENGTH_SHORT).show();
            });
        }

        // Setup job card click → JobDetailActivity (ringkasan)
        CardView cardJobRecent = view.findViewById(R.id.card_job_recent);
        if (cardJobRecent != null) {
            cardJobRecent.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), JobDetailActivity.class);
                // Kirim data job
                intent.putExtra("job_title", "UI/UX Designer");
                intent.putExtra("company_name", "Google Inc");
                intent.putExtra("location", "California, USA");
                intent.putExtra("salary", "$15k/mo");
                intent.putExtra("job_type", "Full Time");
                intent.putExtra("experience", "Senior Level");
                startActivity(intent);
            });
        }

        return view;
    }
}