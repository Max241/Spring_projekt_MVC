package pl.dmcs.brozga.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    long id;


    @NotNull
    @Size(min = 2, max = 30)
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

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUserRole> appUserRole = new HashSet<AppUserRole>(0);

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Pesel pesel;

    public Long getPesel1() {
        return pesel1;
    }

    public void setPesel1(Long pesel1) {
        this.pesel1 = pesel1;
    }

    private Long pesel1;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne
    private Address address;

    
    public Pesel getPesel() {
        return pesel;
    }

    public void setPesel(Pesel pesel) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

