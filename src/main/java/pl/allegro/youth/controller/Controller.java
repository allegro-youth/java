package pl.allegro.youth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/")
public class Controller
{
    @RequestMapping("hello")
 public String hello (){
     return "hello";
 }
}
