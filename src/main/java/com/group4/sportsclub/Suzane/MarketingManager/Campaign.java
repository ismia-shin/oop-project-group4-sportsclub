package com.group4.sportsclub.Suzane.MarketingManager;


import java.time.LocalDate;

public class Campaign {
    private String campaignName;
    private String platform;
    private LocalDate scheduleDate;
    private String status;
    private String content;

    public Campaign(String campaignName, String platform, LocalDate scheduleDate, String content) {
        this.campaignName = campaignName;
        this.platform = platform;
        this.scheduleDate = scheduleDate;
        this.content = content;
        this.status = "Scheduled";
    }
    // Getters and Setters
    public String getCampaignName() { return campaignName; }
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }
}