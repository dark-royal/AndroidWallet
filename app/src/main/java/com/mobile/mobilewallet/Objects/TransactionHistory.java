package com.mobile.mobilewallet.Objects;

import android.graphics.drawable.Drawable;

import java.time.LocalDateTime;

public class TransactionHistory {
    private String name;
    private String description;
//    private Drawable avatar;
    private byte[] avatarByte;
    private LocalDateTime dateTime;
    private String benAccountNumber;
    private String recAccountNumber;
    private String walletId;

    public byte[] getAvatarByte() {
        return avatarByte;
    }

    public void setAvatarByte(byte[] avatarByte) {
        this.avatarByte = avatarByte;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Drawable getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(Drawable avatar) {
//        this.avatar = avatar;
//    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getBenAccountNumber() {
        return benAccountNumber;
    }

    public void setBenAccountNumber(String benAccountNumber) {
        this.benAccountNumber = benAccountNumber;
    }

    public String getRecAccountNumber() {
        return recAccountNumber;
    }

    public void setRecAccountNumber(String recAccountNumber) {
        this.recAccountNumber = recAccountNumber;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
