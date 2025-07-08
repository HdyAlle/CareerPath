package com.example.careerpath;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class JobApplicationActivity extends AppCompatActivity {

    private String jobTitle, companyName, location;
    private TextView tvJobTitle, tvCompanyInfo, tvUploadedFileName;
    private CardView cvUploadedFile;
    private EditText etInformation;
    private Button btnApplyNow;
    private ImageView btnBack, btnRemoveFile;

    private Uri selectedFileUri;
    private boolean isFileUploaded = false;

    // Launcher untuk memilih file
    private ActivityResultLauncher<Intent> filePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedFileUri = result.getData().getData();
                    if (selectedFileUri != null) {
                        handleFileUpload(selectedFileUri);
                    }
                }
            }
    );

    // Launcher untuk permission
    private ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    openFilePicker();
                } else {
                    Toast.makeText(this, "Permission denied to access files", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application);

        // Terima data dari JobDetailActivity atau JobDetailFullActivity
        jobTitle = getIntent().getStringExtra("job_title");
        companyName = getIntent().getStringExtra("company_name");
        location = getIntent().getStringExtra("location");

        initViews();
        setupData();
        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btn_back);
        tvJobTitle = findViewById(R.id.tv_job_title);
        tvCompanyInfo = findViewById(R.id.tv_company_info);
        tvUploadedFileName = findViewById(R.id.tv_uploaded_file_name);
        cvUploadedFile = findViewById(R.id.cv_uploaded_file);
        btnRemoveFile = findViewById(R.id.btn_remove_file);
        etInformation = findViewById(R.id.et_information);
        btnApplyNow = findViewById(R.id.btn_apply_now);

        // Upload CV section
        CardView cvUploadCV = findViewById(R.id.cv_upload_cv);
        cvUploadCV.setOnClickListener(v -> checkPermissionAndPickFile());
    }

    private void setupData() {
        if (tvJobTitle != null) {
            tvJobTitle.setText(jobTitle != null ? jobTitle : "UI/UX Designer");
        }

        if (tvCompanyInfo != null) {
            String companyInfo = (companyName != null ? companyName : "Google") +
                    " • " + (location != null ? location : "California") +
                    " • 1 day ago";
            tvCompanyInfo.setText(companyInfo);
        }

        // Hide uploaded file section initially
        if (cvUploadedFile != null) {
            cvUploadedFile.setVisibility(android.view.View.GONE);
        }
    }

    private void setupClickListeners() {
        // Back button
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Remove file button
        if (btnRemoveFile != null) {
            btnRemoveFile.setOnClickListener(v -> removeUploadedFile());
        }

        // Apply now button
        if (btnApplyNow != null) {
            btnApplyNow.setOnClickListener(v -> submitApplication());
        }
    }

    private void checkPermissionAndPickFile() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            openFilePicker();
        }
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            filePickerLauncher.launch(Intent.createChooser(intent, "Select CV/Resume"));
        } catch (Exception e) {
            Toast.makeText(this, "No file manager found", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleFileUpload(Uri fileUri) {
        try {
            // Get file name
            String fileName = getFileName(fileUri);

            if (fileName != null) {
                // Show uploaded file section
                if (cvUploadedFile != null) {
                    cvUploadedFile.setVisibility(android.view.View.VISIBLE);
                }

                if (tvUploadedFileName != null) {
                    tvUploadedFileName.setText(fileName);
                }

                isFileUploaded = true;
                Toast.makeText(this, "CV uploaded successfully!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Failed to upload file", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileName(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            try {
                android.database.Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME);
                    if (nameIndex >= 0) {
                        fileName = cursor.getString(nameIndex);
                    }
                    cursor.close();
                }
            } catch (Exception e) {
                fileName = "Selected_CV.pdf";
            }
        }

        if (fileName == null) {
            fileName = uri.getLastPathSegment();
        }

        return fileName != null ? fileName : "CV_Resume.pdf";
    }

    private void removeUploadedFile() {
        if (cvUploadedFile != null) {
            cvUploadedFile.setVisibility(android.view.View.GONE);
        }

        selectedFileUri = null;
        isFileUploaded = false;
        Toast.makeText(this, "File removed", Toast.LENGTH_SHORT).show();
    }

    private void submitApplication() {
        String information = etInformation != null ? etInformation.getText().toString().trim() : "";

        if (!isFileUploaded) {
            Toast.makeText(this, "Please upload your CV/Resume first", Toast.LENGTH_SHORT).show();
            return;
        }

        if (information.isEmpty()) {
            Toast.makeText(this, "Please explain why you are the right person for this job", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simulate application submission
        Toast.makeText(this, "Application submitted successfully!\nYou will hear back from " +
                (companyName != null ? companyName : "Google") + " soon.", Toast.LENGTH_LONG).show();

        // Close activity and return to previous screen
        finish();
    }
}