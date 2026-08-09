package com.huaweicloud.devkit.portal.repository;

import com.huaweicloud.devkit.portal.entity.MetricSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MetricSnapshotRepository extends JpaRepository<MetricSnapshot, Long> {

    List<MetricSnapshot> findBySourceAndMetricDateBetweenOrderByMetricDateAsc(
            String source, LocalDate startDate, LocalDate endDate);

    @Query("SELECT m FROM MetricSnapshot m WHERE m.source = :source ORDER BY m.metricDate DESC")
    List<MetricSnapshot> findLatestBySource(@Param("source") String source);

    boolean existsBySourceAndMetricDate(String source, LocalDate metricDate);
}
