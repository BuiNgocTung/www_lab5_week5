package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entities.CandidateSkill;
import vn.edu.iuh.fit.ids.CandidateSkillId;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
}