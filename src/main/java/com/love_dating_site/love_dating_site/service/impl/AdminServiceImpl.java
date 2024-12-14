package com.love_dating_site.love_dating_site.service.impl;

import com.love_dating_site.love_dating_site.entity.QrCodesEntity;
import com.love_dating_site.love_dating_site.exception.DataNotFoundException;
import com.love_dating_site.love_dating_site.repository.QrCodesRepository;
import com.love_dating_site.love_dating_site.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final QrCodesRepository qrCodesRepository;

    public AdminServiceImpl(QrCodesRepository qrCodesRepository) {
        this.qrCodesRepository = qrCodesRepository;
    }

    @Override
    public List<QrCodesEntity> getAllQrCodes() {
        List<QrCodesEntity> response = new ArrayList<>();
        qrCodesRepository.findAll().forEach(response::add);
        return response;
    }

    @Override
    public void deleteQrCode(Long id) {
        QrCodesEntity entity = qrCodesRepository.findById(id).orElseThrow(() -> new DataNotFoundException("No Data Found"));
        qrCodesRepository.deleteById(entity.getId());
    }

    @Override
    public QrCodesEntity saveQrCode(QrCodesEntity qrCode) {
        QrCodesEntity entity = new QrCodesEntity();
        BeanUtils.copyProperties(qrCode, entity);
        entity.setIsActive(Boolean.FALSE);
        return qrCodesRepository.save(entity);
    }

    @Override
    public QrCodesEntity setActiveQrCode(Long id) {
        // Set all records to false
        qrCodesRepository.findAll().forEach(code -> {
            code.setIsActive(Boolean.FALSE);
            qrCodesRepository.save(code);
        });
        //Set Selected Record To True
        QrCodesEntity entity = qrCodesRepository.findById(id).orElseThrow(() -> new DataNotFoundException("No Data Found"));
        entity.setIsActive(Boolean.TRUE);
        qrCodesRepository.save(entity);
        return entity;
    }
}
