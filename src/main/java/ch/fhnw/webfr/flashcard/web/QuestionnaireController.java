package ch.fhnw.webfr.flashcard.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistance.QuestionnaireRepository;

@Controller
@RequestMapping("/questionnaires")
public class QuestionnaireController {
    
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @GetMapping
    public String findAll(Model model) {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        model.addAttribute("questionnaires", questionnaires);
		return "questionnaires/list";
    }

    @GetMapping(params = "form")
    public String create(Model model) {
        model.addAttribute("questionnaire", new Questionnaire());
        return "questionnaires/create";
    }

    @GetMapping("/{id}")
	public String findById(@PathVariable("id") String id, Model model) {
		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);
		model.addAttribute("questionnaire", questionnaire.get());
		return "questionnaires/show";
    }

    @PostMapping
    public String create(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
        return "redirect:/questionnaires";
    }
    
}
