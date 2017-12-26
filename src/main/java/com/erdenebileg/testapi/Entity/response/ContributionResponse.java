package com.erdenebileg.testapi.Entity.response;

public class ContributionResponse {
    private boolean success;
    private String description;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
