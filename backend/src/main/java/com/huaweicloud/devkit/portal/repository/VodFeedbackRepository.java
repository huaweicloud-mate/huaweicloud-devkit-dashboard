package com.huaweicloud.devkit.portal.repository;

import com.huaweicloud.devkit.portal.entity.VodFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VodFeedbackRepository extends JpaRepository<VodFeedback, Long> {

    List<VodFeedback> findByStatusOrderByCreatedAtDesc(String status);

    long countByStatus(String status);
}
