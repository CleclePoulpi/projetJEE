
package projectJEE.Backend.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * This class is used to manage the pages
 */
@Controller
@RequestMapping("/")
public class pagesController {
    /**
     * This method is used to get the index page
     * @return the index page
     */
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.ht-ml");
        return modelAndView;
    }

    /**
     * This method is used to get the athletes page
     * @return the athletes page
     */
    @ResponseBody
    @GetMapping("/athletes")
    public ModelAndView athletes() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("athletes.html");
        return modelAndView;
    }

    /**
     * This method is used to get the athletesAdmin page
     * @return the athletesAdmin page
     */
    @ResponseBody
    @GetMapping("/athletesAdmin")
    public ModelAndView athletesAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("athletesAdmin.html");
        return modelAndView;
    }

    /**
     * This method is used to get the eventsAdmin page
     * @return the eventsAdmin page
     */
    @ResponseBody
    @GetMapping("/EventAdmin")
    public ModelAndView EventAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("eventAdmin.html");
        return modelAndView;
    }

    /**
     * This method is used to get the events page
     * @return the events page
     */
    @ResponseBody
    @GetMapping("/events")
    public ModelAndView events() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("events.html");
        return modelAndView;
    }

    /**
     * This method is used to get the locations page
     * @return the locations page
     */
    @ResponseBody
    @GetMapping("/locations")
    public ModelAndView localisations() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("locations.html");
        return modelAndView;
    }

    /**
     * This method is used to get the locationsAdmin page
     * @return the locationsAdmin page
     */
    @ResponseBody
    @GetMapping("/locationsAdmin")
    public ModelAndView localisationsAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("locationsAdmin.html");
        return modelAndView;
    }

    /**
     * This method is used to get the plan page
     * @return the plan page
     */
    @ResponseBody
    @GetMapping("/plan")
    public ModelAndView plan() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("plan.html");
        return modelAndView;
    }

    /**
     * This method is used to get the sports page
     * @return the sports page
     */
    @ResponseBody
    @GetMapping("/sports")
    public ModelAndView sports() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sports.html");
        return modelAndView;
    }

    /**
     * This method is used to get the sportsAdmin page
     * @return the sportsAdmin page
     */
    @ResponseBody
    @GetMapping("/sportsAdmin")
    public ModelAndView sportsAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sportsAdmin.html");
        return modelAndView;
    }

    /**
     * This method is used to get the stats page
     * @return the stats page
     */
    @ResponseBody
    @GetMapping("/stats")
    public ModelAndView stats() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stats.html");
        return modelAndView;
    }
}