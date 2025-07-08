package com.example.careerpath.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.R;
import com.example.careerpath.models.ChatMessage;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SENT = 1;
    private static final int TYPE_RECEIVED = 2;
    private static final int TYPE_FILE = 3;

    private List<ChatMessage> chatMessages;
    private Context context;
    private String currentUser;

    public ChatAdapter(List<ChatMessage> chatMessages, Context context, String currentUser) {
        this.chatMessages = chatMessages;
        this.context = context;
        this.currentUser = currentUser;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage message = chatMessages.get(position);
        if (message.getMessageType().equals("file")) {
            return TYPE_FILE;
        } else if (message.isFromCurrentUser()) {
            return TYPE_SENT;
        } else {
            return TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_SENT:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_sent, parent, false);
                return new SentMessageViewHolder(view);
            case TYPE_FILE:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_file, parent, false);
                return new FileMessageViewHolder(view);
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_chat_received, parent, false);
                return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = chatMessages.get(position);

        switch (holder.getItemViewType()) {
            case TYPE_SENT:
                ((SentMessageViewHolder) holder).bind(message);
                break;
            case TYPE_FILE:
                ((FileMessageViewHolder) holder).bind(message);
                break;
            default:
                ((ReceivedMessageViewHolder) holder).bind(message);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    // ViewHolder classes
    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTimestamp;
        ImageView ivMessageStatus;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            ivMessageStatus = itemView.findViewById(R.id.iv_message_status);
        }

        void bind(ChatMessage message) {
            tvMessage.setText(message.getMessage());
            tvTimestamp.setText(message.getTimestamp());
            // Show double check for delivered messages
            ivMessageStatus.setVisibility(message.isDelivered() ? View.VISIBLE : View.GONE);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTimestamp;
        ImageView ivProfile;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_message);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            ivProfile = itemView.findViewById(R.id.iv_profile);
        }

        void bind(ChatMessage message) {
            tvMessage.setText(message.getMessage());
            tvTimestamp.setText(message.getTimestamp());
            // Set profile image if needed
        }
    }

    static class FileMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvFileName, tvTimestamp;
        ImageView ivProfile, ivFileIcon;

        public FileMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_file_name);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            ivProfile = itemView.findViewById(R.id.iv_profile);
            ivFileIcon = itemView.findViewById(R.id.iv_file_icon);
        }

        void bind(ChatMessage message) {
            tvFileName.setText(message.getFileName());
            tvTimestamp.setText(message.getTimestamp());
            if (message.getFileIcon() != 0) {
                ivFileIcon.setImageResource(message.getFileIcon());
            }
        }
    }
}