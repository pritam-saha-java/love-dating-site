package com.love_dating_site.love_dating_site.service.impl;

import com.google.zxing.WriterException;
import com.love_dating_site.love_dating_site.configuration.QRCodeGenerator;
import com.love_dating_site.love_dating_site.dto.QrCodeResponse;
import com.love_dating_site.love_dating_site.entity.QrCodesEntity;
import com.love_dating_site.love_dating_site.exception.DataNotFoundException;
import com.love_dating_site.love_dating_site.repository.QrCodesRepository;
import com.love_dating_site.love_dating_site.service.DashBoardService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DashBoardServiceImpl implements DashBoardService {

    private final QrCodesRepository qrCodesRepository;
    private final QRCodeGenerator qrCodeGenerator;

    public DashBoardServiceImpl(QrCodesRepository qrCodesRepository,
                                QRCodeGenerator qrCodeGenerator) {
        this.qrCodesRepository = qrCodesRepository;
        this.qrCodeGenerator = qrCodeGenerator;
    }

    @Override
    public QrCodeResponse getRechargeQrCode() {
        QrCodesEntity qr = qrCodesRepository.findByIsActiveTrue()
                .orElseThrow(() -> new DataNotFoundException("No QR Code Found"));

        byte[] qrCode;
        try {
            qrCode = qrCodeGenerator.generateQRCode(qr.getUpiId(), qr.getPayeeName());
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
        return new QrCodeResponse(qr.getUpiId(), qrCode);
    }
}
