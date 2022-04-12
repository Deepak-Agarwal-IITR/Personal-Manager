package com.deepakagarwal.personalmanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacts implements Parcelable {
    public String ourUser;
    public String name;
    public String phoneNumber;
    public String address;
    public int id;

    public Contacts(String u,String n, String p,String a){
        ourUser = u;
        name = n;
        phoneNumber = p;
        address = a;
    }

    protected Contacts(Parcel in) {
        ourUser = in.readString();
        name = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
        id = in.readInt();
    }

    public static final Creator<Contacts> CREATOR = new Creator<Contacts>() {
        @Override
        public Contacts createFromParcel(Parcel in) {
            return new Contacts(in);
        }

        @Override
        public Contacts[] newArray(int size) {
            return new Contacts[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ourUser);
        dest.writeString(name);
        dest.writeString(phoneNumber);
        dest.writeString(address);
        dest.writeInt(id);
    }

    public void setId(int id) {
        this.id = id;
    }
}
