package com.example.careerpath.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.JobDetailActivity;
import com.example.careerpath.R;
import com.example.careerpath.adapters.JobAdapter;
import com.example.careerpath.models.Job;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchEditText;
    private ImageButton filterButton;
    private Button chipSeniorDesigner, chipDesigner, chipFullTime;
    private RecyclerView jobsRecyclerView;
    private JobAdapter jobAdapter;
    private List<Job> jobList;
    private boolean filterExpanded = true;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        
        initializeViews(view);
        setupRecyclerView();
        setupFilterChips();
        setupFilterButton();
        createSampleJobs();
        
        return view;
    }

    private void initializeViews(View view) {
        searchEditText = view.findViewById(R.id.search_edittext);
        filterButton = view.findViewById(R.id.filter_button);
        chipSeniorDesigner = view.findViewById(R.id.chip_senior_designer);
        chipDesigner = view.findViewById(R.id.chip_designer);
        chipFullTime = view.findViewById(R.id.chip_full_time);
        jobsRecyclerView = view.findViewById(R.id.jobs_recycler_view);
    }

    private void setupRecyclerView() {
        jobList = new ArrayList<>();
        jobAdapter = new JobAdapter(jobList);
        
        jobsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        jobsRecyclerView.setAdapter(jobAdapter);
        
        // Set click listeners
        jobAdapter.setOnJobClickListener(this::openJobDetail);
        jobAdapter.setOnBookmarkClickListener(this::handleBookmarkClick);
    }

    private void setupFilterChips() {
        // Set initial selected state for Senior Designer chip
        updateChipState(chipSeniorDesigner, true);
        updateChipState(chipDesigner, false);
        updateChipState(chipFullTime, false);

        // Set click listeners for filter chips
        chipSeniorDesigner.setOnClickListener(v -> toggleChip(chipSeniorDesigner));
        chipDesigner.setOnClickListener(v -> toggleChip(chipDesigner));
        chipFullTime.setOnClickListener(v -> toggleChip(chipFullTime));
    }

    private void setupFilterButton() {
        filterButton.setOnClickListener(v -> toggleFilterExpansion());
    }

    private void toggleChip(Button chip) {
        boolean isSelected = chip.getTag() != null && (Boolean) chip.getTag();
        updateChipState(chip, !isSelected);
        
        // Apply filter logic here if needed
        applyFilters();
    }

    private void updateChipState(Button chip, boolean isSelected) {
        chip.setTag(isSelected);
        if (isSelected) {
            chip.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_tag_selected));
            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue_active));
        } else {
            chip.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_tag));
            chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_inactive));
        }
    }

    private void toggleFilterExpansion() {
        filterExpanded = !filterExpanded;
        // Update filter button appearance
        if (filterExpanded) {
            filterButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_filter_selected));
            filterButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.blue_active));
        } else {
            filterButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bg_tag));
            filterButton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray_inactive));
        }
        
        // Show/hide filter chips
        View chipsContainer = getView().findViewById(R.id.filter_chips_container);
        chipsContainer.setVisibility(filterExpanded ? View.VISIBLE : View.GONE);
    }

    private void applyFilters() {
        // Filter logic based on selected chips
        // For now, just show a toast
        List<String> selectedFilters = new ArrayList<>();
        
        if (isChipSelected(chipSeniorDesigner)) {
            selectedFilters.add("Senior Designer");
        }
        if (isChipSelected(chipDesigner)) {
            selectedFilters.add("Designer");
        }
        if (isChipSelected(chipFullTime)) {
            selectedFilters.add("Full-time");
        }
        
        if (!selectedFilters.isEmpty()) {
            String filterText = "Filtering by: " + String.join(", ", selectedFilters);
            Toast.makeText(getContext(), filterText, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isChipSelected(Button chip) {
        return chip.getTag() != null && (Boolean) chip.getTag();
    }

    private void createSampleJobs() {
        jobList.clear();
        
        // Sample job data matching the design
        jobList.add(new Job(
            "UI/UX Designer",
            "Google inc",
            "California, USA",
            "$15K",
            "/Mo",
            "25 minutes ago",
            "google",
            new String[]{"Design", "Full time", "Senior designer"}
        ));
        
        jobList.add(new Job(
            "Product Designer",
            "Apple Inc",
            "San Francisco, USA",
            "$18K",
            "/Mo",
            "1 hour ago",
            "apple",
            new String[]{"Design", "Full time", "Senior designer"}
        ));
        
        jobList.add(new Job(
            "Senior UX Designer",
            "Meta",
            "Menlo Park, USA",
            "$20K",
            "/Mo",
            "2 hours ago",
            "meta",
            new String[]{"Design", "Full time", "Senior designer"}
        ));
        
        jobList.add(new Job(
            "Visual Designer",
            "Netflix",
            "Los Gatos, USA",
            "$16K",
            "/Mo",
            "3 hours ago",
            "netflix",
            new String[]{"Design", "Full time", "Mid level"}
        ));

        jobAdapter.updateJobs(jobList);
    }

    private void openJobDetail(Job job) {
        Intent intent = new Intent(requireContext(), JobDetailActivity.class);
        intent.putExtra("job_title", job.getJobTitle());
        intent.putExtra("company_name", job.getCompanyName());
        intent.putExtra("location", job.getLocation());
        intent.putExtra("salary", job.getSalary() + job.getSalaryPeriod());
        intent.putExtra("job_type", "Full Time");
        intent.putExtra("experience", "Senior Level");
        startActivity(intent);
    }

    private void handleBookmarkClick(Job job, int position) {
        String message = job.isBookmarked() ? 
            "Job bookmarked: " + job.getJobTitle() : 
            "Bookmark removed: " + job.getJobTitle();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}