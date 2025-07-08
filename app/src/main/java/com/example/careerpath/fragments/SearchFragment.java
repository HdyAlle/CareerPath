package com.example.careerpath.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.careerpath.JobDetailActivity;
import com.example.careerpath.R;

public class SearchFragment extends Fragment {

    private EditText searchEditText, locationEditText;
    private Button btnSeniorDesigner, btnDesigner, btnFullTime;
    private ImageButton filterButton;
    private LinearLayout jobListContainer;
    private CardView jobCard, jobCard2;
    private ImageView bookmarkButton, bookmarkButton2;

    // Filter states
    private boolean isSeniorDesignerSelected = true;
    private boolean isDesignerSelected = false;
    private boolean isFullTimeSelected = false;
    private boolean isBookmarked = false;
    private boolean isBookmarked2 = false;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        
        initViews(view);
        setupClickListeners();
        
        return view;
    }

    private void initViews(View view) {
        // Search inputs
        searchEditText = view.findViewById(R.id.search_edittext);
        locationEditText = view.findViewById(R.id.location_edittext);
        
        // Filter buttons
        filterButton = view.findViewById(R.id.filter_button);
        btnSeniorDesigner = view.findViewById(R.id.btn_senior_designer);
        btnDesigner = view.findViewById(R.id.btn_designer);
        btnFullTime = view.findViewById(R.id.btn_full_time);
        
        // Job list container
        jobListContainer = view.findViewById(R.id.job_list_container);
        
        // Job card elements (from included layout)
        jobCard = view.findViewById(R.id.job_card);
        bookmarkButton = view.findViewById(R.id.iv_bookmark);
        
        // Second job card elements
        jobCard2 = view.findViewById(R.id.job_card_2);
        bookmarkButton2 = view.findViewById(R.id.iv_bookmark_2);
    }

    private void setupClickListeners() {
        // Filter button click
        if (filterButton != null) {
            filterButton.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Filter options", Toast.LENGTH_SHORT).show();
            });
        }

        // Job type filter buttons
        if (btnSeniorDesigner != null) {
            btnSeniorDesigner.setOnClickListener(v -> toggleFilter(btnSeniorDesigner, "senior_designer"));
        }

        if (btnDesigner != null) {
            btnDesigner.setOnClickListener(v -> toggleFilter(btnDesigner, "designer"));
        }

        if (btnFullTime != null) {
            btnFullTime.setOnClickListener(v -> toggleFilter(btnFullTime, "full_time"));
        }

        // Job card click - navigate to job details
        if (jobCard != null) {
            jobCard.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), JobDetailActivity.class);
                // Pass job data
                intent.putExtra("job_title", "UI/UX Designer");
                intent.putExtra("company_name", "Google Inc");
                intent.putExtra("location", "California, USA");
                intent.putExtra("salary", "$15K/Mo");
                intent.putExtra("job_type", "Full Time");
                intent.putExtra("experience", "Senior Level");
                startActivity(intent);
            });
        }

        // Second job card click
        if (jobCard2 != null) {
            jobCard2.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), JobDetailActivity.class);
                // Pass Microsoft job data
                intent.putExtra("job_title", "Product Designer");
                intent.putExtra("company_name", "Microsoft");
                intent.putExtra("location", "Seattle, USA");
                intent.putExtra("salary", "$18K/Mo");
                intent.putExtra("job_type", "Remote");
                intent.putExtra("experience", "Mid Level");
                startActivity(intent);
            });
        }

        // Bookmark button click
        if (bookmarkButton != null) {
            bookmarkButton.setOnClickListener(v -> {
                toggleBookmark(1);
            });
        }

        // Second bookmark button click
        if (bookmarkButton2 != null) {
            bookmarkButton2.setOnClickListener(v -> {
                toggleBookmark(2);
            });
        }
    }

    private void toggleFilter(Button button, String filterType) {
        // Check current state by text color
        boolean isCurrentlySelected = button.getCurrentTextColor() == getResources().getColor(R.color.blue_active, null);
        
        if (isCurrentlySelected) {
            // Deselect - use unselected style
            button.setBackgroundResource(R.drawable.bg_tag);
            button.setTextColor(getResources().getColor(R.color.gray_inactive, null));
            updateFilterState(filterType, false);
        } else {
            // Select - use selected style
            button.setBackgroundResource(R.drawable.bg_tag_selected);
            button.setTextColor(getResources().getColor(R.color.blue_active, null));
            updateFilterState(filterType, true);
        }
        
        // Show toast with current filter
        String action = isCurrentlySelected ? "removed" : "applied";
        Toast.makeText(getContext(), "Filter " + button.getText() + " " + action, Toast.LENGTH_SHORT).show();
    }

    private void updateFilterState(String filterType, boolean isSelected) {
        switch (filterType) {
            case "senior_designer":
                isSeniorDesignerSelected = isSelected;
                break;
            case "designer":
                isDesignerSelected = isSelected;
                break;
            case "full_time":
                isFullTimeSelected = isSelected;
                break;
        }
    }

    private void toggleBookmark(int cardNumber) {
        // Toggle bookmark state for specified card
        if (cardNumber == 1) {
            isBookmarked = !isBookmarked;
            
            if (bookmarkButton != null) {
                if (isBookmarked) {
                    // Add bookmark - blue color
                    bookmarkButton.setColorFilter(getResources().getColor(R.color.blue_active, null));
                    Toast.makeText(getContext(), "Google job bookmarked", Toast.LENGTH_SHORT).show();
                } else {
                    // Remove bookmark - gray color
                    bookmarkButton.setColorFilter(getResources().getColor(R.color.gray_inactive, null));
                    Toast.makeText(getContext(), "Google job bookmark removed", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (cardNumber == 2) {
            isBookmarked2 = !isBookmarked2;
            
            if (bookmarkButton2 != null) {
                if (isBookmarked2) {
                    // Add bookmark - blue color
                    bookmarkButton2.setColorFilter(getResources().getColor(R.color.blue_active, null));
                    Toast.makeText(getContext(), "Microsoft job bookmarked", Toast.LENGTH_SHORT).show();
                } else {
                    // Remove bookmark - gray color
                    bookmarkButton2.setColorFilter(getResources().getColor(R.color.gray_inactive, null));
                    Toast.makeText(getContext(), "Microsoft job bookmark removed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}