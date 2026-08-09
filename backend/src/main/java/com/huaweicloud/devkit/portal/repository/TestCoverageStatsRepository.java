package com.huaweicloud.devkit.portal.repository;

import com.huaweicloud.devkit.portal.entity.TestCoverageStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestCoverageStatsRepository extends JpaRepository<TestCoverageStats, Long> {

    List<TestCoverageStats> findByStatDateBetweenOrderByStatDateAsc(LocalDate start, LocalDate end);

    Optional<TestCoverageStats> findByStatDate(LocalDate statDate);
}
