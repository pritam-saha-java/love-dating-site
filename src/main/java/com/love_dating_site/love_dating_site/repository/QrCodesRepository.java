package com.love_dating_site.love_dating_site.repository;

import com.love_dating_site.love_dating_site.entity.QrCodesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrCodesRepository extends CrudRepository<QrCodesEntity, Long> {

    Optional<QrCodesEntity> findByIsActiveTrue();
}
