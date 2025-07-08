package com.example.careerpath.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.example.careerpath.MainActivity;
import com.example.careerpath.R;
import com.example.careerpath.activities.AddJobActivity;

/**
 * Enhanced Success Dialog untuk Job Submission
 * Updated for User: Arsieruuu - Current UTC: 2025-07-08 17:11:27
 */
public class JobSuccessDialog extends Dialog {

    private String jobName;
    private String submittedBy;
    private String submissionTime;
    private Context context;

    // ✅ Updated Animation views untuk new layout structure
    private View successCircle;
    private ImageView ivCheckmark;
    private View particle1, particle2, particle3, particle4;

    // ✅ Updated Action buttons - Now LinearLayout instead of ImageView
    private LinearLayout btnHome, btnNotification, btnAddMore, btnMessage, btnBookmark;

    // Text views
    private TextView tvSuccessMessage;

    public JobSuccessDialog(@NonNull Context context, String jobName, String submittedBy, String submissionTime) {
        super(context);
        this.context = context;
        this.jobName = jobName;
        this.submittedBy = submittedBy;
        this.submissionTime = submissionTime;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_job_success);

        // ✅ Setup dialog properties
        setCancelable(false);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        initViews();
        setupClickListeners();
        setupIconTints();
        startCelebrationAnimation();

        android.util.Log.d("JobSuccessDialog", "✅ Success dialog shown for " + submittedBy + " - Job: " + jobName + " at " + getCurrentTimeString());
    }

    private void initViews() {
        // ✅ Updated Animation views untuk new layout - UTC: 17:11:27
        successCircle = findViewById(R.id.success_circle);
        ivCheckmark = findViewById(R.id.iv_checkmark);

        // ✅ Updated Particles - now using View instead of ImageView
        particle1 = findViewById(R.id.particle_1);
        particle2 = findViewById(R.id.particle_2);
        particle3 = findViewById(R.id.particle_3);
        particle4 = findViewById(R.id.particle_4);

        // ✅ Updated Action buttons - now LinearLayout
        btnHome = findViewById(R.id.btn_home);
        btnNotification = findViewById(R.id.btn_notification);
        btnAddMore = findViewById(R.id.btn_add_more);
        btnMessage = findViewById(R.id.btn_message);
        btnBookmark = findViewById(R.id.btn_bookmark);

        // Text
        tvSuccessMessage = findViewById(R.id.tv_success_message);

        // ✅ Enhanced message dengan job info untuk User Arsieruuu - Current: 17:11:27
        String message = "Job '" + jobName + "' has been successfully submitted by " + submittedBy + "\nthank you";
        tvSuccessMessage.setText(message);

        android.util.Log.d("JobSuccessDialog", "Views initialized for " + submittedBy + " at " + getCurrentTimeString());
    }

    /**
     * ✅ Setup icon tints programmatically untuk avoid XML namespace issues
     */
    private void setupIconTints() {
        try {
            int inactiveColor = ContextCompat.getColor(context, R.color.gray_inactive);
            int whiteColor = ContextCompat.getColor(context, R.color.white);

            // Set tints untuk inactive buttons
            setButtonIconTint(btnHome, inactiveColor);
            setButtonIconTint(btnNotification, inactiveColor);
            setButtonIconTint(btnMessage, inactiveColor);
            setButtonIconTint(btnBookmark, inactiveColor);

            // Set tint untuk active button (add more)
            setButtonIconTint(btnAddMore, whiteColor);

            android.util.Log.d("JobSuccessDialog", "Icon tints applied for " + submittedBy);

        } catch (Exception e) {
            android.util.Log.w("JobSuccessDialog", "Error setting icon tints: " + e.getMessage());
        }
    }

    /**
     * ✅ Helper method untuk set icon tint pada LinearLayout button
     */
    private void setButtonIconTint(LinearLayout button, int color) {
        if (button != null && button.getChildCount() > 0) {
            View child = button.getChildAt(0);
            if (child instanceof ImageView) {
                ((ImageView) child).setColorFilter(color);
            }
        }
    }

    private void setupClickListeners() {
        // ✅ Home Button - Back to MainActivity
        if (btnHome != null) {
            btnHome.setOnClickListener(v -> {
                android.util.Log.d("JobSuccessDialog", "🏠 Home clicked by " + submittedBy + " at " + getCurrentTimeString());
                animateButtonClick(btnHome);

                new android.os.Handler().postDelayed(() -> {
                    dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("returning_from", "job_success_dialog");
                    intent.putExtra("last_action", "home_from_success");
                    intent.putExtra("user", submittedBy);
                    context.startActivity(intent);
                }, 200);
            });
        }

        // ✅ Add More Button - Create another job
        if (btnAddMore != null) {
            btnAddMore.setOnClickListener(v -> {
                android.util.Log.d("JobSuccessDialog", "➕ Add More clicked by " + submittedBy + " at " + getCurrentTimeString());
                animateButtonClick(btnAddMore);

                new android.os.Handler().postDelayed(() -> {
                    dismiss();
                    Intent intent = new Intent(context, AddJobActivity.class);
                    intent.putExtra("current_user", submittedBy);
                    intent.putExtra("current_time", getCurrentTimeString());
                    intent.putExtra("action_type", "add_another_job");
                    intent.putExtra("source", "success_dialog_add_more");
                    intent.putExtra("previous_job", jobName);
                    intent.putExtra("session_continuation", "true");
                    context.startActivity(intent);
                }, 200);
            });
        }

        // ✅ Enhanced placeholder actions dengan better feedback
        if (btnNotification != null) {
            btnNotification.setOnClickListener(v -> {
                android.util.Log.d("JobSuccessDialog", "🔔 Notification clicked by " + submittedBy);
                animateButtonClick(btnNotification);
                showFeatureComingSoonToast("Notifications");
            });
        }

        if (btnMessage != null) {
            btnMessage.setOnClickListener(v -> {
                android.util.Log.d("JobSuccessDialog", "💬 Message clicked by " + submittedBy);
                animateButtonClick(btnMessage);
                showFeatureComingSoonToast("Messages");
            });
        }

        if (btnBookmark != null) {
            btnBookmark.setOnClickListener(v -> {
                android.util.Log.d("JobSuccessDialog", "🔖 Bookmark clicked by " + submittedBy);
                animateButtonClick(btnBookmark);
                showFeatureComingSoonToast("Bookmarks");
            });
        }
    }

    /**
     * ✅ Enhanced feature coming soon toast
     */
    private void showFeatureComingSoonToast(String featureName) {
        String message = featureName + " feature coming soon for " + submittedBy + "!";
        android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show();
    }

    /**
     * ✅ Enhanced celebration animation sequence untuk User Arsieruuu - UTC: 17:11:27
     */
    private void startCelebrationAnimation() {
        // Success circle scale animation
        if (successCircle != null) {
            successCircle.setScaleX(0f);
            successCircle.setScaleY(0f);
            successCircle.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(600)
                    .setStartDelay(200)
                    .start();
        }

        // Checkmark animation
        if (ivCheckmark != null) {
            ivCheckmark.setScaleX(0f);
            ivCheckmark.setScaleY(0f);
            ivCheckmark.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(400)
                    .setStartDelay(600)
                    .start();
        }

        // Particles animation
        animateParticles();

        android.util.Log.d("JobSuccessDialog", "🎉 Celebration animation started for " + submittedBy);
    }

    /**
     * ✅ Enhanced animate celebration particles
     */
    private void animateParticles() {
        View[] particles = {particle1, particle2, particle3, particle4};

        for (int i = 0; i < particles.length; i++) {
            View particle = particles[i];
            if (particle != null) {
                particle.setAlpha(0f);
                particle.setScaleX(0f);
                particle.setScaleY(0f);

                particle.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(300)
                        .setStartDelay(800 + (i * 100))
                        .start();

                // Floating animation
                startFloatingAnimation(particle, i * 200);
            }
        }
    }

    /**
     * ✅ Enhanced floating animation untuk particles
     */
    private void startFloatingAnimation(View view, long delay) {
        new android.os.Handler().postDelayed(() -> {
            if (view != null) {
                view.animate()
                        .translationY(-20f)
                        .setDuration(1000)
                        .withEndAction(() -> {
                            if (view != null) {
                                view.animate()
                                        .translationY(0f)
                                        .setDuration(1000)
                                        .withEndAction(() -> startFloatingAnimation(view, 0))
                                        .start();
                            }
                        })
                        .start();
            }
        }, delay);
    }

    /**
     * ✅ Enhanced button click animation untuk LinearLayout
     */
    private void animateButtonClick(LinearLayout button) {
        if (button != null) {
            button.animate()
                    .scaleX(0.85f)
                    .scaleY(0.85f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        if (button != null) {
                            button.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(100)
                                    .start();
                        }
                    })
                    .start();
        }
    }

    /**
     * ✅ Get current timestamp - Updated UTC: 2025-07-08 17:11:27
     */
    private String getCurrentTimeString() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    /**
     * ✅ Get formatted time untuk UI display
     */
    private String getFormattedTime() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault());
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
        return sdf.format(new java.util.Date());
    }

    /**
     * ✅ Enhanced dialog information
     */
    public void logDialogInfo() {
        android.util.Log.i("JobSuccessDialog", "Dialog Info - User: " + submittedBy +
                ", Job: " + jobName +
                ", Submitted: " + submissionTime +
                ", Current: " + getCurrentTimeString());
    }

    @Override
    public void onBackPressed() {
        // ✅ Enhanced back button handling
        android.util.Log.d("JobSuccessDialog", "⬅️ Back button pressed - dialog remains open for " + submittedBy + " at " + getCurrentTimeString());

        // Optional: Show hint to user
        android.widget.Toast.makeText(context, "Please use action buttons to continue", android.widget.Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        android.util.Log.d("JobSuccessDialog", "Dialog stopped for " + submittedBy + " at " + getCurrentTimeString());
    }

    @Override
    public void dismiss() {
        android.util.Log.d("JobSuccessDialog", "✅ Dialog dismissed for " + submittedBy + " at " + getCurrentTimeString());
        super.dismiss();
    }
}