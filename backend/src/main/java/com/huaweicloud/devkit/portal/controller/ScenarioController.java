package com.huaweicloud.devkit.portal.controller;

import com.huaweicloud.devkit.portal.entity.TestScenario;
import com.huaweicloud.devkit.portal.repository.TestScenarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/developer/server/hcdevkitportal/v1/scenarios")
public class ScenarioController {

    private final TestScenarioRepository repo;

    public ScenarioController(TestScenarioRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(defaultValue = "active") String status) {
        List<TestScenario> scenarios = repo.findByStatusOrderByUpdatedAtDesc(status);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("scenarios", scenarios);
        result.put("total", scenarios.size());
        result.put("active_count", repo.countActive());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody Map<String, Object> body) {
        TestScenario scenario = new TestScenario();
        scenario.setScenarioName((String) body.get("scenario_name"));
        scenario.setSkillName((String) body.get("skill_name"));
        scenario.setCliService((String) body.get("cli_service"));
        scenario.setCliOperation((String) body.get("cli_operation"));
        scenario.setApiPath((String) body.get("api_path"));
        scenario.setApiMethod((String) body.get("api_method"));
        scenario.setSdkLanguage((String) body.get("sdk_language"));
        scenario.setStatus(Objects.toString(body.get("status"), "active"));
        scenario.setLastResult(Objects.toString(body.get("last_result"), "untested"));

        repo.save(scenario);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);
        result.put("id", scenario.getId());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Map<String, Object> body) {
        Long id = Long.valueOf(body.get("id").toString());
        TestScenario scenario = repo.findById(id).orElse(null);
        if (scenario == null) {
            Map<String, Object> err = new LinkedHashMap<>();
            err.put("success", false);
            err.put("message", "Scenario not found");
            return ResponseEntity.badRequest().body(err);
        }

        if (body.containsKey("scenario_name")) scenario.setScenarioName((String) body.get("scenario_name"));
        if (body.containsKey("skill_name")) scenario.setSkillName((String) body.get("skill_name"));
        if (body.containsKey("cli_service")) scenario.setCliService((String) body.get("cli_service"));
        if (body.containsKey("cli_operation")) scenario.setCliOperation((String) body.get("cli_operation"));
        if (body.containsKey("api_path")) scenario.setApiPath((String) body.get("api_path"));
        if (body.containsKey("api_method")) scenario.setApiMethod((String) body.get("api_method"));
        if (body.containsKey("sdk_language")) scenario.setSdkLanguage((String) body.get("sdk_language"));
        if (body.containsKey("status")) scenario.setStatus((String) body.get("status"));
        if (body.containsKey("last_result")) scenario.setLastResult((String) body.get("last_result"));

        repo.save(scenario);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Map<String, Object> body) {
        Long id = Long.valueOf(body.get("id").toString());
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);

        return ResponseEntity.ok(result);
    }
}
