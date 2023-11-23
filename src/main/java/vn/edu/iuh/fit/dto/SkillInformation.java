package vn.edu.iuh.fit.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.entities.Skill;
import vn.edu.iuh.fit.enums.SkillLevel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillInformation {
    Skill skill;
    SkillLevel skillLevel;
    private String moreInfo;
}
