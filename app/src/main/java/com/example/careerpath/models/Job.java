package com.example.careerpath.models;

public class Job {
    private String jobTitle;
    private String companyName;
    private String location;
    private String salary;
    private String salaryPeriod;
    private String postedTime;
    private String companyLogo;
    private String[] tags;
    private boolean isBookmarked;

    public Job(String jobTitle, String companyName, String location, String salary, 
               String salaryPeriod, String postedTime, String companyLogo, String[] tags) {
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.location = location;
        this.salary = salary;
        this.salaryPeriod = salaryPeriod;
        this.postedTime = postedTime;
        this.companyLogo = companyLogo;
        this.tags = tags;
        this.isBookmarked = false;
    }

    // Getters
    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getSalary() {
        return salary;
    }

    public String getSalaryPeriod() {
        return salaryPeriod;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String[] getTags() {
        return tags;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public String getCompanyInfo() {
        return companyName + " • " + location;
    }

    // Setters
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setSalaryPeriod(String salaryPeriod) {
        this.salaryPeriod = salaryPeriod;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public void toggleBookmark() {
        this.isBookmarked = !this.isBookmarked;
    }
}