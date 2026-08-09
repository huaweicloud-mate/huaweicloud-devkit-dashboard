CREATE TABLE metric_snapshots (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    source        VARCHAR(32)  NOT NULL,
    metric_date   DATE         NOT NULL,
    snapshot_value BIGINT      NOT NULL,
    meta          TEXT,
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_source_date UNIQUE (source, metric_date)
);

CREATE TABLE telemetry_events (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_type     VARCHAR(32)  NOT NULL,
    session_id     VARCHAR(64)  NOT NULL,
    event_id       VARCHAR(64)  NOT NULL UNIQUE,
    plugin_version VARCHAR(32),
    agent_platform VARCHAR(32),
    os             VARCHAR(16),
    service_name   VARCHAR(64),
    operation_name VARCHAR(128),
    risk           VARCHAR(16),
    approved       BOOLEAN,
    exit_code      INT,
    error_code     VARCHAR(64),
    duration_ms    INT,
    skill_name     VARCHAR(64),
    payload        TEXT,
    created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_session ON telemetry_events (session_id);
CREATE INDEX idx_type_date ON telemetry_events (event_type, created_at);

CREATE TABLE test_scenarios (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    scenario_name  VARCHAR(128) NOT NULL,
    skill_name     VARCHAR(64),
    cli_service    VARCHAR(64),
    cli_operation  VARCHAR(128),
    api_path       VARCHAR(255),
    api_method     VARCHAR(16),
    sdk_language   VARCHAR(32),
    status         VARCHAR(16) DEFAULT 'active',
    last_result    VARCHAR(16),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE vod_feedback (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    session_id      VARCHAR(64),
    service_name    VARCHAR(64),
    operation_name  VARCHAR(128),
    issue_type      VARCHAR(32),
    issue_desc      VARCHAR(1000),
    status          VARCHAR(16) DEFAULT 'open',
    github_issue_url VARCHAR(255),
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE test_coverage_stats (
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    stat_date     DATE NOT NULL,
    skill_count   INT,
    cli_count     INT,
    api_count     INT,
    sdk_count     INT,
    pass_count    INT,
    fail_count    INT,
    CONSTRAINT uq_stat_date UNIQUE (stat_date)
);

INSERT INTO metric_snapshots (source, metric_date, snapshot_value, meta) VALUES
('github_stars',  DATEADD('DAY', -30, CURRENT_DATE), 120, '{}'),
('github_stars',  DATEADD('DAY', -23, CURRENT_DATE), 135, '{}'),
('github_stars',  DATEADD('DAY', -16, CURRENT_DATE), 158, '{}'),
('github_stars',  DATEADD('DAY', -9, CURRENT_DATE), 172, '{}'),
('github_stars',  DATEADD('DAY', -2, CURRENT_DATE), 195, '{}'),
('github_releases', DATEADD('DAY', -30, CURRENT_DATE), 45, '{}'),
('github_releases', DATEADD('DAY', -23, CURRENT_DATE), 67, '{}'),
('github_releases', DATEADD('DAY', -16, CURRENT_DATE), 89, '{}'),
('github_releases', DATEADD('DAY', -9, CURRENT_DATE), 112, '{}'),
('github_releases', DATEADD('DAY', -2, CURRENT_DATE), 156, '{}'),
('npm_downloads', DATEADD('DAY', -30, CURRENT_DATE), 230, '{}'),
('npm_downloads', DATEADD('DAY', -23, CURRENT_DATE), 310, '{}'),
('npm_downloads', DATEADD('DAY', -16, CURRENT_DATE), 445, '{}'),
('npm_downloads', DATEADD('DAY', -9, CURRENT_DATE), 512, '{}'),
('npm_downloads', DATEADD('DAY', -2, CURRENT_DATE), 689, '{}');

INSERT INTO test_scenarios (scenario_name, skill_name, cli_service, cli_operation, api_method, sdk_language, status, last_result) VALUES
('ECS Create Instance', 'huaweicloud-ecs', 'ECS', 'CreateServers', 'POST', 'Python', 'active', 'pass'),
('ECS List Instances', 'huaweicloud-ecs', 'ECS', 'ListServersDetails', 'GET', 'Python', 'active', 'pass'),
('VPC Create with Subnet', 'huaweicloud-vpc', 'VPC', 'CreateVpc', 'POST', 'Python', 'active', 'fail'),
('OBS Bucket Operations', 'huaweicloud-obs', 'OBS', 'CreateBucket', 'PUT', 'Java', 'active', 'pass'),
('RDS Instance Query', 'huaweicloud-rds', 'RDS', 'ListInstances', 'GET', 'Go', 'active', 'untested'),
('CCE Cluster Management', 'huaweicloud-cce', 'CCE', 'CreateCluster', 'POST', 'Python', 'active', 'untested'),
('IAM Policy Management', 'huaweicloud-iam', 'IAM', 'CreatePolicy', 'POST', 'Python', 'archived', 'pass'),
('ELB Load Balancer Setup', 'huaweicloud-elb', 'ELB', 'CreateLoadBalancer', 'POST', 'Java', 'active', 'pass');

INSERT INTO test_coverage_stats (stat_date, skill_count, cli_count, api_count, sdk_count, pass_count, fail_count) VALUES
(DATEADD('DAY', -6, CURRENT_DATE), 72, 156, 98, 45, 43, 2),
(DATEADD('DAY', -5, CURRENT_DATE), 74, 158, 100, 47, 45, 2),
(DATEADD('DAY', -4, CURRENT_DATE), 74, 159, 102, 48, 46, 2),
(DATEADD('DAY', -3, CURRENT_DATE), 75, 161, 103, 49, 47, 2),
(DATEADD('DAY', -2, CURRENT_DATE), 76, 164, 105, 50, 48, 2),
(DATEADD('DAY', -1, CURRENT_DATE), 77, 166, 107, 52, 50, 2),
(CURRENT_DATE, 78, 168, 108, 52, 50, 2);

INSERT INTO vod_feedback (session_id, service_name, operation_name, issue_type, issue_desc, status) VALUES
('sess-001', 'ECS', 'CreateServers', 'doc_gap', 'API documentation missing parameter examples for security groups', 'open'),
('sess-002', 'VPC', 'CreateVpc', 'api_bug', 'Subnet creation fails with CIDR 10.0.0.0/8 even though it is valid', 'acknowledged'),
('sess-003', 'OBS', 'PutObject', 'missing_capability', 'Cannot set object metadata during upload via CLI', 'open'),
('sess-004', 'CCE', 'CreateCluster', 'doc_gap', 'Missing documentation for node pool taint configuration', 'resolved');
