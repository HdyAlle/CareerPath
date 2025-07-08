package com.example.careerpath.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.JobDetailActivity;
import com.example.careerpath.R;
import com.example.careerpath.models.Job;
import java.util.List;

public class JobCardAdapter extends RecyclerView.Adapter<JobCardAdapter.JobViewHolder> {
    
    private List<Job> jobList;
    private Context context;

    public JobCardAdapter(Context context, List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_job_card, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.bind(job);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        
        private CardView jobCard;
        private ImageView companyLogo, bookmarkIcon;
        private TextView jobTitle, companyLocation, postedTime, salaryAmount, salaryPeriod;
        private TextView tagDesign, tagFullTime, tagSenior;
        private LinearLayout tagsContainer;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            
            // Initialize views
            jobCard = itemView.findViewById(R.id.job_card);
            companyLogo = itemView.findViewById(R.id.iv_company_logo);
            bookmarkIcon = itemView.findViewById(R.id.iv_bookmark);
            jobTitle = itemView.findViewById(R.id.tv_job_title);
            companyLocation = itemView.findViewById(R.id.tv_company_location);
            postedTime = itemView.findViewById(R.id.tv_posted_time);
            salaryAmount = itemView.findViewById(R.id.tv_salary_amount);
            salaryPeriod = itemView.findViewById(R.id.tv_salary_period);
            tagDesign = itemView.findViewById(R.id.tv_tag_design);
            tagFullTime = itemView.findViewById(R.id.tv_tag_full_time);
            tagSenior = itemView.findViewById(R.id.tv_tag_senior);
            tagsContainer = itemView.findViewById(R.id.ll_job_tags);
        }

        public void bind(Job job) {
            // Set job data
            jobTitle.setText(job.getTitle());
            companyLocation.setText(job.getCompanyLocation());
            postedTime.setText(job.getTimePosted());
            salaryAmount.setText(job.getSalary());
            salaryPeriod.setText(job.getSalaryPeriod());
            
            // Set company logo
            companyLogo.setImageResource(job.getCompanyLogoResource());
            
            // Set bookmark state
            updateBookmarkIcon(job.isBookmarked());
            
            // Set job tags
            setupJobTags(job.getTags());
            
            // Set click listeners
            setupClickListeners(job);
        }

        private void setupJobTags(String[] tags) {
            // Clear existing tags
            tagsContainer.removeAllViews();
            
            // Add tags dynamically
            for (String tag : tags) {
                TextView tagView = new TextView(context);
                tagView.setText(tag);
                tagView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_tag));
                tagView.setTextColor(ContextCompat.getColor(context, R.color.gray_inactive));
                tagView.setTextSize(13);
                tagView.setGravity(android.view.Gravity.CENTER);
                tagView.setPadding(
                    dpToPx(14), dpToPx(6), dpToPx(14), dpToPx(6)
                );
                
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(28)
                );
                params.setMargins(0, 0, dpToPx(8), 0);
                tagView.setLayoutParams(params);
                
                tagsContainer.addView(tagView);
            }
        }

        private void setupClickListeners(Job job) {
            // Job card click - navigate to details
            jobCard.setOnClickListener(v -> {
                Intent intent = new Intent(context, JobDetailActivity.class);
                intent.putExtra("job_title", job.getTitle());
                intent.putExtra("company_name", job.getCompanyName());
                intent.putExtra("location", job.getLocation());
                intent.putExtra("salary", job.getFullSalary());
                intent.putExtra("job_type", "Full Time");
                intent.putExtra("experience", "Senior Level");
                context.startActivity(intent);
            });

            // Bookmark click - toggle bookmark state
            bookmarkIcon.setOnClickListener(v -> {
                job.setBookmarked(!job.isBookmarked());
                updateBookmarkIcon(job.isBookmarked());
                
                String message = job.isBookmarked() ? "Job bookmarked" : "Bookmark removed";
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            });
        }

        private void updateBookmarkIcon(boolean isBookmarked) {
            int color = isBookmarked ? 
                ContextCompat.getColor(context, R.color.blue_active) : 
                ContextCompat.getColor(context, R.color.gray_inactive);
            bookmarkIcon.setColorFilter(color);
        }

        private int dpToPx(int dp) {
            float density = context.getResources().getDisplayMetrics().density;
            return Math.round(dp * density);
        }
    }
}