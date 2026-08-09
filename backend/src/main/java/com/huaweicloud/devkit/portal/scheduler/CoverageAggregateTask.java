package com.huaweicloud.devkit.portal.scheduler;

import com.huaweicloud.devkit.portal.entity.TestCoverageStats;
import com.huaweicloud.devkit.portal.repository.TestCoverageStatsRepository;
import com.huaweicloud.devkit.portal.repository.TestScenarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CoverageAggregateTask {

    private static final Logger log = LoggerFactory.getLogger(CoverageAggregateTask.class);

    private final TestScenarioRepository scenarioRepo;
    private final TestCoverageStatsRepository statsRepo;

    public CoverageAggregateTask(TestScenarioRepository scenarioRepo,
                                 TestCoverageStatsRepository statsRepo) {
        this.scenarioRepo = scenarioRepo;
        this.statsRepo = statsRepo;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void aggregate() {
        try {
            LocalDate today = LocalDate.now();
            if (statsRepo.findByStatDate(today).isPresent()) {
                log.debug("Coverage stats already aggregated for {}", today);
                return;
            }

            long skillCount = scenarioRepo.countActiveWithSkill();
            long cliCount = scenarioRepo.countActiveWithCli();
            long apiCount = scenarioRepo.countActiveWithApi();
            long sdkCount = scenarioRepo.countActiveWithSdk();
            long passCount = scenarioRepo.countPass();
            long failCount = scenarioRepo.countFail();

            TestCoverageStats stats = new TestCoverageStats();
            stats.setStatDate(today);
            stats.setSkillCount((int) skillCount);
            stats.setCliCount((int) cliCount);
            stats.setApiCount((int) apiCount);
            stats.setSdkCount((int) sdkCount);
            stats.setPassCount((int) passCount);
            stats.setFailCount((int) failCount);

            statsRepo.save(stats);
            log.info("Aggregated coverage stats for {}: skill={}, cli={}, api={}, sdk={}, pass={}, fail={}",
                    today, skillCount, cliCount, apiCount, sdkCount, passCount, failCount);
        } catch (Exception e) {
            log.warn("Failed to aggregate coverage stats: {}", e.getMessage());
        }
    }
}
