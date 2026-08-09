package com.huaweicloud.devkit.portal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "test_scenarios")
public class TestScenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scenario_name", nullable = false, length = 128)
    private String scenarioName;

    @Column(name = "skill_name", length = 64)
    private String skillName;

    @Column(name = "cli_service", length = 64)
    private String cliService;

    @Column(name = "cli_operation", length = 128)
    private String cliOperation;

    @Column(name = "api_path", length = 255)
    private String apiPath;

    @Column(name = "api_method", length = 16)
    private String apiMethod;

    @Column(name = "sdk_language", length = 32)
    private String sdkLanguage;

    @Column(length = 16)
    private String status = "active";

    @Column(name = "last_result", length = 16)
    private String lastResult;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getScenarioName() { return scenarioName; }
    public void setScenarioName(String scenarioName) { this.scenarioName = scenarioName; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getCliService() { return cliService; }
    public void setCliService(String cliService) { this.cliService = cliService; }
    public String getCliOperation() { return cliOperation; }
    public void setCliOperation(String cliOperation) { this.cliOperation = cliOperation; }
    public String getApiPath() { return apiPath; }
    public void setApiPath(String apiPath) { this.apiPath = apiPath; }
    public String getApiMethod() { return apiMethod; }
    public void setApiMethod(String apiMethod) { this.apiMethod = apiMethod; }
    public String getSdkLanguage() { return sdkLanguage; }
    public void setSdkLanguage(String sdkLanguage) { this.sdkLanguage = sdkLanguage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getLastResult() { return lastResult; }
    public void setLastResult(String lastResult) { this.lastResult = lastResult; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
