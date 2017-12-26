package com.erdenebileg.testapi.Entity.response;

public class LoginUserResponse {
    private boolean success;
    private int id;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
