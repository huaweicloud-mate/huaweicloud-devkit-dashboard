# HuaweiCloud DevKit 杩愯惀鐪嬫澘璁捐鏂囨。

鏃ユ湡锛?026-08-09
鐘舵€侊細寰呰瘎瀹?
## 1. 鑳屾櫙涓庣洰鏍?
涓哄崕涓轰簯寮€鏀捐兘鍔?Agent 鎻掍欢锛圚uaweiCloud DevKit锛夊缓璁捐繍钀ョ湅鏉匡紝灏嗕互涓嬫寚鏍囬泦涓睍绀哄苟缁存姢锛?
1. 鎻掍欢涓嬭浇閲忥紙浠ｇ爜浠撲笅杞?+ npm 鍖呬笅杞斤級
2. 鎻掍欢寮€婧愪粨 Star 鏁帮紙GitHub锛?3. 鎻掍欢浠诲姟涓€娆℃€у畬鎴愮巼锛堟彃浠跺煁鐐圭粺璁★級
4. 鎻掍欢宸插畬鎴愭祴璇曞満鏅鐩栨暟锛圫kills / CLI / API / SDK锛?5. 鎻掍欢璇嗗埆寮€鏀捐兘鍔?Issue 鐨?VOD 鍙嶉鏁?
## 2. 鎬讳綋鏋舵瀯

閲囩敤**鐩磋繛 REST 妯″紡锛堟柟妗?A锛?*銆傛柟妗?B锛圓PI Gateway + Kafka锛変綔涓哄悗鏈熶紭鍖栨柟鍚戯紝瑙侀仐鐣欓棶棰?#3銆?
```
鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?    鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?鈹? DevKit 鎻掍欢锛堢敤鎴蜂晶锛?鈹?    鈹? GitHub / npm API     鈹?鈹? 鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?    鈹? (澶栭儴鏁版嵁婧?          鈹?鈹? 鈹?閬ユ祴妯″潡(鏂板)  鈹傗攢鈹€鈹尖攢鈹€鈹? 鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?鈹? 鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹? 鈹?             鈹?鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?             鈹?瀹氭椂鎷夊彇
                          鈹?             鈻?              HTTP POST   鈹? 鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?              (浠诲姟鏄庣粏)   鈹? 鈹? Spring Boot 鍚庣     鈹?                          鈹斺攢鈻垛攤  (CCE 瀹瑰櫒鍖栭儴缃?      鈹?                             鈹? 鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?                             鈹? 鈹?閬ユ祴鎺ユ敹 API    鈹? 鈹?                             鈹? 鈹?GitHub 鍚屾浠诲姟  鈹? 鈹?                             鈹? 鈹?娴嬭瘯鍦烘櫙 CRUD   鈹? 鈹?                             鈹? 鈹?鐪嬫澘鏁版嵁 API     鈹? 鈹?                             鈹? 鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?                             鈹?         鈹?           鈹?                             鈹? 鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈻尖攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?                             鈹? 鈹?  MySQL        鈹? 鈹?                             鈹? 鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹? 鈹?                             鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?                                        鈹?                             鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈻尖攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?                             鈹? Vue 鍓嶇 (OBS+CDN)  鈹?                             鈹? - 涓嬭浇閲?Star       鈹?                             鈹? - 瀹屾垚鐜?           鈹?                             鈹? - 鍦烘櫙瑕嗙洊          鈹?                             鈹? - VOD 鍙嶉          鈹?                             鈹? - 鍦烘櫙绠＄悊琛ㄥ崟       鈹?                             鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?```

### 2.1 浠撳簱涓庢ā鍧楀垝鍒?
| 妯″潡 | 浠撳簱 | 閮ㄧ讲浣嶇疆 | 鎶€鏈爤 |
|------|------|----------|--------|
| 鎻掍欢閬ユ祴 | 鐜版湁 `huaweicloud-devkit` 浠撳簱鏂板 | 鐢ㄦ埛鏈湴 npm 鍖?| Node.js (ESM) |
| 鍚庣鏈嶅姟 | 鏂颁粨搴?`huaweicloud-devkit-dashboard` | 鍗庡崡-骞垮窞 CCE | Spring Boot 3.x + MySQL |
| 鍓嶇鐪嬫澘 | 鍚屾柊浠撳簱 | 鍗庡崡-骞垮窞 OBS + CDN | Vue 3 + Vite |
| 鏁版嵁鍚屾 | 鍚庣鍐呯疆瀹氭椂浠诲姟 | CCE 鍐?| Spring Scheduler |

### 2.2 鏁版嵁娴?
- **瀹炴椂娴?*锛氭彃浠堕仴娴?鈫?HTTP POST 鈫?鍚庣
- **瀹氭椂鎷夊彇**锛氬悗绔瘡灏忔椂浠?GitHub API 鎷夊彇 Star/涓嬭浇閲忥紝浠?npm API 鎷夊彇涓嬭浇閲?- **鐢ㄦ埛褰曞叆**锛氭祴璇曞満鏅€氳繃鍓嶇 Web 琛ㄥ崟鍐欏叆鏁版嵁搴?- **灞曠ず娴?*锛氬墠绔粠鍚庣 REST API 鎷夊彇鑱氬悎鏁版嵁娓叉煋鍥捐〃

## 3. 鎻掍欢閬ユ祴鍗忚

### 3.1 鍩嬬偣浣嶇疆锛堝熀浜庡疄闄呬唬鐮侀摼璺級

鍏抽敭鎵ц璺緞鍦?`tools.mjs` 鈫?`callTool()` 鈫?`runHcloud()`銆傞仴娴嬫ā鍧楀祵鍏ヨ繖鏉￠摼璺€?
```
Agent 浼氳瘽
  鈹?  鈹溾攢 tools/list          鈫?涓嶅煁鐐?  鈹溾攢 tools/call (13绉嶅伐鍏?
  鈹?    鈹溾攢 鐭ヨ瘑鍙戠幇绫?(search_docs, retrieve_skill, list_regions...)
  鈹?    鈹?    鈹斺攢 鍩嬬偣: skill_load    (skill_name)
  鈹?    鈹溾攢 CLI 鎵ц绫?(run_readonly_command, run_approved_command, plan_cli_command)
  鈹?    鈹?    鈹斺攢 鍩嬬偣: cli_execute   (service, operation, 鍒嗙被 read/write, 缁撴灉)
  鈹?    鈹斺攢 鎺掗敊绫?(explain_error)
  鈹?          鈹斺攢 鍩嬬偣: error_explain (error_code)
  鈹?  鈹斺攢 鎻掍欢瀹夎鏃?(setup-cli.mjs cmdInstall)
        鈹斺攢 鍩嬬偣: plugin_install, telemetry_consent  鈫?鍦ㄦ寮圭獥璇㈤棶鐢ㄦ埛
```

### 3.2 鏂板閬ユ祴妯″潡鏂囦欢

```
plugins/huaweicloud-core/src/
  telemetry.mjs          鈫?鏂板锛岀函 Node锛岄浂渚濊禆
  mcp-server.mjs         鈫?淇敼锛岃皟鐢?telemetry.record()
  tools.mjs              鈫?淇敼锛宑allTool 鍖呰９鍩嬬偣
  setup-cli.mjs          鈫?淇敼锛屽畨瑁呮椂 opt-in 璇㈤棶
```

`telemetry.mjs` 鑱岃矗锛?- 璇诲彇寮€鍏筹細鐜鍙橀噺 `HWCLOUD_TELEMETRY_ENABLED=true` 鎴?`~/.huaweicloud/telemetry.json` 涓?`{"enabled":true}`
- 浜嬩欢鍏ラ槦 + 寮傛鎵归噺涓婃姤锛堥槻闃诲 MCP 鍝嶅簲锛?- 澶辫触闈欓粯锛堢绾?鍚庣涓嶅彲杈炬椂涓㈠純锛岀粷涓嶆姏閿欏奖鍝嶄富娴佺▼锛?- 鑴辨晱锛氬鐢?`redactSecrets()`锛坄safety-policy.mjs` 宸叉湁锛?
### 3.3 浜嬩欢绫诲瀷

| 浜嬩欢 | 瑙﹀彂鐐癸紙浠ｇ爜浣嶇疆锛?| 鍏抽敭瀛楁 |
|------|---------------------|----------|
| `plugin_install` | `setup-cli.mjs` install 瀹屾垚 | plugin_version, agent_target, telemetry_consent |
| `skill_load` | `tools.mjs` retrieve_skill | skill_name, skill_version |
| `cli_execute` | `tools.mjs` runHcloud 鍖呰９灞?| service, operation, risk(read/write/execution), exit_code, error_code, duration_ms |
| `error_explain` | `tools.mjs` explain_error | service, error_code |
| `vod_feedback` | `tools.mjs` 璇嗗埆鍒板紑鏀捐兘鍔涚己闄?| service, operation, issue_type, issue_desc(鑴辨晱) |

### 3.4 閬ユ祴鏁版嵁缁撴瀯

```json
{
  "schema_version": 1,
  "event_type": "cli_execute",
  "session_id": "uuid-v4",
  "event_id": "uuid-v4",
  "timestamp": "2026-08-09T10:30:00Z",
  "plugin_version": "0.1.21",
  "agent_platform": "opencode|codex|codearts|cursor|claude",
  "os": "win32|linux|darwin",
  "payload": {
    "service": "ECS",
    "operation": "CreateServers",
    "risk": "write",
    "approved": true,
    "exit_code": 0,
    "error_code": null,
    "duration_ms": 1200
  }
}
```

### 3.5 闅愮绾︽潫锛堢‖鎬э級

- **涓嶄笂鎶?*锛氬弬鏁板€笺€佹枃浠跺悕銆佷唬鐮併€丄K/SK銆乼oken銆両P
- **瀛楁鐧藉悕鍗?*锛氬彧涓婃姤鍗忚瑙勫畾鐨勫瓧娈碉紝鍏朵綑涓€寰嬩涪寮?- 涓婃姤鍐呭鍏堣繃 `redactSecrets()` 浜屾淇濋櫓
- 瀹夎鏃堕粯璁?*鍏抽棴**锛屽脊绐?`[y/N]` 璇㈤棶锛宱pt-in 鍚庢墠寮€鍚?- 閬ユ祴寮€鍏崇嫭绔嬫枃浠?`~/.huaweicloud/telemetry.json`锛宍status` 鍛戒护鍙煡鐪嬶紝`uninstall` 鏃跺垹闄?
### 3.6 涓婃姤鍗忚

```
POST https://devkit.topxtopx.com/rest/developer/server/hcdevkitportal/v1/telemetry
Content-Type: application/json
Authorization: Bearer <ingest_token>

鎵归噺锛氭瘡 60 绉掓垨鏀掓弧 20 鏉★紝鍚堝苟涓婃姤锛堝噺灏戣姹傛暟锛?閲嶈瘯锛氬け璐ラ潤榛橈紝鏈€澶氶噸璇?2 娆★紝鎸囨暟閫€閬?```

### 3.7 涓€娆℃€у畬鎴愮巼

> **閬楃暀闂 #1**锛氫换鍔＄矑搴﹀畾涔夊緟瀹氾紙鏁翠釜 Agent 浼氳瘽 / 鍗曟搷浣?/ 鎸?intent 鑱氬悎锛夈€傚綋鍓嶇増鏈笉瀹炵幇瀹屾垚鐜囩粺璁★紝浠呴噰闆嗕簨浠舵槑缁嗐€傞鐣?`session_id` 瀛楁锛屼究浜庡悗缁仛鍚堛€?
## 4. 鍚庣璁捐锛圫pring Boot锛?
### 4.1 鎶€鏈€夊瀷

| 缁勪欢 | 閫夋嫨 |
|------|------|
| 妗嗘灦 | Spring Boot 3.x (Java 17) |
| ORM | Spring Data JPA |
| 鏁版嵁搴?| 鏈湴 H2 鍐呭瓨搴擄紙mock锛夛紱娴嬭瘯/鐢熶骇 MySQL 8.0 (RDS) |
| 鏁版嵁搴撹縼绉?| Flyway锛圚2 涓?MySQL 鍧囨墽琛岋級 |
| 瀹氭椂浠诲姟 | Spring Scheduler |
| 缂撳瓨 | DCS Redis锛堟祴璇曠幆澧冿級 |

### 4.2 API 璁捐

鍓嶇紑锛歚/rest/developer/server/hcdevkitportal/v1`锛?*浠?GET/POST**銆?
| 鏂规硶 | 璺緞 | 璇存槑 | 閴存潈 |
|------|------|------|------|
| POST | `/telemetry` | 鎻掍欢鎵归噺涓婃姤閬ユ祴 | Bearer锛坕ngest token锛?|
| GET | `/metrics/downloads` | 涓嬭浇閲忚秼鍔?| 鍏紑 |
| GET | `/metrics/stars` | Star 瓒嬪娍 | 鍏紑 |
| GET | `/telemetry/summary` | 瀹屾垚鐜囪仛鍚堬紙棰勭暀锛?| 鍏紑 |
| POST | `/telemetry/query` | 閬ユ祴鏄庣粏鏌ヨ锛堝垎椤碉級 | 杩愯惀璐﹀彿 |
| GET | `/scenarios/list` | 娴嬭瘯鍦烘櫙鍒楄〃 | 杩愯惀璐﹀彿 |
| POST | `/scenarios/add` | 鏂板娴嬭瘯鍦烘櫙 | 杩愯惀璐﹀彿 |
| POST | `/scenarios/update` | 鏇存柊娴嬭瘯鍦烘櫙 | 杩愯惀璐﹀彿 |
| POST | `/scenarios/delete` | 鍒犻櫎娴嬭瘯鍦烘櫙 | 杩愯惀璐﹀彿 |
| GET | `/vod/list` | VOD 鍙嶉鍒楄〃 | 杩愯惀璐﹀彿 |
| POST | `/vod/updateStatus` | 鏇存柊 VOD 鍙嶉鐘舵€?| 杩愯惀璐﹀彿 |

绾﹀畾锛氭煡璇㈢敤 GET锛涘啓鍏?澶嶆潅鏌ヨ缁熶竴 POST锛坆ody 浼?JSON锛夈€傝祫婧愭洿鏂拌蛋 `POST /xxx/update`锛屽垹闄よ蛋 `POST /xxx/delete`銆?
### 4.3 鏁版嵁搴撹璁★紙Flyway 绠＄悊锛?
杩佺Щ鑴氭湰浣嶄簬 `backend/src/main/resources/db/migration/`锛屽懡鍚?`V1__init_schema.sql`銆乣V2__xxx.sql`銆?
```sql
-- 1. 澶栭儴鏁版嵁蹇収锛圙itHub Star/涓嬭浇銆乶pm 涓嬭浇锛?CREATE TABLE metric_snapshots (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  source        VARCHAR(32)  NOT NULL,   -- github_releases | github_stars | npm_downloads
  metric_date   DATE         NOT NULL,
  value         BIGINT       NOT NULL,
  meta          JSON,
  created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_source_date (source, metric_date)
);

-- 2. 鎻掍欢閬ユ祴浜嬩欢锛堥€愭潯鏄庣粏锛?CREATE TABLE telemetry_events (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  event_type     VARCHAR(32)  NOT NULL,  -- plugin_install | skill_load | cli_execute | error_explain | vod_feedback
  session_id     VARCHAR(64)  NOT NULL,
  event_id       VARCHAR(64)  NOT NULL UNIQUE,
  plugin_version VARCHAR(32),
  agent_platform VARCHAR(32),
  os             VARCHAR(16),
  service_name   VARCHAR(64),
  operation_name VARCHAR(128),
  risk           VARCHAR(16),
  approved       TINYINT(1),
  exit_code      INT,
  error_code     VARCHAR(64),
  duration_ms    INT,
  skill_name     VARCHAR(64),
  payload        JSON,
  created_at     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  KEY idx_session (session_id),
  KEY idx_type_date (event_type, created_at)
);

-- 3. 娴嬭瘯鍦烘櫙锛圵eb 琛ㄥ崟缁存姢锛?CREATE TABLE test_scenarios (
  id             BIGINT AUTO_INCREMENT PRIMARY KEY,
  scenario_name  VARCHAR(128) NOT NULL,
  skill_name     VARCHAR(64),
  cli_service    VARCHAR(64),
  cli_operation  VARCHAR(128),
  api_path       VARCHAR(255),
  api_method     VARCHAR(16),
  sdk_language   VARCHAR(32),
  status         VARCHAR(16) DEFAULT 'active',   -- active | archived
  last_result    VARCHAR(16),                    -- pass | fail | untested
  created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 4. VOD 鍙嶉锛堟彃浠惰瘑鍒埌寮€鏀捐兘鍔涚己闄凤級
CREATE TABLE vod_feedback (
  id              BIGINT AUTO_INCREMENT PRIMARY KEY,
  session_id      VARCHAR(64),
  service_name    VARCHAR(64),
  operation_name  VARCHAR(128),
  issue_type      VARCHAR(32),      -- api_bug | doc_gap | missing_capability
  issue_desc      VARCHAR(1000),    -- 鑴辨晱鎻忚堪
  status          VARCHAR(16) DEFAULT 'open',     -- open | acknowledged | resolved
  github_issue_url VARCHAR(255),    -- 鑻ュ凡鍦ㄦ彃浠朵粨搴撴彁 issue
  created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. 娴嬭瘯鍦烘櫙瑕嗙洊鐜囷紙棰勮绠楄仛鍚堬級
CREATE TABLE test_coverage_stats (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  stat_date     DATE NOT NULL,
  skill_count   INT,
  cli_count     INT,
  api_count     INT,
  sdk_count     INT,
  pass_count    INT,
  fail_count    INT,
  UNIQUE KEY uq_date (stat_date)
);
```

### 4.4 瀹氭椂鍚屾浠诲姟

| 浠诲姟 | 鍛ㄦ湡 | 鏁版嵁婧?| 鍐欏叆 |
|------|------|--------|------|
| GitHub Star 蹇収 | 姣忓皬鏃?| `GET api.github.com/repos/huaweicloud-mate/huaweicloud-devkit` | metric_snapshots (github_stars) |
| GitHub Release 涓嬭浇 | 姣忓皬鏃?| `GET api.github.com/repos/.../releases` | metric_snapshots (github_releases) |
| npm 涓嬭浇閲?| 姣忓皬鏃?| `GET api.npmjs.org/downloads/point/last-month/...` | metric_snapshots (npm_downloads) |
| 娴嬭瘯瑕嗙洊鐜囪仛鍚?| 姣忔棩 | test_scenarios 琛?| test_coverage_stats |

## 5. 鍓嶇璁捐锛圴ue 3锛?
### 5.1 璁捐鍩鸿皟锛堝弬鑰?http://open.topxtopx.com/openplatform/eval/index.html锛?
| 鍏冪礌 | 鍙栧€?|
|------|------|
| 鑳屾櫙 | `#f7f8fa` 娴呯伆 |
| 鍗＄墖 | 鐧藉簳銆?px `#d9dee7` 杈规銆?px 鍦嗚銆佹煍鍜岄槾褰?|
| 椤堕儴鑹插甫 | 姣忎釜鍗＄墖椤堕儴 4px 涓婚鑹诧紙teal/amber/gray/red锛?|
| 鏁板瓧 | 澶у彿 760 鏉冮噸 |
| 甯冨眬 | 1180px 灞呬腑 shell锛屽崱鐗囩綉鏍?2 鍒楋紝鍝嶅簲寮忛檷 1 鍒?|
| 瀛椾綋 | `-apple-system, BlinkMacSystemFont, "Segoe UI", "Noto Sans SC", "Microsoft YaHei"` |
| 杩涘害鏉?| 10px 楂樸€?99px 鍦嗚銆佹笎鍙樺～鍏咃紙blue鈫抰eal锛?|

### 5.2 椤甸潰缁撴瀯

```
masthead: 鍗庝负浜慏evKit杩愯惀鐪嬫澘 + 鐢熸垚鏃堕棿
  鈶?鎻掍欢鐢熸€侊紙GitHub涓嬭浇 + npm涓嬭浇 + GitHub Star 瓒嬪娍锛?  鈶?浠诲姟涓€娆℃€у畬鎴愮巼锛堢幆褰㈠浘 + 鎴愬姛/澶辫触鏁帮級銆愰鐣欍€?  鈶?娴嬭瘯鍦烘櫙瑕嗙洊锛圫kills/CLI/API/SDK 杩涘害鏉?+ 鍦烘櫙绠＄悊琛ㄥ崟锛?  鈶?VOD 鍙嶉锛堜豢鍙傝€冪珯 ISSUE 鍗＄墖锛?```

### 5.3 鎶€鏈爤

| 椤?| 閫夋嫨 |
|----|------|
| 妗嗘灦 | Vue 3 (Composition API) |
| 鏋勫缓 | Vite |
| 鍥捐〃 | ECharts锛堣秼鍔跨嚎銆佺幆褰㈠浘锛?|
| UI | 鎵嬪啓 CSS锛堢户鎵垮弬鑰冪珯鍙橀噺锛夛紝涓嶅紩鍏ラ噸鍨嬬粍浠跺簱 |
| 鐘舵€?| Pinia |
| 璇锋眰 | axios |
| 閮ㄧ讲 | 鏋勫缓浜х墿 `dist/` 涓婁紶 OBS 闈欐€佹墭绠?|

### 5.4 鍓嶇鐩綍

```
frontend/
  src/
    api/            # API 灏佽
    components/
      Card.vue       # 閫氱敤鍗＄墖锛堥《閮ㄨ壊甯︼級
      StatTile.vue   # 鏁板瓧缁熻鍧?      ProgressBar.vue
      DistributionBar.vue
    views/
      Dashboard.vue  # 涓荤湅鏉块〉
      Scenarios.vue  # 娴嬭瘯鍦烘櫙绠＄悊椤碉紙Web 琛ㄥ崟锛?      VodFeedback.vue
    router/
    main.js
  vite.config.js
```

## 6. 閮ㄧ讲鏂规

### 6.1 娴嬭瘯鐜璧勬簮锛堝崕鍗?骞垮窞 cn-south-1锛屽煙鍚?devkit.topxtopx.com锛?
| 璧勬簮 | 淇℃伅 | 鐢ㄩ€?|
|------|------|------|
| VPC | huaweicloud-agent-vpc 10.0.0.0/16 | 缃戠粶 |
| Subnet1 | huaweicloud-agent-subnet 10.0.1.0/24 | 搴旂敤瀛愮綉 |
| Subnet2 | cce-new-subnet 10.0.2.0/24 | CCE 瀛愮綉 |
| ELB | elb-hc-devkit-test 鍏綉 113.45.151.224锛岀洃鍚?443鈫?000銆?000鈫?000 | 鍚庣鍏ュ彛 |
| ECS | agent-builder 2C4G 110.41.83.215 | 鏋勫缓/鍙戝竷鏈?|
| DCS | dcs-huaweicloud-agent 涓诲16G 10.0.1.53:6379 | Redis 缂撳瓨 |
| RDS | rds-huaweicloud-agent 2C8G 10.0.1.242:3306 | MySQL |
| SNAT | cce-snat-gateway 116.205.235.201 | CCE 鍑哄叕缃?|
| CCE | cce-hd-devkit-test 10.0.2.19:5443 | 鍚庣瀹瑰櫒 |

### 6.2 閮ㄧ讲鎷撴墤

```
devkit.topxtopx.com
        鈹? CDN CNAME
        鈻?     CDN 鈹€鈹€ 璺緞璺敱 鈹€鈹€鈻?鍓嶇闈欐€?鍥炴簮 OBS) / 鍚庣 API(杞彂 ELB)
        鈹?        鈹溾攢 /rest/developer/server/hcdevkitportal/v1/*  鈹€鈹€鈻?ELB 鈹€鈹€鈻?CCE
        鈹斺攢 鍏朵綑璺緞 鈹€鈹€鈻?OBS 闈欐€佹《 (鍓嶇 dist/)

鍚庣 API 鍏ュ彛锛歨ttps://devkit.topxtopx.com/rest/developer/server/hcdevkitportal/v1
        鈹?        鈻?鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€ ELB (10.0.1.66) 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?鈹? 鐩戝惉 443  鈹€鈹€鈻?鍚庣 NodePort 3000             鈹?鈹? 鐩戝惉 3000 鈹€鈹€鈻?鍚庣 NodePort 3000             鈹?鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?                      鈻?鈹屸攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€ CCE 闆嗙兢 (10.0.2.x) 鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?鈹? Deployment: hc-devkit-dashboard (Spring Boot)鈹?鈹? 瀹瑰櫒绔彛 3000                                 鈹?鈹? 鈹€鈹€ SNAT 鈹€鈹€鈻?GitHub/npm API (鍑哄叕缃戞媺鏁版嵁)     鈹?鈹斺攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹攢鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹€鈹?                      鈻?          RDS MySQL 10.0.1.242:3306
          DCS Redis 10.0.1.53:6379
```

### 6.3 鍓嶇鍙戝竷閾捐矾

```
Vue 鏋勫缓 (vite build) 鈫?dist/
   鈫?涓婁紶 OBS 妗?(闈欐€佺綉绔欐墭绠?
   鈫?缁戝畾鍩熷悕 devkit.topxtopx.com (CDN CNAME)
   鈫?CDN 鍥炴簮 OBS
```

### 6.4 鍚庣鍙戝竷閾捐矾

```
鏂颁粨搴?push tag v0.1.0
   鈫?ECS agent-builder: mvn package 鈫?docker build
   鈫?push SWR (cn-south-1)
   鈫?kubectl 婊氬姩鏇存柊 CCE Deployment
```

### 6.5 閰嶇疆娓呭崟锛堢幆澧冨彉閲忥級

| 閰嶇疆 | 娴嬭瘯鐜鍊?| 鐢ㄩ€?|
|------|-----------|------|
| SPRING_DATASOURCE_URL | jdbc:mysql://10.0.1.242:3306/hcdevkit | 鏁版嵁搴擄紙K8s Secret锛?|
| SPRING_DATASOURCE_USERNAME | root | RDS 璐﹀彿 |
| SPRING_DATASOURCE_PASSWORD | (K8s Secret) | RDS 瀵嗙爜 |
| SPRING_REDIS_HOST | 10.0.1.53 | DCS Redis |
| SPRING_REDIS_PASSWORD | (K8s Secret) | DCS 瀵嗙爜 |
| GITHUB_TOKEN | (K8s Secret) | GitHub API 鎷夊彇 |
| INGEST_TOKEN | 鍚庣鐢熸垚 | 鎻掍欢涓婃姤閴存潈 |
| CORS_ORIGIN | https://devkit.topxtopx.com | 鍓嶇璺ㄥ煙锛堝悓鍩熸椂鍙渷锛?|

### 6.6 鏈湴寮€鍙?
- **鏈湴鍚庣**锛歚mvn spring-boot:run`锛岃繛 H2 鍐呭瓨搴擄紙Flyway 鎵ц V1 杩佺Щ锛?- **鏈湴鍓嶇**锛歚npm run dev`锛孷ite proxy 浠ｇ悊鍒板悗绔?- **鏈湴楠岃瘉**锛歝url 璋?API + 娴忚鍣ㄧ湅鐪嬫澘

## 7. 閬楃暀闂锛堝綋鍓嶇増鏈笉鍋氾級

| # | 闂 | 璇存槑 |
|---|------|------|
| 1 | 浠诲姟绮掑害瀹氫箟 | 涓€涓?浠诲姟"= 鏁翠釜浼氳瘽 / 鍗曟搷浣?/ 鎸?intent 鑱氬悎锛屽緟瀹氥€傚綋鍓嶇増鏈笉瀹炵幇瀹屾垚鐜囷紝浠呴噰闆嗘槑缁?|
| 2 | 鑷姩娴嬭瘯鏈哄櫒浜?| 浠?Web 琛ㄥ崟娴嬭瘯鍦烘櫙璇诲彇 鈫?鑷姩鎵ц娴嬭瘯鍦烘櫙锛堣繙鏈熻鍒掞級 |
| 3 | 鏂规 B 婕旇繘 | API Gateway + Kafka 鍓婂嘲锛岄噺绾т笂鏉ュ悗鍐嶅仛 |
| 4 | VOD 涓婃姤瀵规帴 | 鍙嶉鏁版嵁鍚庣画瀵规帴 VOD 鐪嬫澘涓婃姤 |
