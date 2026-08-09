package com.huaweicloud.devkit.portal.controller;

import com.huaweicloud.devkit.portal.entity.VodFeedback;
import com.huaweicloud.devkit.portal.repository.VodFeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest/developer/server/hcdevkitportal/v1/vod")
public class VodFeedbackController {

    private final VodFeedbackRepository repo;

    public VodFeedbackController(VodFeedbackRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(defaultValue = "open") String status) {
        List<VodFeedback> feedbacks = repo.findByStatusOrderByCreatedAtDesc(status);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("feedbacks", feedbacks);
        result.put("total", feedbacks.size());
        result.put("open_count", repo.countByStatus("open"));
        result.put("acknowledged_count", repo.countByStatus("acknowledged"));
        result.put("resolved_count", repo.countByStatus("resolved"));

        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<Map<String, Object>> updateStatus(@RequestBody Map<String, Object> body) {
        Long id = Long.valueOf(body.get("id").toString());
        String newStatus = (String) body.get("status");

        VodFeedback feedback = repo.findById(id).orElse(null);
        if (feedback == null) {
            Map<String, Object> err = new LinkedHashMap<>();
            err.put("success", false);
            err.put("message", "Feedback not found");
            return ResponseEntity.badRequest().body(err);
        }

        feedback.setStatus(newStatus);
        if (body.containsKey("github_issue_url")) {
            feedback.setGithubIssueUrl((String) body.get("github_issue_url"));
        }
        repo.save(feedback);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("success", true);

        return ResponseEntity.ok(result);
    }
}
