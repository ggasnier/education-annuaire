package com.guillaumegasnier.education.web.controllers;

import com.guillaumegasnier.education.web.dto.formations.ActionFormationDto;
import com.guillaumegasnier.education.web.dto.formations.FormationDto;
import com.guillaumegasnier.education.web.services.WebFormationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/formations")
public class FormationController {

    private final WebFormationService webFormationService;

    public FormationController(WebFormationService webFormationService) {
        this.webFormationService = webFormationService;
    }

    @GetMapping("/{id}")
    public String getFormation(@PathVariable long id, Model model) {

        FormationDto formation = webFormationService.getFormation(id);

        model.addAttribute("formation", formation);

        // Les actions


        return "formations/details";
    }

    @GetMapping("/{id}/actions/{actionId}")
    public String getAction(@PathVariable long id, @PathVariable long actionId, Model model) {

        ActionFormationDto action = webFormationService.getActionFormation(id, actionId);

        model.addAttribute("action", action);


        return "formations/action";
    }
}
