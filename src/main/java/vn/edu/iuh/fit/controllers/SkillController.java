package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.iuh.fit.dto.SkillInformation;
import vn.edu.iuh.fit.entities.*;
import vn.edu.iuh.fit.entities.Job;
import vn.edu.iuh.fit.entities.JobSkill;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;
import vn.edu.iuh.fit.ids.JobSkillId;
import vn.edu.iuh.fit.repositories.JobRepository;
import vn.edu.iuh.fit.repositories.JobSkillRepository;
import vn.edu.iuh.fit.repositories.SkillRepository;
import vn.edu.iuh.fit.services.SkillService;

import java.util.List;

@Controller
public class SkillController {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobSkillRepository jobSkillRepository;


    @GetMapping("/{jobId}/skills")
    public String showJobList(Model model, @PathVariable("jobId") long jobId){
        List<SkillInformation> skillInformation = skillService.getInfoSkillOfJob(jobId);
        Job job = jobRepository.findById(jobId).orElse(null);
        model.addAttribute("skills", skillInformation);
        model.addAttribute("job", job);
//        JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(job, skills));
        return "skill/listSkill";
    }

    @GetMapping("/{jobId}/skill/show-add-form")
    public ModelAndView addSkill(@PathVariable("jobId") long jobId){
        ModelAndView modelAndView = new ModelAndView();
        JobSkill jobSkill = new JobSkill();
        List<Skill> skillJobNotHave = skillRepository.notFindAllByJobId(jobId);
        Skill skill = new Skill();
        modelAndView.addObject("jobSkill", jobSkill);
        modelAndView.addObject("skills", skillJobNotHave);
        modelAndView.addObject("skillLevel", SkillLevel.values());
        modelAndView.addObject("skill", skill);
        modelAndView.addObject("jobId", jobId);
        modelAndView.setViewName("skill/add");
        return modelAndView;
    }

    @PostMapping("/{jobId}/skill/addSkill")
    public String addSkill(@PathVariable("jobId") long jobId,
            @ModelAttribute("jobSkill") JobSkill jobSkill,
            @ModelAttribute("skill") Skill skill,
            BindingResult result, Model model
    ){
        Job job = jobRepository.findById(jobId).orElse(null);
        jobSkill.setSkill(skillRepository.findById(skill.getId()).orElse(null));
        jobSkill.setJob(job);
        jobSkillRepository.save(jobSkill);
        String uri;
        uri = "redirect:/"+jobId+"/skills";
        return uri;
    }

    @PostMapping("/{jobId}/{skillId}/deleteSkill")
    public String deleteSkill(@PathVariable("jobId") long jobId,
                              @PathVariable("skillId") long skillId
    ){
        System.out.println("\n\n\n\n\n\nOK");
        JobSkill jobSkill = jobSkillRepository.findById(new JobSkillId(jobId, skillId)).get();
        jobSkillRepository.delete(jobSkill);
        String uri;
        uri = "redirect:/"+jobId+"/skills";
        return uri;
    }
}
