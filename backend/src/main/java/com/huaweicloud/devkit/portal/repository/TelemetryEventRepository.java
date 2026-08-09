package com.huaweicloud.devkit.portal.repository;

import com.huaweicloud.devkit.portal.entity.TelemetryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelemetryEventRepository extends JpaRepository<TelemetryEvent, Long>,
        JpaSpecificationExecutor<TelemetryEvent> {

    boolean existsByEventId(String eventId);

    Optional<TelemetryEvent> findByEventId(String eventId);
}
