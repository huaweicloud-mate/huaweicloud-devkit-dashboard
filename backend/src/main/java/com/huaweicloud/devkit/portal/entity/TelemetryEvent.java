package com.huaweicloud.devkit.portal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "telemetry_events", indexes = {
    @Index(name = "idx_session", columnList = "session_id"),
    @Index(name = "idx_type_date", columnList = "event_type, created_at")
})
public class TelemetryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_type", nullable = false, length = 32)
    private String eventType;

    @Column(name = "session_id", nullable = false, length = 64)
    private String sessionId;

    @Column(name = "event_id", nullable = false, unique = true, length = 64)
    private String eventId;

    @Column(name = "plugin_version", length = 32)
    private String pluginVersion;

    @Column(name = "agent_platform", length = 32)
    private String agentPlatform;

    @Column(length = 16)
    private String os;

    @Column(name = "service_name", length = 64)
    private String serviceName;

    @Column(name = "operation_name", length = 128)
    private String operationName;

    @Column(length = 16)
    private String risk;

    private Boolean approved;

    @Column(name = "exit_code")
    private Integer exitCode;

    @Column(name = "error_code", length = 64)
    private String errorCode;

    @Column(name = "duration_ms")
    private Integer durationMs;

    @Column(name = "skill_name", length = 64)
    private String skillName;

    @Column(length = 4000)
    private String payload;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getPluginVersion() { return pluginVersion; }
    public void setPluginVersion(String pluginVersion) { this.pluginVersion = pluginVersion; }
    public String getAgentPlatform() { return agentPlatform; }
    public void setAgentPlatform(String agentPlatform) { this.agentPlatform = agentPlatform; }
    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }
    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    public String getOperationName() { return operationName; }
    public void setOperationName(String operationName) { this.operationName = operationName; }
    public String getRisk() { return risk; }
    public void setRisk(String risk) { this.risk = risk; }
    public Boolean getApproved() { return approved; }
    public void setApproved(Boolean approved) { this.approved = approved; }
    public Integer getExitCode() { return exitCode; }
    public void setExitCode(Integer exitCode) { this.exitCode = exitCode; }
    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
    public Integer getDurationMs() { return durationMs; }
    public void setDurationMs(Integer durationMs) { this.durationMs = durationMs; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
