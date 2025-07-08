package com.example.careerpath.models;

public class Job {
    private String title;
    private String companyName;
    private String location;
    private String salary;
    private String salaryPeriod;
    private String timePosted;
    private String[] tags;
    private int companyLogoResource;
    private boolean isBookmarked;

    public Job(String title, String companyName, String location, String salary, 
               String salaryPeriod, String timePosted, String[] tags, int companyLogoResource) {
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
        this.salaryPeriod = salaryPeriod;
        this.timePosted = timePosted;
        this.tags = tags;
        this.companyLogoResource = companyLogoResource;
        this.isBookmarked = false;
    }

    // Getters
    public String getTitle() { return title; }
    public String getCompanyName() { return companyName; }
    public String getLocation() { return location; }
    public String getSalary() { return salary; }
    public String getSalaryPeriod() { return salaryPeriod; }
    public String getTimePosted() { return timePosted; }
    public String[] getTags() { return tags; }
    public int getCompanyLogoResource() { return companyLogoResource; }
    public boolean isBookmarked() { return isBookmarked; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public void setLocation(String location) { this.location = location; }
    public void setSalary(String salary) { this.salary = salary; }
    public void setSalaryPeriod(String salaryPeriod) { this.salaryPeriod = salaryPeriod; }
    public void setTimePosted(String timePosted) { this.timePosted = timePosted; }
    public void setTags(String[] tags) { this.tags = tags; }
    public void setCompanyLogoResource(int companyLogoResource) { this.companyLogoResource = companyLogoResource; }
    public void setBookmarked(boolean bookmarked) { this.isBookmarked = bookmarked; }

    public String getCompanyLocation() {
        return companyName + " • " + location;
    }

    public String getFullSalary() {
        return salary + salaryPeriod;
    }
}