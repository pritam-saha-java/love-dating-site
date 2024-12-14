package com.love_dating_site.love_dating_site.configuration;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeGenerator {

    public byte[] generateQRCode(String upiId, String payeeName) throws WriterException, IOException {
        String qrContent = buildQRCodeContent(upiId, payeeName);
        BitMatrix matrix = new MultiFormatWriter().encode(qrContent, BarcodeFormat.QR_CODE, 450, 450);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "png", os);
        return os.toByteArray();
    }

    private String buildQRCodeContent(String upiId, String payeeName) {
        StringBuilder sb = new StringBuilder("upi://pay?");
        sb.append("pa=").append(upiId);
        sb.append("&pn=").append(payeeName);
        sb.append("&cu=INR");
        return sb.toString();
    }
}
