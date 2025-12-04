package com.group4.sportsclub.Suzane.FacilitiesManager;

import java.time.LocalDate;

public class MaintenanceTask {
    private String taskName;
    private String assignedTeam;
    private LocalDate scheduledDate;
    private String status;

    public MaintenanceTask(String taskName, String assignedTeam, LocalDate scheduledDate) {
        this.taskName = taskName;
        this.assignedTeam = assignedTeam;
        this.scheduledDate = scheduledDate;
        this.status = "Pending";
    }
    // Getters and Setters
    public String getTaskName() { return taskName; }
    public String getAssignedTeam() { return assignedTeam; }
    public LocalDate getScheduledDate() { return scheduledDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

