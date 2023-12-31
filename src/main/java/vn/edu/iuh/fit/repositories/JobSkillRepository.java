package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.ids.JobSkillId;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}