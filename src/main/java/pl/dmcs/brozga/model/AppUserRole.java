package pl.dmcs.brozga.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "appuserrole")
public class AppUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
