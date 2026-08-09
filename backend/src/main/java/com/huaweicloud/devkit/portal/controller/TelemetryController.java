package com.huaweicloud.devkit.portal.controller;

import com.huaweicloud.devkit.portal.entity.TelemetryEvent;
import com.huaweicloud.devkit.portal.repository.TelemetryEventRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/developer/server/hcdevkitportal/v1/telemetry")
public class TelemetryController {

    private final TelemetryEventRepository repo;

    public TelemetryController(TelemetryEventRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> ingest(@RequestBody Map<String, Object> body) {
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> events = (List<Map<String, Object>>) body.getOrDefault("events",
                body.containsKey("events") ? body.get("events") : List.of(body));

        int accepted = 0;
        int duplicates = 0;

        for (Map<String, Object> evt : events) {
            String eventId = (String) evt.get("event_id");
            if (eventId == null) continue;

            if (repo.existsByEventId(eventId)) {
                duplicates++;
                continue;
            }

            TelemetryEvent event = new TelemetryEvent();
            event.setEventType((String) evt.get("event_type"));
            event.setSessionId((String) evt.get("session_id"));
            event.setEventId(eventId);
            event.setPluginVersion((String) evt.get("plugin_version"));
            event.setAgentPlatform((String) evt.get("agent_platform"));
            event.setOs((String) evt.get("os"));
            event.setServiceName((String) evt.get("service_name"));
            event.setOperationName((String) evt.get("operation_name"));
            event.setRisk((String) evt.get("risk"));

            Object approved = evt.get("approved");
            if (approved instanceof Boolean) event.setApproved((Boolean) approved);

            Object exitCode = evt.get("exit_code");
            if (exitCode instanceof Number) event.setExitCode(((Number) exitCode).intValue());

            event.setErrorCode((String) evt.get("error_code"));

            Object durationMs = evt.get("duration_ms");
            if (durationMs instanceof Number) event.setDurationMs(((Number) durationMs).intValue());

            event.setSkillName((String) evt.get("skill_name"));

            @SuppressWarnings("unchecked")
            Map<String, Object> payload = (Map<String, Object>) evt.get("payload");
            if (payload != null) {
                event.setPayload(payload.toString());
            }

            repo.save(event);
            accepted++;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("accepted", accepted);
        result.put("duplicates", duplicates);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> summary() {
        long total = repo.count();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("total_events", total);
        result.put("completion_rate", 0);
        result.put("message", "Completion rate tracking not yet implemented (see遗留问题 #1)");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/query")
    public ResponseEntity<Map<String, Object>> query(@RequestBody Map<String, Object> body) {
        int page = body.containsKey("page") ? ((Number) body.get("page")).intValue() : 0;
        int size = body.containsKey("size") ? ((Number) body.get("size")).intValue() : 20;
        String eventType = (String) body.get("event_type");
        String serviceName = (String) body.get("service_name");

        Specification<TelemetryEvent> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (eventType != null && !eventType.isEmpty()) {
                predicates.add(cb.equal(root.get("eventType"), eventType));
            }
            if (serviceName != null && !serviceName.isEmpty()) {
                predicates.add(cb.equal(root.get("serviceName"), serviceName));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<TelemetryEvent> pageResult = repo.findAll(spec,
                PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt")));

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("events", pageResult.getContent());
        result.put("total", pageResult.getTotalElements());
        result.put("page", page);
        result.put("size", size);

        return ResponseEntity.ok(result);
    }
}
