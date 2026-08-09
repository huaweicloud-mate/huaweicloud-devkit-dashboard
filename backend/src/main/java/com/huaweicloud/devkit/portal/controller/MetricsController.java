package com.huaweicloud.devkit.portal.controller;

import com.huaweicloud.devkit.portal.entity.MetricSnapshot;
import com.huaweicloud.devkit.portal.entity.TestCoverageStats;
import com.huaweicloud.devkit.portal.repository.MetricSnapshotRepository;
import com.huaweicloud.devkit.portal.repository.TestCoverageStatsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/rest/developer/server/hcdevkitportal/v1/metrics")
public class MetricsController {

    private final MetricSnapshotRepository metricRepo;
    private final TestCoverageStatsRepository coverageRepo;

    public MetricsController(MetricSnapshotRepository metricRepo,
                             TestCoverageStatsRepository coverageRepo) {
        this.metricRepo = metricRepo;
        this.coverageRepo = coverageRepo;
    }

    @GetMapping("/downloads")
    public ResponseEntity<Map<String, Object>> getDownloads() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(30);

        List<MetricSnapshot> githubReleases = metricRepo.findBySourceAndMetricDateBetweenOrderByMetricDateAsc(
                "github_releases", start, end);
        List<MetricSnapshot> npmDownloads = metricRepo.findBySourceAndMetricDateBetweenOrderByMetricDateAsc(
                "npm_downloads", start, end);

        Map<String, Object> result = new LinkedHashMap<>();

        List<Map<String, Object>> githubSeries = new ArrayList<>();
        for (MetricSnapshot m : githubReleases) {
            Map<String, Object> point = new LinkedHashMap<>();
            point.put("date", m.getMetricDate().toString());
            point.put("value", m.getValue());
            githubSeries.add(point);
        }

        List<Map<String, Object>> npmSeries = new ArrayList<>();
        for (MetricSnapshot m : npmDownloads) {
            Map<String, Object> point = new LinkedHashMap<>();
            point.put("date", m.getMetricDate().toString());
            point.put("value", m.getValue());
            npmSeries.add(point);
        }

        long githubTotal = githubReleases.stream().mapToLong(MetricSnapshot::getValue).sum();
        long npmTotal = npmDownloads.stream().mapToLong(MetricSnapshot::getValue).sum();

        result.put("github_releases", githubSeries);
        result.put("npm_downloads", npmSeries);
        result.put("github_total", githubTotal);
        result.put("npm_total", npmTotal);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/stars")
    public ResponseEntity<Map<String, Object>> getStars() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(30);

        List<MetricSnapshot> stars = metricRepo.findBySourceAndMetricDateBetweenOrderByMetricDateAsc(
                "github_stars", start, end);

        List<Map<String, Object>> series = new ArrayList<>();
        for (MetricSnapshot m : stars) {
            Map<String, Object> point = new LinkedHashMap<>();
            point.put("date", m.getMetricDate().toString());
            point.put("value", m.getValue());
            series.add(point);
        }

        long current = stars.isEmpty() ? 0 : stars.get(stars.size() - 1).getValue();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("series", series);
        result.put("current", current);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/coverage")
    public ResponseEntity<Map<String, Object>> getCoverage() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(7);

        List<TestCoverageStats> stats = coverageRepo.findByStatDateBetweenOrderByStatDateAsc(start, end);

        List<Map<String, Object>> series = new ArrayList<>();
        TestCoverageStats latest = null;
        for (TestCoverageStats s : stats) {
            Map<String, Object> point = new LinkedHashMap<>();
            point.put("date", s.getStatDate().toString());
            point.put("skill_count", s.getSkillCount());
            point.put("cli_count", s.getCliCount());
            point.put("api_count", s.getApiCount());
            point.put("sdk_count", s.getSdkCount());
            point.put("pass_count", s.getPassCount());
            point.put("fail_count", s.getFailCount());
            series.add(point);
            latest = s;
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("series", series);
        if (latest != null) {
            result.put("skill_count", latest.getSkillCount());
            result.put("cli_count", latest.getCliCount());
            result.put("api_count", latest.getApiCount());
            result.put("sdk_count", latest.getSdkCount());
            result.put("pass_count", latest.getPassCount());
            result.put("fail_count", latest.getFailCount());
        }

        return ResponseEntity.ok(result);
    }
}
