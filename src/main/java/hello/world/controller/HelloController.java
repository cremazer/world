package hello.world.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        String name = "Lucky";
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/hello-there")
    public String helloThere(
            @RequestParam("team") String team,
            @RequestParam("name") String name,
            Model model) {
        model.addAttribute("team", team);
        model.addAttribute("name", name);
        return "hello-there";
    }
}
