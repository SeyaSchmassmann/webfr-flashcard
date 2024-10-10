package ch.fhnw.webfr.flashcard.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    // redirect to /questionnaires
    @GetMapping
    public String index() {
        return "redirect:/questionnaires";
    }
    
}
