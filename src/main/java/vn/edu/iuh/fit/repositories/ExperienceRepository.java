package vn.edu.iuh.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.entities.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}