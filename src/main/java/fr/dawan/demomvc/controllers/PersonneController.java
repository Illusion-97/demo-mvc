package fr.dawan.demomvc.controllers;

import fr.dawan.demomvc.models.entities.Personne;
import fr.dawan.demomvc.models.repositories.PersonneRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/personnes") // Correspond à un nom de dossier dans templates
public class PersonneController {

    private final PersonneRepository repository;

    public PersonneController(PersonneRepository repository) {
        this.repository = repository;
    }

    @GetMapping // GET -> /personnes
    public String all(Model model) {
        model.addAttribute("elements",repository.findAll());
        return "personnes/all"; // Correspond au chemin vers une page html depuis le dossier template
    }

    @GetMapping(value ="/{id}") // GET -> /personnes/0
    public String byId(@PathVariable long id, Model model) {
        model.addAttribute("element", repository.findById(id).orElse(new Personne()));
        return "personnes/byId";
    }

    @PostMapping // POST -> /personnes
    public String save(Personne personne, BindingResult result) {
        repository.save(personne);
        return "redirect:/personnes";
    }

    @GetMapping("/delete/{id}") // DELETE -> /personnes/delete/0
    public String delete(@PathVariable long id) {
        repository.deleteById(id);
        return "redirect:/personnes";
    }

    /* Font la même chose
    public ModelAndView all() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("elements",repository.findAll());
        mav.setViewName("personnes/all");
        return mav;
    }

    public ModelAndView all() {
        return new ModelAndView(
                "personnes/all",
                Map.of(
                        "elements",repository.findAll()
                )
        );
    }*/
}
