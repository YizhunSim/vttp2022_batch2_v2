package vttp2022.paf.day29.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.paf.day29.models.SuperHero;
import vttp2022.paf.day29.repositories.MarvelCache;
import vttp2022.paf.day29.services.MarvelService;

@Controller
@RequestMapping(path="/search")
public class SearchController {
    @Autowired
    private MarvelService marvelService;

    @Autowired
    private MarvelCache marvelCache;

    @GetMapping
    public String search(@RequestParam String heroName, Model model){
        List<SuperHero> results = null;

        Optional<List<SuperHero>> opt = marvelCache.get(heroName);
        if (opt.isEmpty()){
            results = marvelService.search(heroName);    
            marvelCache.cache(heroName, results);
        } else{
            results = opt.get();
            System.out.println(">>>> from CACHE\n");
        }

        model.addAttribute("results", results);

        return "result";
    }

    @GetMapping (value="/{heroId}")
    public String searchBySuperHeroId(@PathVariable(name = "heroId") String heroId, Model model){
        System.out.println("Entered here");
        SuperHero result = null;

        Optional<SuperHero> opt = marvelCache.getSuperheroById(heroId);
        if (opt.isEmpty()){
            System.out.println("Is empty");
            result = marvelService.searchById(Integer.parseInt(heroId));
            marvelCache.cacheBySuperHeroId(heroId, result);
        } else{
            System.out.println("Is not empty");
            result = opt.get();
            System.out.println(">>>> from CACHE\n");
        }

        model.addAttribute("results", result);

        return "result";
    }
}
