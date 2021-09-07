package pl.dmcs.brozga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.repository.AppUserRepo;
import pl.dmcs.brozga.service.AppUserService;

import java.util.List;

@RestController
@RequestMapping("appUserRest")
public class AppUserRESTController {

    private AppUserRepo appUserRepo;

    @Autowired
    public AppUserRESTController(AppUserRepo appUserRepository) {
        this.appUserRepo = appUserRepository;
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserByLoginInJSON(@PathVariable String login) {

        return appUserRepo.findByLogin(login);
    }

    @RequestMapping(value = "/login/{login}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserByLoginInXML(@PathVariable String login) {

        return appUserRepo.findByLogin(login);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserByEmailInJSON(@PathVariable String name) {

        return appUserRepo.findByName(name);
    }

    @RequestMapping(value = "/name/{name}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserByEmailInXML(@PathVariable String name) {

        return appUserRepo.findByName(name);
    }
}
