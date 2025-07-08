package com.example.careerpath;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class JobDetailActivity extends AppCompatActivity {

    private String jobTitle, companyName, location, salary, jobType, experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        // Terima data dari HomeFragment
        jobTitle = getIntent().getStringExtra("job_title");
        companyName = getIntent().getStringExtra("company_name");
        location = getIntent().getStringExtra("location");
        salary = getIntent().getStringExtra("salary");
        jobType = getIntent().getStringExtra("job_type");
        experience = getIntent().getStringExtra("experience");

        // Setup data ke views
        setupJobData();

        // Setup Apply button
        // Di method setupClickListeners(), ubah Apply button:
        Button btnApply = findViewById(R.id.btn_apply_now);
        if (btnApply != null) {
            btnApply.setOnClickListener(v -> {
                Intent intent = new Intent(this, JobApplicationActivity.class);
                // Kirim data job ke JobApplicationActivity
                intent.putExtra("job_title", jobTitle);
                intent.putExtra("company_name", companyName);
                intent.putExtra("location", location);
                startActivity(intent);
            });
        }

        // Setup Read More button → JobDetailFullActivity
        TextView readMoreButton = findViewById(R.id.tv_read_more);
        if (readMoreButton != null) {
            readMoreButton.setOnClickListener(v -> {
                Intent intent = new Intent(this, JobDetailFullActivity.class);
                // Kirim semua data job ke detail full
                intent.putExtra("job_title", jobTitle);
                intent.putExtra("company_name", companyName);
                intent.putExtra("location", location);
                intent.putExtra("salary", salary);
                intent.putExtra("job_type", jobType);
                intent.putExtra("experience", experience);
                startActivity(intent);
            });
        }

        // Setup company logo
        ImageView companyLogo = findViewById(R.id.iv_company_logo);
        if (companyLogo != null) {
            // Bisa set logo berdasarkan company name
            // companyLogo.setImageResource(getCompanyLogo(companyName));
        }
    }

    private void setupJobData() {
        // Set job title
        TextView tvJobTitle = findViewById(R.id.tv_job_title);
        if (tvJobTitle != null) {
            tvJobTitle.setText(jobTitle != null ? jobTitle : "UI/UX Designer");
        }

        // Set company name
        TextView tvCompanyName = findViewById(R.id.tv_company_name);
        if (tvCompanyName != null) {
            tvCompanyName.setText(companyName != null ? companyName : "Google");
        }

        // Set location
        TextView tvLocation = findViewById(R.id.tv_location);
        if (tvLocation != null) {
            tvLocation.setText(location != null ? location : "California");
        }

        // Set posted time
        TextView tvPostedTime = findViewById(R.id.tv_posted_time);
        if (tvPostedTime != null) {
            tvPostedTime.setText("1 day ago");
        }

        // Set job description
        TextView tvJobDescription = findViewById(R.id.tv_job_description);
        if (tvJobDescription != null) {
            tvJobDescription.setText(getJobDescriptionPreview());
        }
    }

    private String getJobDescriptionPreview() {
        return "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem ...";
    }
}