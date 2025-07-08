package com.example.careerpath.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.R;
import com.example.careerpath.adapters.MessageAdapter;
import com.example.careerpath.models.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {

    private RecyclerView recyclerViewMessages;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    // Current user info - Updated: 2025-07-08 15:30:42 (UTC)
    private String currentUser = "Arsieruuu";
    private String currentDateTime = "2025-07-08 15:30:42";
    private String repoName = "HdyAlle/CareerPath";
    private String primaryLanguage = "Java";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        initViews(view);
        setupRecyclerView();
        loadMessages();

        return view;
    }

    private void initViews(View view) {
        recyclerViewMessages = view.findViewById(R.id.recycler_view_messages);
    }

    private void setupRecyclerView() {
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList, getContext());
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMessages.setAdapter(messageAdapter);
    }

    private void loadMessages() {
        // Current UTC time: 2025-07-08 15:30:42 - Real-time messages untuk user Arsieruuu
        messageList.add(new Message("Andy Robertson", "Hey " + currentUser + "! Just reviewed your " + primaryLanguage + " code in " + repoName + ". Outstanding work! Ready for the next step?", "now", R.drawable.sample_photo, true));

        messageList.add(new Message("Giorgio Chiellini", "Hello " + currentUser + "! Your CareerPath project caught my attention. The UI is impressive! Are you open for collaboration?", "1m ago", R.drawable.sample_photo, true));

        messageList.add(new Message("Alex Morgan", "Hi! I noticed you're actively developing with " + primaryLanguage + " on " + repoName + ". Perfect timing for new opportunities!", "30m ago", R.drawable.sample_photo, false));

        messageList.add(new Message("Megan Rapinoe", "Great to see " + currentUser + " building such a comprehensive career platform. The architecture looks solid!", "1h ago", R.drawable.sample_photo, false));

        messageList.add(new Message("Alessandro Bastoni", "Your " + primaryLanguage + " development skills are impressive " + currentUser + ". Let's connect for exciting projects!", "2h ago", R.drawable.sample_photo, false));

        messageList.add(new Message("İlkay Gündoğan", "Saw your " + repoName + " repository. Perfect match for current job opportunities in tech industry!", "6h ago", R.drawable.sample_photo, false));

        messageAdapter.notifyDataSetChanged();
    }

    // Method untuk refresh messages dengan timestamp terbaru
    public void refreshMessages() {
        messageList.clear();
        loadMessages();
    }

    // Method untuk menambah message baru
    public void addNewMessage(String senderName, String message, String time, int profileImage, boolean hasUnread) {
        messageList.add(0, new Message(senderName, message, time, profileImage, hasUnread));
        messageAdapter.notifyItemInserted(0);
        recyclerViewMessages.scrollToPosition(0);
    }

    // Method untuk simulate real-time message receiving
    public void simulateNewMessage() {
        String[] sampleSenders = {"Andy Robertson", "Giorgio Chiellini", "Alex Morgan"};
        String[] sampleMessages = {
                "Perfect timing " + currentUser + "! Let's schedule a call soon.",
                "Your " + primaryLanguage + " skills are exactly what we need!",
                "Impressed by your " + repoName + " project architecture!"
        };

        int randomIndex = (int) (Math.random() * sampleSenders.length);
        addNewMessage(
                sampleSenders[randomIndex],
                sampleMessages[randomIndex],
                "just now",
                R.drawable.sample_photo,
                true
        );
    }
}