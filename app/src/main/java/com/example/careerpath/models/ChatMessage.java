package com.example.careerpath.models;

public class ChatMessage {
    private String senderId;
    private String senderName;
    private String message;
    private String timestamp;
    private String messageType; // text, file, image
    private boolean isFromCurrentUser;
    private boolean isDelivered;
    private boolean isRead;
    private String fileName; // for file messages
    private int fileIcon; // for file messages

    public ChatMessage(String senderId, String senderName, String message, String timestamp,
                       String messageType, boolean isFromCurrentUser) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.message = message;
        this.timestamp = timestamp;
        this.messageType = messageType;
        this.isFromCurrentUser = isFromCurrentUser;
        this.isDelivered = true;
        this.isRead = true;
    }

    // Constructor for file messages
    public ChatMessage(String senderId, String senderName, String message, String timestamp,
                       String messageType, boolean isFromCurrentUser, String fileName, int fileIcon) {
        this(senderId, senderName, message, timestamp, messageType, isFromCurrentUser);
        this.fileName = fileName;
        this.fileIcon = fileIcon;
    }

    // Getters and Setters
    public String getSenderId() { return senderId; }
    public String getSenderName() { return senderName; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
    public String getMessageType() { return messageType; }
    public boolean isFromCurrentUser() { return isFromCurrentUser; }
    public boolean isDelivered() { return isDelivered; }
    public boolean isRead() { return isRead; }
    public String getFileName() { return fileName; }
    public int getFileIcon() { return fileIcon; }

    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    public void setMessage(String message) { this.message = message; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setMessageType(String messageType) { this.messageType = messageType; }
    public void setFromCurrentUser(boolean fromCurrentUser) { isFromCurrentUser = fromCurrentUser; }
    public void setDelivered(boolean delivered) { isDelivered = delivered; }
    public void setRead(boolean read) { isRead = read; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public void setFileIcon(int fileIcon) { this.fileIcon = fileIcon; }
}