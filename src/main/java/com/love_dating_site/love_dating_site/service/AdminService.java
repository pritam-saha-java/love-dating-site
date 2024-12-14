package com.love_dating_site.love_dating_site.service;

import com.love_dating_site.love_dating_site.entity.QrCodesEntity;

import java.util.List;

public interface AdminService {
    List<QrCodesEntity> getAllQrCodes();

    void deleteQrCode(Long id);

    QrCodesEntity saveQrCode(QrCodesEntity qrCode);

    QrCodesEntity setActiveQrCode(Long id);
}
