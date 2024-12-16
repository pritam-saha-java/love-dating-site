package com.love_dating_site.love_dating_site.dto;

import lombok.AllArgsConstructor;


public class QrCodeResponse {
    private String upiId;
    private byte[] qrCode;

    public QrCodeResponse(String upiId, byte[] qrCode) {
        this.upiId = upiId;
        this.qrCode = qrCode;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public byte[] getQrCode() {
        return qrCode;
    }

    public void setQrCode(byte[] qrCode) {
        this.qrCode = qrCode;
    }
}
