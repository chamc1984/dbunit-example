package xyz.chamc.mockitoexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chamc.mockitoexample.service.RandomStringGeneratorService;

@RestController
@RequestMapping(value = "/api/example")
public class RandomStringGeneratorController {

  @Autowired
  RandomStringGeneratorService randomStringGeneratorService;

  @RequestMapping(value = "/generate/{charsize}")
  public String randomStringGenerate(@PathVariable("charsize") int charsize) {
    String res = randomStringGeneratorService.execute(charsize);
    return res;
  }
}
