package com.example.careerpath.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.careerpath.FilterBottomSheetFragment;
import com.example.careerpath.JobDetailActivity;
import com.example.careerpath.R;

public class SearchFragment extends Fragment {

    private EditText etSearch;
    private TextView tvLocation;
    private CardView filterButton;
    private CardView jobCard1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupFilterButton();
        setupJobCards();
    }

    private void initViews(View view) {
        etSearch = view.findViewById(R.id.et_search);
        tvLocation = view.findViewById(R.id.tv_location);
        filterButton = view.findViewById(R.id.cv_filter_button);
        jobCard1 = view.findViewById(R.id.job_card_1);
    }

    private void setupFilterButton() {
        filterButton.setOnClickListener(v -> {
            FilterBottomSheetFragment filterFragment = new FilterBottomSheetFragment();
            filterFragment.setOnFilterAppliedListener(filterData -> {
                // Handle filter applied
                applyFilter(filterData);
            });
            filterFragment.show(getParentFragmentManager(), "FilterBottomSheet");
        });
    }

    private void applyFilter(FilterBottomSheetFragment.FilterData filterData) {
        // Update UI based on filter
        if (tvLocation != null) {
            tvLocation.setText(filterData.location);
        }

        // Toast untuk demo
        if (getContext() != null) {
            android.widget.Toast.makeText(getContext(),
                    "Filter applied: " + filterData.jobType + " in " + filterData.location,
                    android.widget.Toast.LENGTH_SHORT).show();
        }
    }

    private void setupJobCards() {
        if (jobCard1 != null) {
            jobCard1.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), JobDetailActivity.class);
                intent.putExtra("job_title", "UI/UX Designer");
                intent.putExtra("company_name", "Google");
                intent.putExtra("location", "California, USA");
                intent.putExtra("salary", "$15K /Mo");
                intent.putExtra("job_type", "Full Time");
                intent.putExtra("experience", "Senior Level");
                startActivity(intent);
            });
        }
    }
}