
package projectJEE.Backend.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class pagesController {

    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.ht-ml");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/athletes")
    public ModelAndView athletes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("athletes.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/EventAdmin")
    public ModelAndView EventAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventAdmin.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/events")
    public ModelAndView events() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/locations")
    public ModelAndView localisations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("locationsAdmin.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/plan")
    public ModelAndView plan() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plan.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/sports")
    public ModelAndView sports() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sportsAdmin.html");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping("/stats")
    public ModelAndView stats() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stats.html");
        return modelAndView;
    }
}