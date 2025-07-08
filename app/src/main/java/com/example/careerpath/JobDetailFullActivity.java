package com.example.careerpath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class JobDetailFullActivity extends AppCompatActivity {

    private String jobTitle, companyName, location, salary, jobType, experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail_full);

        // Terima data dari JobDetailActivity
        jobTitle = getIntent().getStringExtra("job_title");
        companyName = getIntent().getStringExtra("company_name");
        location = getIntent().getStringExtra("location");
        salary = getIntent().getStringExtra("salary");
        jobType = getIntent().getStringExtra("job_type");
        experience = getIntent().getStringExtra("experience");

        // Setup job data lengkap
        setupFullJobData();

        // Setup tabs
        setupTabs();

        // Setup Apply button
        Button btnApply = findViewById(R.id.btn_apply_now);
        if (btnApply != null) {
            btnApply.setOnClickListener(v -> {
                // Buka JobApplicationActivity dengan data job
                Intent intent = new Intent(this, JobApplicationActivity.class);

                // Kirim data job ke JobApplicationActivity
                intent.putExtra("job_title", jobTitle != null ? jobTitle : "UI/UX Designer");
                intent.putExtra("company_name", companyName != null ? companyName : "Google Inc");
                intent.putExtra("location", location != null ? location : "California, USA");
                intent.putExtra("salary", salary != null ? salary : "$15k/mo");
                intent.putExtra("job_type", jobType != null ? jobType : "Full Time");
                intent.putExtra("experience", experience != null ? experience : "Senior Level");

                startActivity(intent);
            });
        }
    }

    private void setupFullJobData() {
        // Setup company logo (jika ada di layout)
        ImageView companyLogo = findViewById(R.id.iv_company_logo);
        if (companyLogo != null) {
            // Set logo berdasarkan company (implementasi nanti)
            // companyLogo.setImageResource(getCompanyLogo(companyName));
        }

        // Hanya gunakan ID yang benar-benar ada di layout
        // Comment code yang menggunakan ID yang tidak ada
    }

    private void setupTabs() {
        TextView tabSalary = findViewById(R.id.tab_salary);
        TextView tabJobType = findViewById(R.id.tab_job_type);
        TextView tabPosition = findViewById(R.id.tab_position);

        if (tabSalary != null && tabJobType != null && tabPosition != null) {
            // Set default selected tab (Position)
            selectTab(tabPosition, tabSalary, tabJobType);

            // Setup click listeners
            tabSalary.setOnClickListener(v -> {
                selectTab(tabSalary, tabJobType, tabPosition);
                showSalaryInfo();
            });

            tabJobType.setOnClickListener(v -> {
                selectTab(tabJobType, tabSalary, tabPosition);
                showJobTypeInfo();
            });

            tabPosition.setOnClickListener(v -> {
                selectTab(tabPosition, tabSalary, tabJobType);
                showPositionInfo();
            });
        }
    }

    private void selectTab(TextView selected, TextView... others) {
        // Set selected tab style
        selected.setBackgroundResource(R.drawable.tab_selected);
        selected.setTextColor(ContextCompat.getColor(this, android.R.color.white));

        // Set unselected tabs style
        for (TextView tab : others) {
            tab.setBackgroundResource(android.R.color.transparent);
            tab.setTextColor(ContextCompat.getColor(this, R.color.gray_text));
        }
    }

    private void showSalaryInfo() {
        Toast.makeText(this, "Salary: " + (salary != null ? salary : "$15k/mo"), Toast.LENGTH_SHORT).show();
    }

    private void showJobTypeInfo() {
        Toast.makeText(this, "Job Type: " + (jobType != null ? jobType : "Full Time"), Toast.LENGTH_SHORT).show();
    }

    private void showPositionInfo() {
        Toast.makeText(this, "Position: " + (experience != null ? experience : "Senior Level"), Toast.LENGTH_SHORT).show();
    }
}