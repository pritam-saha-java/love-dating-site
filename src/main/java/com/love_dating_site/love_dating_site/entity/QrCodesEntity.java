package com.love_dating_site.love_dating_site.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "qr_codes")
public class QrCodesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "upi_id")
    private String upiId;

    @Column(name = "payee_name")
    private String payeeName;

    @Column(name = "is_active")
    private Boolean isActive;
}
