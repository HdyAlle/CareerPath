package com.example.careerpath.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.R;
import com.example.careerpath.activities.ChatActivity;
import com.example.careerpath.models.Message;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messageList;
    private Context context;

    // Current user info - Real timestamp: 2025-07-08 15:29:14
    private String currentUser = "Arsieruuu";
    private String currentDateTime = "2025-07-08 15:29:14";

    public MessageAdapter(List<Message> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);

        holder.tvSenderName.setText(message.getSenderName());
        holder.tvLastMessage.setText(message.getLastMessage());
        holder.tvTime.setText(message.getTime());
        holder.ivProfileImage.setImageResource(message.getProfileImage());

        // Show/hide unread indicator
        holder.viewUnreadIndicator.setVisibility(message.hasUnread() ? View.VISIBLE : View.GONE);

        // Set ripple effect yang benar
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        holder.itemView.setBackgroundResource(outValue.resourceId);

        // Click listener untuk navigasi ke ChatActivity - Current UTC: 2025-07-08 15:29:14
        holder.itemView.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("contact_name", message.getSenderName());
                intent.putExtra("contact_id", "user_" + position);
                intent.putExtra("current_user", currentUser);
                intent.putExtra("current_time", currentDateTime);
                intent.putExtra("repo_name", "HdyAlle/CareerPath");
                intent.putExtra("primary_language", "Java");

                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                // Log untuk debugging jika masih error
                android.util.Log.e("MessageAdapter", "Error starting ChatActivity: " + e.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfileImage;
        TextView tvSenderName, tvLastMessage, tvTime;
        View viewUnreadIndicator;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.iv_profile_image);
            tvSenderName = itemView.findViewById(R.id.tv_sender_name);
            tvLastMessage = itemView.findViewById(R.id.tv_last_message);
            tvTime = itemView.findViewById(R.id.tv_time);
            viewUnreadIndicator = itemView.findViewById(R.id.view_unread_indicator);
        }
    }
}