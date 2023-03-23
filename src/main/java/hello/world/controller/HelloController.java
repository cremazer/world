package hello.world.controller;

import hello.world.model.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /**
     * Web browser call url
     * http://localhost:8080/hello
     */
    @GetMapping("/hello")
    public String hello(Model model) {
        String name = "Lucky";
        model.addAttribute("name", name);
        return "hello";
    }

    /**
     * Web browser call url
     * http://localhost:8080/hello-there?team=Dev&name=Lucky
     * @param team Dev
     * @param name Lucky
     * @return viewName
     */
    @GetMapping("/hello-there")
    public String helloThere(
            @RequestParam("team") String team,
            @RequestParam("name") String name,
            Model model) {
        model.addAttribute("team", team);
        model.addAttribute("name", name);
        return "hello-there";
    }

    /**
     * Web browser call url
     * http://localhost:8080/hello-string?team=Dev&name=Lucky
     * @param team Dev
     * @param name Lucky
     * @return String
     */
    @GetMapping("/hello-string")
    @ResponseBody
    public String helloString(
            @RequestParam("team") String team,
            @RequestParam("name") String name) {
        return team + "/" + name;
    }

    /**
     * Web browser call url
     * http://localhost:8080/hello-api?team=Dev&name=Lucky
     * @param team
     * @param name
     * @return
     */
    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(
            @RequestParam("team") String team,
            @RequestParam("name") String name) {
        return Hello.builder()
                .team(team)
                .name(name)
                .build();
    }
}
