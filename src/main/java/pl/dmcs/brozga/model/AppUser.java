package pl.dmcs.brozga.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "appuser") //Table name if not name of class will be used
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @Size(min = 2, max = 30, message = "{error.size.name}")
    @Column(name = "name", nullable = false) // Column name
    private String name;

    @NotNull
    @Size(min = 2, max = 30, message = "{error.size.surname}")
    private String surname;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    @NotNull
    @Column(unique = true)
    private String login;

    @NotNull
    @JsonIgnore
    private String password;

    private boolean enabled = false;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUserRole> appUserRole = new HashSet<>(0);

    @OneToOne
    @JsonIgnore
    private Token token;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "patient")
    private Set<Visit> visit = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor")
    private Set<VisitHours> visitHours = new HashSet<>(0);


    @NotNull
    private Long pesel;

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<AppUserRole> getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(Set<AppUserRole> appUserRole) {
        this.appUserRole = appUserRole;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Set<Visit> getVisit() {
        return visit;
    }

    public void setVisit(Set<Visit> visit) {
        this.visit = visit;
    }

    public Set<VisitHours> getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(Set<VisitHours> visitHours) {
        this.visitHours = visitHours;
    }
}

