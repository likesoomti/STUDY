package com.onthecoffee.hanplace.DOMAIN;

/**
 * Created by Soomti on 2017. 5. 22..
 */

public class HG_CHAT {
    private String userName;
    private String message;

    public HG_CHAT() { }

    public HG_CHAT(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}