package be.ucll.ip_minor_task_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping
    public String getIndex() {
        return "index";
    }


    @GetMapping("/international")
    public String getIndexLang() {
        return "index";
    }
}




