package com.mobile.mobilewallet.Objects;

import android.graphics.drawable.Drawable;

import java.time.LocalDateTime;

public class TransactionHistory {
    private String name;
    private String description;
//    private Drawable avatar;
    private byte[] avatarByte;
    private String dateTime;
    private String benAccountNumber;
    private String recAccountNumber;
    private String walletId;
    private String senderName;
    private String tranType;
    private String amount;
    private String transactionStatus;

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
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
