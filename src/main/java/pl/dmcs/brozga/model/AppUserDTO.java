package pl.dmcs.brozga.model;

import java.util.HashSet;
import java.util.Set;

public class AppUserDTO {

    private String name;
    private String surname;
    private String phoneNumber;
    private Long pesel;
    private Set<String> appUserRole = new HashSet<>(0);


    public Set<String> getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(Set<String> appUserRole) {
        this.appUserRole = appUserRole;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }


}
