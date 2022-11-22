package com.api.rest.Controller;
import com.api.rest.Model.Fact;
import com.api.rest.Repo.FactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private FactRepo factRepo;

    @GetMapping("/")
    public String getPage() {
        return  "Welcome to the random pokemon fact page.<br><br>" +
                "To get a list of all facts, go to /facts <br>" +
                "To get a random fact, go to /randomFact <br>" +
                "To get a fact by id, go to /facts/{id} <br>" +
                "To add a fact, go to /new <br>" +
                "To delete a fact, go to /delete/{id} <br>" +
                "To update a fact, go to /update/{id} <br>";
    }

    @GetMapping("/facts")
    public List<Fact> getFacts() {
        return factRepo.findAll();
    }

    @GetMapping("/randomFact")
    public Fact getRandomFact() {
        List<Fact> facts = factRepo.findAll();
        int randomIndex = (int) (Math.random() * facts.size());
        return facts.get(randomIndex);
    }

    @GetMapping("/facts/{id}")
    public Fact getFact(@PathVariable long id) {
        return factRepo.findById(id).get();
    }

    @PostMapping("/new")
    public String newFact(@RequestBody Fact fact) {
        factRepo.save(fact);
        return "Fact added";
    }

    @PutMapping(value = "/update/{id}")
    public String updateFact(@PathVariable long id, @RequestBody Fact fact) {
        Fact updateFact = factRepo.findById(id).get();
        updateFact.setFact(fact.getFact());
        factRepo.save(updateFact);
        return "Fact updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteFact(@PathVariable long id) {
        Fact deleteFact = factRepo.findById(id).get();
        factRepo.delete(deleteFact);
        return "Fact with id " + id + " deleted";
    }
}
