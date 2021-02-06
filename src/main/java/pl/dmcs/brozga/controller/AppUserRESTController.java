package pl.dmcs.brozga.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.repository.AppUserRepo;

@RestController
@RequestMapping("appUserRest")
public class AppUserRESTController {

    private AppUserRepo appUserRepo;

    @Autowired
    public AppUserRESTController(AppUserRepo appUserRepository) {
        this.appUserRepo = appUserRepository;
    }

    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = "application/json")
    public AppUser getAppUserInJSON(@PathVariable String login) {

        return appUserRepo.findByLogin(login);
    }

    @RequestMapping(value = "/{login}.xml", method = RequestMethod.GET, produces = "application/xml")
    public AppUser getAppUserInXML(@PathVariable String login) {

        return appUserRepo.findByLogin(login);
    }
}
