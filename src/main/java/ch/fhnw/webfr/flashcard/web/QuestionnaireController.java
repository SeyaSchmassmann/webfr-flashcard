package ch.fhnw.webfr.flashcard.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.webfr.flashcard.domain.Questionnaire;
import ch.fhnw.webfr.flashcard.persistance.QuestionnaireRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class QuestionnaireController {
    
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @RequestMapping("/questionnaires")
    public void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
		List<Questionnaire> questionnaires = questionnaireRepository.findAll();
		
		PrintWriter writer = response.getWriter();
		writer.append("<html><head><title>Example</title></head><body>");
		writer.append("<h3>Fragebögen</h3>");
		
		for (Questionnaire questionnaire : questionnaires) {
			
			String url = request.getContextPath() + request.getServletPath();
			url = url + "/" + questionnaire.getId().toString();
			
			writer.append("<p><a href='" + response.encodeURL(url) + "'>" + questionnaire.getTitle() + "</a></p>");
		}
		
		writer.append("</body></html>");
    }

    @RequestMapping("/questionnaires/{id}")
    public void findById(@PathVariable("id") String id, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Questionnaire questionnaire = questionnaireRepository.findById(id).get();
        
        PrintWriter writer = response.getWriter();
        writer.append("<html><head><title>Example</title></head><body>");
        writer.append("<h3>Fragebogen</h3>");
        writer.append("<p>" + questionnaire.getTitle() + "</p>");
        writer.append("<p>" + questionnaire.getDescription() + "</p>");
        String url = request.getContextPath();
        writer.append("<p><a href='" + response.encodeURL(url) + "/questionnaires'>Back</a></p>");
        writer.append("</body></html>");
    }
    
}
