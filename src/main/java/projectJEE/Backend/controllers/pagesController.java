package projectJEE.Backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pages")
public class pagesController {

    @Autowired
    private pagesService pagesService;

    @PostMapping("/")
}
