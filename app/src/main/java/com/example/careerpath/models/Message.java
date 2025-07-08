package com.example.careerpath.models;

public class Message {
    private String senderName;
    private String lastMessage;
    private String time;
    private int profileImage;
    private boolean hasUnread;

    public Message(String senderName, String lastMessage, String time, int profileImage, boolean hasUnread) {
        this.senderName = senderName;
        this.lastMessage = lastMessage;
        this.time = time;
        this.profileImage = profileImage;
        this.hasUnread = hasUnread;
    }

    // Getters
    public String getSenderName() { return senderName; }
    public String getLastMessage() { return lastMessage; }
    public String getTime() { return time; }
    public int getProfileImage() { return profileImage; }
    public boolean hasUnread() { return hasUnread; }

    // Setters
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
    public void setTime(String time) { this.time = time; }
    public void setProfileImage(int profileImage) { this.profileImage = profileImage; }
    public void setHasUnread(boolean hasUnread) { this.hasUnread = hasUnread; }
}