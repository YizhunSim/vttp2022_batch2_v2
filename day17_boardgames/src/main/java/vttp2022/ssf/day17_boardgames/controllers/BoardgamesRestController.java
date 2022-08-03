package vttp2022.ssf.day17_boardgames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.ssf.day17_boardgames.services.BoardgameService;

@RestController
@RequestMapping("/boardgames")
public class BoardGamesRestController {

  @Autowired
  private BoardgameService bgService;

  @GetMapping
}
