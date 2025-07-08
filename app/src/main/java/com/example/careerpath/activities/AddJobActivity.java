package com.example.careerpath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.careerpath.R;
import com.example.careerpath.dialogs.JobSuccessDialog;

public class AddJobActivity extends AppCompatActivity {

    private ImageView ivBack;
    private EditText etJobName, etInstitutionName, etFieldOfStudy, etSalary, etDescription;
    private Button btnRemove, btnSubmit;

    // ✅ Current user info - Updated UTC: 2025-07-08 17:01:18
    private String currentUser = "Arsieruuu";
    private String currentDateTime = "2025-07-08 17:01:18";
    private String sessionStartTime = "";
    private String actionSource = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        // ✅ Log activity creation dengan current timestamp
        android.util.Log.d("AddJobActivity", "Activity created by " + currentUser + " at " + getCurrentTimeString());

        initViews();
        getIntentData();
        setupClickListeners();
        setupDefaultValues();

        // ✅ Track session start
        sessionStartTime = getCurrentTimeString();
    }

    private void initViews() {
        ivBack = findViewById(R.id.iv_back);
        etJobName = findViewById(R.id.et_job_name);
        etInstitutionName = findViewById(R.id.et_institution_name);
        etFieldOfStudy = findViewById(R.id.et_field_of_study);
        etSalary = findViewById(R.id.et_salary);
        etDescription = findViewById(R.id.et_description);
        btnRemove = findViewById(R.id.btn_remove);
        btnSubmit = findViewById(R.id.btn_submit);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        currentUser = intent.getStringExtra("current_user");
        currentDateTime = intent.getStringExtra("current_time");
        actionSource = intent.getStringExtra("source");
        String userLogin = intent.getStringExtra("user_login");

        // ✅ Enhanced data validation dengan current timestamp
        if (currentUser == null) currentUser = "Arsieruuu";
        if (currentDateTime == null) currentDateTime = "2025-07-08 17:01:18";
        if (actionSource == null) actionSource = "unknown_source";

        // ✅ Log intent data untuk debugging
        android.util.Log.d("AddJobActivity", "Intent data - User: " + currentUser +
                ", Source: " + actionSource + ", Time: " + currentDateTime);
    }

    private void setupClickListeners() {
        // ✅ Back button - Current UTC: 2025-07-08 17:01:18
        ivBack.setOnClickListener(v -> {
            android.util.Log.d("AddJobActivity", "Back button pressed by " + currentUser + " at " + getCurrentTimeString());
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_down_exit);
        });

        // ✅ Remove button - Clear all fields dengan enhanced feedback
        btnRemove.setOnClickListener(v -> {
            clearAllFields();
            String clearTime = getCurrentTimeString();
            String message = "All fields cleared by " + currentUser + " at " + clearTime.substring(11, 16);

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            android.util.Log.d("AddJobActivity", "Fields cleared by " + currentUser + " at " + clearTime);
        });

        // ✅ Submit button - Save job dengan enhanced validation
        btnSubmit.setOnClickListener(v -> {
            android.util.Log.d("AddJobActivity", "Submit button pressed by " + currentUser + " at " + getCurrentTimeString());

            if (validateFields()) {
                submitJob();
            } else {
                android.util.Log.w("AddJobActivity", "Form validation failed for " + currentUser + " at " + getCurrentTimeString());
            }
        });
    }

    private void setupDefaultValues() {
        // ✅ Setup default values sesuai dengan screenshot - Current time: 17:01:18
        etJobName.setText("Build Landing Page");
        etInstitutionName.setText("PT. Bimtek Tech");
        etFieldOfStudy.setText("Senior High School");
        etSalary.setText("200");
        etDescription.setHint("Write additional information here");

        android.util.Log.d("AddJobActivity", "Default values set for " + currentUser + " at " + getCurrentTimeString());
    }

    private void clearAllFields() {
        etJobName.setText("");
        etInstitutionName.setText("");
        etFieldOfStudy.setText("");
        etSalary.setText("");
        etDescription.setText("");
    }

    private boolean validateFields() {
        // ✅ Enhanced validation dengan detailed error messages
        if (TextUtils.isEmpty(etJobName.getText().toString().trim())) {
            etJobName.setError("Job name is required");
            etJobName.requestFocus();
            android.util.Log.w("AddJobActivity", "Validation failed: Job name empty for " + currentUser);
            return false;
        }

        if (TextUtils.isEmpty(etInstitutionName.getText().toString().trim())) {
            etInstitutionName.setError("Institution name is required");
            etInstitutionName.requestFocus();
            android.util.Log.w("AddJobActivity", "Validation failed: Institution name empty for " + currentUser);
            return false;
        }

        if (TextUtils.isEmpty(etFieldOfStudy.getText().toString().trim())) {
            etFieldOfStudy.setError("Field of study is required");
            etFieldOfStudy.requestFocus();
            android.util.Log.w("AddJobActivity", "Validation failed: Field of study empty for " + currentUser);
            return false;
        }

        if (TextUtils.isEmpty(etSalary.getText().toString().trim())) {
            etSalary.setError("Salary is required");
            etSalary.requestFocus();
            android.util.Log.w("AddJobActivity", "Validation failed: Salary empty for " + currentUser);
            return false;
        }

        android.util.Log.d("AddJobActivity", "Form validation passed for " + currentUser + " at " + getCurrentTimeString());
        return true;
    }

    /**
     * ✅ Enhanced submitJob() method dengan Success Dialog - UTC: 2025-07-08 17:01:18
     * User: Arsieruuu
     */
    private void submitJob() {
        // Get all field values dengan current timestamp
        String jobName = etJobName.getText().toString().trim();
        String institutionName = etInstitutionName.getText().toString().trim();
        String fieldOfStudy = etFieldOfStudy.getText().toString().trim();
        String salary = etSalary.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        // Create job submission dengan real-time timestamp
        String submissionTime = getCurrentTimeString(); // Real current time: 2025-07-08 17:01:18
        String submittedBy = currentUser; // Arsieruuu

        // ✅ Enhanced job data logging
        android.util.Log.i("AddJobActivity", "Job submission data - " +
                "Name: " + jobName +
                ", Institution: " + institutionName +
                ", Field: " + fieldOfStudy +
                ", Salary: $" + salary +
                ", By: " + submittedBy +
                ", At: " + submissionTime);

        // ✅ Create and show Success Dialog instead of Toast
        try {
            JobSuccessDialog successDialog = new JobSuccessDialog(
                    this,
                    jobName,
                    submittedBy,
                    submissionTime
            );

            // ✅ Set dialog dismiss listener untuk handle result
            successDialog.setOnDismissListener(dialog -> {
                android.util.Log.d("AddJobActivity", "Success dialog dismissed by " + submittedBy);

                // Return result to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("job_submitted", true);
                resultIntent.putExtra("job_name", jobName);
                resultIntent.putExtra("institution_name", institutionName);
                resultIntent.putExtra("field_of_study", fieldOfStudy);
                resultIntent.putExtra("salary", salary);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("submitted_by", submittedBy);
                resultIntent.putExtra("submission_time", submissionTime);
                resultIntent.putExtra("session_start_time", sessionStartTime);
                resultIntent.putExtra("action_source", actionSource);

                setResult(RESULT_OK, resultIntent);

                // ✅ Delay sebelum close activity untuk smooth transition
                new android.os.Handler().postDelayed(() -> {
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.slide_down_exit);
                }, 300);
            });

            successDialog.show();

            android.util.Log.i("AddJobActivity", "✅ Success dialog shown for job '" + jobName + "' by " + submittedBy + " at " + submissionTime);

        } catch (Exception e) {
            // ✅ Fallback ke Toast jika dialog error
            android.util.Log.e("AddJobActivity", "Error showing success dialog: " + e.getMessage());

            String successMessage = "✅ Job '" + jobName + "' submitted successfully by " + submittedBy +
                    " at " + submissionTime.substring(11, 16) + " UTC";

            Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();

            // Return result immediately
            Intent resultIntent = new Intent();
            resultIntent.putExtra("job_submitted", true);
            resultIntent.putExtra("job_name", jobName);
            resultIntent.putExtra("submitted_by", submittedBy);
            resultIntent.putExtra("submission_time", submissionTime);
            setResult(RESULT_OK, resultIntent);

            new android.os.Handler().postDelayed(() -> {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.slide_down_exit);
            }, 1500);
        }
    }

    // ✅ Enhanced lifecycle tracking
    @Override
    protected void onResume() {
        super.onResume();
        android.util.Log.d("AddJobActivity", "Activity resumed by " + currentUser + " at " + getCurrentTimeString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        android.util.Log.d("AddJobActivity", "Activity paused by " + currentUser + " at " + getCurrentTimeString());
    }

    @Override
    public void onBackPressed() {
        android.util.Log.d("AddJobActivity", "Hardware back pressed by " + currentUser + " at " + getCurrentTimeString());

        // ✅ Set cancelled result
        setResult(RESULT_CANCELED);

        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_down_exit);
    }

    // ✅ Helper method untuk get current timestamp dalam format UTC
    private String getCurrentTimeString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    // ✅ Helper method untuk get formatted time untuk UI display
    private String getFormattedTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    // ✅ Method untuk calculate session duration
    private String getSessionDuration() {
        if (!sessionStartTime.isEmpty()) {
            try {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
                sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));

                long startTime = sdf.parse(sessionStartTime).getTime();
                long currentTime = System.currentTimeMillis();
                long duration = (currentTime - startTime) / 1000; // seconds

                return String.valueOf(duration) + " seconds";
            } catch (Exception e) {
                return "unknown";
            }
        }
        return "unknown";
    }

    // ✅ Override onDestroy untuk final logging
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String sessionDuration = getSessionDuration();
        android.util.Log.d("AddJobActivity", "Activity destroyed by " + currentUser +
                " at " + getCurrentTimeString() +
                ", Session duration: " + sessionDuration);
    }

    /**
     * ✅ Public method untuk handle success dialog actions
     */
    public void handleSuccessDialogAction(String action) {
        android.util.Log.d("AddJobActivity", "Success dialog action: " + action + " by " + currentUser);

        switch (action) {
            case "home":
                // Will be handled by dialog's home button
                break;
            case "add_more":
                // Will be handled by dialog's add more button
                break;
            default:
                android.util.Log.w("AddJobActivity", "Unknown dialog action: " + action);
        }
    }
}