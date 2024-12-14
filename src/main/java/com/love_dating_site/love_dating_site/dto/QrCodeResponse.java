package com.love_dating_site.love_dating_site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QrCodeResponse {
    private String upiId;
    private byte[] qrCode;
}
