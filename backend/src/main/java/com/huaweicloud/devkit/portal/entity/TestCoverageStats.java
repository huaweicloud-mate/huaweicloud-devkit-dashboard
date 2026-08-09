package com.huaweicloud.devkit.portal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "test_coverage_stats")
public class TestCoverageStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stat_date", nullable = false)
    private LocalDate statDate;

    @Column(name = "skill_count")
    private Integer skillCount;

    @Column(name = "cli_count")
    private Integer cliCount;

    @Column(name = "api_count")
    private Integer apiCount;

    @Column(name = "sdk_count")
    private Integer sdkCount;

    @Column(name = "pass_count")
    private Integer passCount;

    @Column(name = "fail_count")
    private Integer failCount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getStatDate() { return statDate; }
    public void setStatDate(LocalDate statDate) { this.statDate = statDate; }
    public Integer getSkillCount() { return skillCount; }
    public void setSkillCount(Integer skillCount) { this.skillCount = skillCount; }
    public Integer getCliCount() { return cliCount; }
    public void setCliCount(Integer cliCount) { this.cliCount = cliCount; }
    public Integer getApiCount() { return apiCount; }
    public void setApiCount(Integer apiCount) { this.apiCount = apiCount; }
    public Integer getSdkCount() { return sdkCount; }
    public void setSdkCount(Integer sdkCount) { this.sdkCount = sdkCount; }
    public Integer getPassCount() { return passCount; }
    public void setPassCount(Integer passCount) { this.passCount = passCount; }
    public Integer getFailCount() { return failCount; }
    public void setFailCount(Integer failCount) { this.failCount = failCount; }
}
