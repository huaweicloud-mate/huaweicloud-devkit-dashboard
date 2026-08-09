package com.huaweicloud.devkit.portal.repository;

import com.huaweicloud.devkit.portal.entity.TestScenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestScenarioRepository extends JpaRepository<TestScenario, Long> {

    List<TestScenario> findByStatusOrderByUpdatedAtDesc(String status);

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.status = 'active'")
    long countActive();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.skillName IS NOT NULL AND s.status = 'active'")
    long countActiveWithSkill();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.cliService IS NOT NULL AND s.status = 'active'")
    long countActiveWithCli();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.apiPath IS NOT NULL AND s.status = 'active'")
    long countActiveWithApi();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.sdkLanguage IS NOT NULL AND s.status = 'active'")
    long countActiveWithSdk();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.lastResult = 'pass' AND s.status = 'active'")
    long countPass();

    @Query("SELECT COUNT(s) FROM TestScenario s WHERE s.lastResult = 'fail' AND s.status = 'active'")
    long countFail();
}
