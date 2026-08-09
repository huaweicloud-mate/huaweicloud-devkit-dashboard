package com.huaweicloud.devkit.portal.scheduler;

import com.huaweicloud.devkit.portal.entity.MetricSnapshot;
import com.huaweicloud.devkit.portal.repository.MetricSnapshotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "app.sync.enabled", havingValue = "true", matchIfMissing = true)
public class NpmSyncTask {

    private static final Logger log = LoggerFactory.getLogger(NpmSyncTask.class);
    private static final String NPM_API =
            "https://api.npmjs.org/downloads/point/last-month/@anthropic/claude-code";

    private final MetricSnapshotRepository repo;
    private final RestTemplate restTemplate;

    public NpmSyncTask(MetricSnapshotRepository repo) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(cron = "0 20 * * * *")
    public void syncDownloads() {
        try {
            ResponseEntity<Map> resp = restTemplate.getForEntity(NPM_API, Map.class);

            if (resp.getBody() != null) {
                Object downloads = resp.getBody().get("downloads");
                if (downloads instanceof Number) {
                    long value = ((Number) downloads).longValue();
                    LocalDate today = LocalDate.now();
                    if (!repo.existsBySourceAndMetricDate("npm_downloads", today)) {
                        MetricSnapshot snapshot = new MetricSnapshot();
                        snapshot.setSource("npm_downloads");
                        snapshot.setMetricDate(today);
                        snapshot.setValue(value);
                        snapshot.setMeta("{}");
                        repo.save(snapshot);
                        log.info("Saved npm_downloads metric: {} = {}", today, value);
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Failed to sync npm downloads: {}", e.getMessage());
        }
    }
}
