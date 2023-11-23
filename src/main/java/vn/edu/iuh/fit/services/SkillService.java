package vn.edu.iuh.fit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.dto.SkillInformation;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.ids.JobSkillId;
import vn.edu.iuh.fit.repositories.JobSkillRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    public List<SkillInformation> getInfoSkillOfJob(long jobId){
        List<Skill> skills = skillRepository.findAllByJobId(jobId);
        List<SkillInformation> skillInformation = new ArrayList<>();
        for (Skill skill : skills){
            JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(jobId, skill.getId())).get();
            skillInformation.add(new SkillInformation(skill, jobSkill.getSkillLevel(), jobSkill.getMoreInfo()));
        }
        return skillInformation;
    }
}
