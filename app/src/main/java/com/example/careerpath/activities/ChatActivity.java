package com.example.careerpath.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.careerpath.R;
import com.example.careerpath.adapters.ChatAdapter;
import com.example.careerpath.models.ChatMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ImageView btnBack, btnCall, btnSearch, btnMore, btnAttach, btnSend;
    private TextView tvContactName, tvOnlineStatus, tvToday;
    private EditText etMessage;
    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> chatMessages;

    // Current session info - UTC: 2025-07-08 15:35:27
    private String contactName;
    private String currentUser = "Arsieruuu";
    private String currentDateTime = "2025-07-08 15:35:27";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
        getIntentData();
        setupRecyclerView();
        setupClickListeners();
        loadChatMessages();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btn_back);
        btnCall = findViewById(R.id.btn_call);
        btnSearch = findViewById(R.id.btn_search);
        btnMore = findViewById(R.id.btn_more);
        btnAttach = findViewById(R.id.btn_attach);
        btnSend = findViewById(R.id.btn_send);
        tvContactName = findViewById(R.id.tv_contact_name);
        tvOnlineStatus = findViewById(R.id.tv_online_status);
        tvToday = findViewById(R.id.tv_today);
        etMessage = findViewById(R.id.et_message);
        recyclerViewChat = findViewById(R.id.recycler_view_chat);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        contactName = intent.getStringExtra("contact_name");
        currentUser = intent.getStringExtra("current_user");
        if (currentUser == null) currentUser = "Arsieruuu";

        // Update UI dengan contact info - Current UTC: 2025-07-08 15:35:27
        if (contactName != null) {
            tvContactName.setText(contactName);
            tvOnlineStatus.setText("Online • Last seen at 15:35");
        }

        // Set today's date - July 8, 2025
        tvToday.setText("Today - July 8, 2025");
    }

    private void setupRecyclerView() {
        chatMessages = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatMessages, this, currentUser);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);
    }

    private void setupClickListeners() {
        // Back button navigation - UTC: 2025-07-08 15:35:27
        btnBack.setOnClickListener(v -> {
            finish();
        });

        // Call button
        btnCall.setOnClickListener(v -> {
            tvOnlineStatus.setText("Calling " + contactName + "...");

            // Reset status after 3 seconds
            new android.os.Handler().postDelayed(() -> {
                tvOnlineStatus.setText("Online • Last seen at 15:35");
            }, 3000);
        });

        // Search button
        btnSearch.setOnClickListener(v -> {
            // Simulate search in chat functionality
            tvOnlineStatus.setText("Searching in chat...");

            new android.os.Handler().postDelayed(() -> {
                tvOnlineStatus.setText("Online • Last seen at 15:35");
            }, 2000);
        });

        // More options button
        btnMore.setOnClickListener(v -> {
            // Could open options menu (block, report, etc.)
            tvOnlineStatus.setText("More options available");

            new android.os.Handler().postDelayed(() -> {
                tvOnlineStatus.setText("Online • Last seen at 15:35");
            }, 2000);
        });

        // Attach button
        btnAttach.setOnClickListener(v -> {
            // Simulate file attachment - Current time: 15:35:27
            String currentTimeChat = "15:35 pm";
            ChatMessage fileMessage = new ChatMessage(
                    currentUser,
                    "Arsieruuu",
                    "",
                    currentTimeChat,
                    "file",
                    true,
                    "CV-" + currentUser + "-CareerPath-2025.PDF",
                    R.drawable.ic_pdf
            );

            chatMessages.add(fileMessage);
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            recyclerViewChat.scrollToPosition(chatMessages.size() - 1);

            // Auto-reply simulation
            simulateReply();
        });

        // Send button
        btnSend.setOnClickListener(v -> sendMessage());
    }

    private void loadChatMessages() {
        // Current UTC: 2025-07-08 15:35:27 - Real conversation for user Arsieruuu
        if (contactName != null && contactName.equals("Orlando Diggs")) {
            chatMessages.add(new ChatMessage("orlando_diggs", "Orlando Diggs", "Hello " + currentUser + ", Good Morning! How are you today?", "08:30 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Morning Orlando! I'm great, thanks for asking. How can I help you?", "08:31 am", "text", true));
            chatMessages.add(new ChatMessage("orlando_diggs", "Orlando Diggs", "I saw the UI/UX Designer vacancy that you uploaded on LinkedIn yesterday and I am interested in joining your company.", "08:32 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "That's wonderful! We're always looking for talented designers. Tell me about your experience.", "08:33 am", "text", true));
            chatMessages.add(new ChatMessage("orlando_diggs", "Orlando Diggs", "Oh yes, please send your CV/Resume Here", "08:35 am", "text", false));
            chatMessages.add(new ChatMessage("orlando_diggs", "Orlando Diggs", "", "08:36 am", "file", false, "Orlando-Diggs-CV-UI-UX-Designer.PDF", R.drawable.ic_pdf));

            // Current time message based on UTC 2025-07-08 15:35:27
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Thanks for sharing your CV! I'll review it carefully and get back to you soon. Your portfolio looks impressive!", "15:35 pm", "text", true));

        } else if (contactName != null && contactName.equals("Andy Robertson")) {
            chatMessages.add(new ChatMessage("andy_robertson", "Andy Robertson", "Hey " + currentUser + "! Just reviewed your Java skills in HdyAlle/CareerPath. Outstanding work!", "09:15 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Hi Andy! Thanks for checking out my project. I appreciate the feedback!", "09:16 am", "text", true));
            chatMessages.add(new ChatMessage("andy_robertson", "Andy Robertson", "We have an exciting senior developer position that matches your skills perfectly. Interested?", "09:18 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Absolutely! I'm very interested in learning more about the opportunity.", "15:35 pm", "text", true));

        } else if (contactName != null && contactName.equals("Giorgio Chiellini")) {
            chatMessages.add(new ChatMessage("giorgio_chiellini", "Giorgio Chiellini", "Hello " + currentUser + "! Your CareerPath UI design caught my attention. Very impressive work!", "10:20 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Hi Giorgio! Thank you so much. I put a lot of effort into the user experience.", "10:21 am", "text", true));
            chatMessages.add(new ChatMessage("giorgio_chiellini", "Giorgio Chiellini", "It shows! Are you open for collaboration on similar innovative projects?", "10:23 am", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Definitely! I'm always excited to work on challenging and innovative projects.", "15:35 pm", "text", true));

        } else {
            // Default conversation for other contacts
            chatMessages.add(new ChatMessage(contactName.toLowerCase().replace(" ", "_"), contactName, "Hello " + currentUser + "! Great to connect with you on this platform.", "12:00 pm", "text", false));
            chatMessages.add(new ChatMessage(currentUser, "Arsieruuu", "Hi " + contactName + "! Nice to connect with you too. How can I help you today?", "15:35 pm", "text", true));
        }

        chatAdapter.notifyDataSetChanged();

        // Scroll to bottom
        if (chatMessages.size() > 0) {
            recyclerViewChat.scrollToPosition(chatMessages.size() - 1);
        }
    }

    private void sendMessage() {
        String messageText = etMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            // Current UTC time: 2025-07-08 15:35:27
            String currentTimeChat = "15:35 pm";

            ChatMessage newMessage = new ChatMessage(
                    currentUser,
                    "Arsieruuu",
                    messageText,
                    currentTimeChat,
                    "text",
                    true
            );

            chatMessages.add(newMessage);
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            recyclerViewChat.scrollToPosition(chatMessages.size() - 1);

            etMessage.setText("");

            // Auto-reply simulation
            simulateReply();
        }
    }

    // Method untuk simulasi reply otomatis dengan timestamp akurat
    private void simulateReply() {
        new android.os.Handler().postDelayed(() -> {
            String replyText = "";

            if (contactName != null && contactName.equals("Orlando Diggs")) {
                replyText = "Perfect " + currentUser + "! I'm excited about this opportunity. When can we schedule an interview? Looking forward to discussing my experience further! 🚀";
            } else if (contactName != null && contactName.equals("Andy Robertson")) {
                replyText = "Excellent " + currentUser + "! I'll send you the detailed job description right away. The team is excited to potentially work with someone of your caliber.";
            } else if (contactName != null && contactName.equals("Giorgio Chiellini")) {
                replyText = "Fantastic " + currentUser + "! Let's schedule a call to discuss the collaboration details. I have some exciting project ideas to share.";
            } else {
                replyText = "Great talking with you " + currentUser + "! I appreciate your time and look forward to our continued conversation.";
            }

            ChatMessage replyMessage = new ChatMessage(
                    contactName.toLowerCase().replace(" ", "_"),
                    contactName,
                    replyText,
                    "15:36 pm",
                    "text",
                    false
            );

            chatMessages.add(replyMessage);
            chatAdapter.notifyItemInserted(chatMessages.size() - 1);
            recyclerViewChat.scrollToPosition(chatMessages.size() - 1);
        }, 2500); // Reply after 2.5 seconds
    }

    @Override
    public void onBackPressed() {
        // Handle hardware back button - same as btnBack
        super.onBackPressed();
        finish();
    }
}