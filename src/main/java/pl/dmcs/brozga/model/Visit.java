package pl.dmcs.brozga.model;


import javax.persistence.*;
import java.time.LocalDateTime;


@Table
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean cancelled;

    private boolean approved;

    @ManyToOne
    private AppUser patient;

    @ManyToOne
    private VisitHours visitHours;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public AppUser getPatient() {
        return patient;
    }

    public void setPatient(AppUser patient) {
        this.patient = patient;
    }

    public VisitHours getVisitHours() {
        return visitHours;
    }

    public void setVisitHours(VisitHours visitHours) {
        this.visitHours = visitHours;
    }
}
