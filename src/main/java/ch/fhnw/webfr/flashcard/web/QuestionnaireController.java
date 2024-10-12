package ch.fhnw.webfr.flashcard.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistance.QuestionnaireRepository;
import jakarta.validation.Valid;

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

    @GetMapping("/{id}")
	public String findById(@PathVariable("id") String id, Model model) {
        if (id == null) {
            return "404";
        }

		Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);

        if (!questionnaire.isPresent()) {
            return "404";
        }

		model.addAttribute("questionnaire", questionnaire.get());
		return "questionnaires/show";
    }

    @GetMapping("/create") // @GetMapping(params = "form")
    public String create(Model model) {
        model.addAttribute("questionnaire", new Questionnaire());
        return "questionnaires/create";
    }

    @PostMapping
    public String create(@Valid Questionnaire questionnaire, BindingResult result) {
        if (result.hasErrors()) {
            return "questionnaires/create";
        }

        questionnaireRepository.save(questionnaire);
        return "redirect:/questionnaires";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        if (id == null || !questionnaireRepository.existsById(id)) {
            return "404";
        }
        questionnaireRepository.deleteById(id);
        return "redirect:/questionnaires";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") String id, Model model) {
        if (id == null) {
            return "404";
        }

        Optional<Questionnaire> questionnaire = questionnaireRepository.findById(id);

        if (!questionnaire.isPresent()) {
            return "404";
        }

        model.addAttribute("questionnaire", questionnaire.get());
        return "questionnaires/update";
    }

    @PutMapping("/{id}")
    public String edit(@PathVariable("id") String id, @Valid Questionnaire questionnaire, BindingResult result) {
        if (id == null || !questionnaireRepository.existsById(id)) {
            return "404";
        }

        if (result.hasErrors()) {
            return "questionnaires/edit";
        }

        questionnaire.setId(id);
        questionnaireRepository.save(questionnaire);
        return "redirect:/questionnaires";
    }
    
}
