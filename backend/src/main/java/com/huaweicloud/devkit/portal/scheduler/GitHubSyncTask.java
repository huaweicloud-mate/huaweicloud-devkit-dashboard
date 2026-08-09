package com.huaweicloud.devkit.portal.scheduler;

import com.huaweicloud.devkit.portal.entity.MetricSnapshot;
import com.huaweicloud.devkit.portal.repository.MetricSnapshotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@ConditionalOnProperty(value = "app.sync.enabled", havingValue = "true", matchIfMissing = true)
public class GitHubSyncTask {

    private static final Logger log = LoggerFactory.getLogger(GitHubSyncTask.class);
    private static final String GITHUB_API = "https://api.github.com/repos/huaweicloud-mate/huaweicloud-devkit";

    private final MetricSnapshotRepository repo;
    private final RestTemplate restTemplate;
    private final String githubToken;

    public GitHubSyncTask(MetricSnapshotRepository repo,
                          @Value("${app.sync.github-token:}") String githubToken) {
        this.repo = repo;
        this.restTemplate = new RestTemplate();
        this.githubToken = githubToken;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void syncStars() {
        if (githubToken == null || githubToken.isEmpty()) {
            log.debug("GitHub token not configured, skipping star sync");
            return;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubToken);
            headers.set("Accept", "application/vnd.github+json");
            headers.set("X-GitHub-Api-Version", "2022-11-28");

            ResponseEntity<Map> resp = restTemplate.exchange(
                    GITHUB_API, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

            if (resp.getBody() != null) {
                Object count = resp.getBody().get("stargazers_count");
                if (count instanceof Number) {
                    saveMetric("github_stars", ((Number) count).longValue());
                }
            }
        } catch (Exception e) {
            log.warn("Failed to sync GitHub stars: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "0 10 * * * *")
    public void syncReleases() {
        if (githubToken == null || githubToken.isEmpty()) {
            log.debug("GitHub token not configured, skipping release sync");
            return;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubToken);
            headers.set("Accept", "application/vnd.github+json");
            headers.set("X-GitHub-Api-Version", "2022-11-28");

            ResponseEntity<List> resp = restTemplate.exchange(
                    GITHUB_API + "/releases?per_page=100",
                    HttpMethod.GET, new HttpEntity<>(headers), List.class);

            long totalDownloads = 0;
            if (resp.getBody() != null) {
                for (Object release : resp.getBody()) {
                    if (release instanceof Map) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> r = (Map<String, Object>) release;
                        @SuppressWarnings("unchecked")
                        List<Map<String, Object>> assets = (List<Map<String, Object>>) r.get("assets");
                        if (assets != null) {
                            for (Map<String, Object> asset : assets) {
                                Object downloads = asset.get("download_count");
                                if (downloads instanceof Number) {
                                    totalDownloads += ((Number) downloads).longValue();
                                }
                            }
                        }
                    }
                }
            }
            saveMetric("github_releases", totalDownloads);
        } catch (Exception e) {
            log.warn("Failed to sync GitHub releases: {}", e.getMessage());
        }
    }

    private void saveMetric(String source, long value) {
        LocalDate today = LocalDate.now();
        if (!repo.existsBySourceAndMetricDate(source, today)) {
            MetricSnapshot snapshot = new MetricSnapshot();
            snapshot.setSource(source);
            snapshot.setMetricDate(today);
            snapshot.setValue(value);
            snapshot.setMeta("{}");
            repo.save(snapshot);
            log.info("Saved {} metric: {} = {}", source, today, value);
        }
    }
}
