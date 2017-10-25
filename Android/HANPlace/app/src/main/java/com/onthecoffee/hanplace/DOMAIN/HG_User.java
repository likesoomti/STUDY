package com.onthecoffee.hanplace.DOMAIN;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by BAEJJI on 2017-04-30.
 */

public class HG_User implements Parcelable {

    private int user_id;
    private String user_nickname;
    private String user_email;
    private String user_password;
    private String user_profile;
    private String user_introduce;
    private String user_gender;
    private int user_role_id;
    private String user_last_date;
    private int user_visit_count;

    public HG_User() {

    }

    public HG_User(int user_id, String user_nickname, String user_email, String user_passowrd, String user_profile, String user_introduce, String user_gender, int user_role_id, String user_last_date, int user_visit_count) {

        this.user_id = user_id;
        this.user_nickname = user_nickname;
        this.user_password = user_passowrd;
        this.user_profile = user_profile;
        this.user_introduce = user_introduce;
        this.user_gender = user_gender;
        this.user_role_id = user_role_id;
        this.user_last_date = user_last_date;
        this.user_visit_count = user_visit_count;

    }

    public HG_User(String user_email, String user_gender, String user_profile) {

        this.user_email = user_email;
        this.user_profile = user_profile;
        this.user_gender = user_gender;

    }

    public HG_User(Parcel parcel) {

        this.user_email = parcel.readString();
        this.user_profile = parcel.readString();
        this.user_gender = parcel.readString();

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }

    public String getUser_introduce() {
        return user_introduce;
    }

    public void setUser_introduce(String user_introduce) {
        this.user_introduce = user_introduce;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public int getUser_role_id() {
        return user_role_id;
    }

    public void setUser_role_id(int user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUser_last_date() {
        return user_last_date;
    }

    public void setUser_last_date(String user_last_date) {
        this.user_last_date = user_last_date;
    }

    public int getUser_visit_count() {
        return user_visit_count;
    }

    public void setUser_visit_count(int user_visit_count) {
        this.user_visit_count = user_visit_count;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public HG_User createFromParcel(Parcel in) {

            return new HG_User(in);

        }

        @Override

        public HG_User[] newArray(int size) {

            return new HG_User[size];

        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(user_email);
        dest.writeString(user_profile);
        dest.writeString(user_gender);

    }

}
