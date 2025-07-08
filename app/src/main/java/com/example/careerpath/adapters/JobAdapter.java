package com.example.careerpath.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.R;
import com.example.careerpath.models.Job;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private List<Job> jobs;
    private OnJobClickListener onJobClickListener;
    private OnBookmarkClickListener onBookmarkClickListener;

    public interface OnJobClickListener {
        void onJobClick(Job job);
    }

    public interface OnBookmarkClickListener {
        void onBookmarkClick(Job job, int position);
    }

    public JobAdapter(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setOnJobClickListener(OnJobClickListener listener) {
        this.onJobClickListener = listener;
    }

    public void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        this.onBookmarkClickListener = listener;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_job_card, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.bind(job, position);
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void updateJobs(List<Job> newJobs) {
        this.jobs = newJobs;
        notifyDataSetChanged();
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        private TextView jobTitle;
        private TextView companyInfo;
        private TextView tagDesign;
        private TextView tagFullTime;
        private TextView tagSenior;
        private TextView postedTime;
        private TextView salary;
        private ImageView companyLogo;
        private ImageView bookmarkButton;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitle = itemView.findViewById(R.id.job_title);
            companyInfo = itemView.findViewById(R.id.company_info);
            tagDesign = itemView.findViewById(R.id.tag_design);
            tagFullTime = itemView.findViewById(R.id.tag_full_time);
            tagSenior = itemView.findViewById(R.id.tag_senior);
            postedTime = itemView.findViewById(R.id.posted_time);
            salary = itemView.findViewById(R.id.salary);
            companyLogo = itemView.findViewById(R.id.company_logo);
            bookmarkButton = itemView.findViewById(R.id.bookmark_button);
        }

        public void bind(Job job, int position) {
            jobTitle.setText(job.getJobTitle());
            companyInfo.setText(job.getCompanyInfo());
            postedTime.setText(job.getPostedTime());
            salary.setText(job.getSalary());

            // Set company logo based on company name
            setCompanyLogo(job.getCompanyName());

            // Set tags
            setTags(job.getTags());

            // Set bookmark state
            updateBookmarkState(job.isBookmarked());

            // Set click listeners
            itemView.setOnClickListener(v -> {
                if (onJobClickListener != null) {
                    onJobClickListener.onJobClick(job);
                }
            });

            bookmarkButton.setOnClickListener(v -> {
                job.toggleBookmark();
                updateBookmarkState(job.isBookmarked());
                if (onBookmarkClickListener != null) {
                    onBookmarkClickListener.onBookmarkClick(job, position);
                }
            });
        }

        private void setCompanyLogo(String companyName) {
            // Set default or specific logos based on company name
            if (companyName.toLowerCase().contains("google")) {
                companyLogo.setImageResource(R.drawable.ic_google);
            } else {
                companyLogo.setImageResource(R.drawable.ic_google); // Default for now
            }
        }

        private void setTags(String[] tags) {
            // Hide all tags first
            tagDesign.setVisibility(View.GONE);
            tagFullTime.setVisibility(View.GONE);
            tagSenior.setVisibility(View.GONE);

            // Show and set tags based on available data
            if (tags != null) {
                for (int i = 0; i < tags.length && i < 3; i++) {
                    TextView tagView = getTagView(i);
                    if (tagView != null) {
                        tagView.setVisibility(View.VISIBLE);
                        tagView.setText(tags[i]);
                    }
                }
            }
        }

        private TextView getTagView(int index) {
            switch (index) {
                case 0: return tagDesign;
                case 1: return tagFullTime;
                case 2: return tagSenior;
                default: return null;
            }
        }

        private void updateBookmarkState(boolean isBookmarked) {
            if (isBookmarked) {
                bookmarkButton.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.blue_active));
            } else {
                bookmarkButton.setColorFilter(ContextCompat.getColor(itemView.getContext(), R.color.gray_inactive));
            }
        }
    }
}