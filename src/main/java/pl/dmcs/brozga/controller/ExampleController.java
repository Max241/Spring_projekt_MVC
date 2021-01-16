package pl.dmcs.brozga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

    @RequestMapping(value = "/exampleOne")
    public String exampleOne() {
        return "exampleOne";
    }

    @RequestMapping(value = "/exampleTwo")
    public String exampleTwo() {
        return "exampleTwo";
    }

    @RequestMapping(value = "/exampleThree")
    public String exampleThree() {
        return "exampleThree";
    }

}
