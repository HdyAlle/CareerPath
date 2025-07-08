package com.example.careerpath;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FilterBottomSheetFragment extends BottomSheetDialogFragment {

    private ImageView btnCloseFilter;
    private LinearLayout llCategoryHeader, llSalaryHeader;
    private LinearLayout llCategoryContent, llSalaryContent;
    private ImageView ivCategoryArrow, ivSalaryArrow;
    private TextView chipFulltime, chipParttime, chipRemote;
    private Button btnApplyFilter;
    private TextView tvMinSalary, tvMaxSalary;

    private OnFilterAppliedListener filterListener;

    public interface OnFilterAppliedListener {
        void onFilterApplied(FilterData filterData);
    }

    public static class FilterData {
        public String category;
        public String subCategory;
        public String location;
        public int minSalary;
        public int maxSalary;
        public String jobType;

        public FilterData(String category, String subCategory, String location,
                          int minSalary, int maxSalary, String jobType) {
            this.category = category;
            this.subCategory = subCategory;
            this.location = location;
            this.minSalary = minSalary;
            this.maxSalary = maxSalary;
            this.jobType = jobType;
        }
    }

    public void setOnFilterAppliedListener(OnFilterAppliedListener listener) {
        this.filterListener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        setupClickListeners();
        setupJobTypeSelection();
    }

    private void initViews(View view) {
        btnCloseFilter = view.findViewById(R.id.btn_close_filter);
        llCategoryHeader = view.findViewById(R.id.ll_category_header);
        llSalaryHeader = view.findViewById(R.id.ll_salary_header);
        llCategoryContent = view.findViewById(R.id.ll_category_content);
        llSalaryContent = view.findViewById(R.id.ll_salary_content);
        ivCategoryArrow = view.findViewById(R.id.iv_category_arrow);
        ivSalaryArrow = view.findViewById(R.id.iv_salary_arrow);
        chipFulltime = view.findViewById(R.id.chip_fulltime);
        chipParttime = view.findViewById(R.id.chip_parttime);
        chipRemote = view.findViewById(R.id.chip_remote);
        btnApplyFilter = view.findViewById(R.id.btn_apply_filter);
        tvMinSalary = view.findViewById(R.id.tv_min_salary);
        tvMaxSalary = view.findViewById(R.id.tv_max_salary);
    }

    private void setupClickListeners() {
        btnCloseFilter.setOnClickListener(v -> dismiss());

        llCategoryHeader.setOnClickListener(v -> toggleSection(llCategoryContent, ivCategoryArrow));
        llSalaryHeader.setOnClickListener(v -> toggleSection(llSalaryContent, ivSalaryArrow));

        btnApplyFilter.setOnClickListener(v -> {
            if (filterListener != null) {
                FilterData filterData = new FilterData(
                        "Design",
                        "UI/UX Design",
                        "California",
                        10000, // Min salary
                        25000, // Max salary
                        getSelectedJobType()
                );
                filterListener.onFilterApplied(filterData);
            }
            dismiss();
        });
    }

    private void setupJobTypeSelection() {
        View.OnClickListener jobTypeListener = v -> {
            // Reset all chips
            resetChip(chipFulltime);
            resetChip(chipParttime);
            resetChip(chipRemote);

            // Select clicked chip
            selectChip((TextView) v);
        };

        chipFulltime.setOnClickListener(jobTypeListener);
        chipParttime.setOnClickListener(jobTypeListener);
        chipRemote.setOnClickListener(jobTypeListener);
    }

    private void toggleSection(LinearLayout content, ImageView arrow) {
        if (content.getVisibility() == View.VISIBLE) {
            content.setVisibility(View.GONE);
            arrow.setRotation(0);
        } else {
            content.setVisibility(View.VISIBLE);
            arrow.setRotation(180);
        }
    }

    private void resetChip(TextView chip) {
        chip.setBackgroundResource(R.drawable.chip_background);
        chip.setTextColor(getResources().getColor(R.color.gray_text, null));
    }

    private void selectChip(TextView chip) {
        chip.setBackgroundResource(R.drawable.chip_selected);
        chip.setTextColor(getResources().getColor(android.R.color.white, null));
    }

    private String getSelectedJobType() {
        if (chipFulltime.getCurrentTextColor() == getResources().getColor(android.R.color.white, null)) {
            return "Full-time";
        } else if (chipParttime.getCurrentTextColor() == getResources().getColor(android.R.color.white, null)) {
            return "Part-time";
        } else if (chipRemote.getCurrentTextColor() == getResources().getColor(android.R.color.white, null)) {
            return "Remote";
        }
        return "Full-time"; // default
    }
}