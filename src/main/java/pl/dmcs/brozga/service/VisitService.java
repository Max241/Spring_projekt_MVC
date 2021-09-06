package pl.dmcs.brozga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.Visit;

public interface VisitService {

    boolean addVisit(AppUser appUser, Long visitHoursId);

    boolean isBookingAvailable(Long visitHoursId, Long userId);

    Page<Visit> getVisitsPageForAdmin(Pageable pageable);

    Page<Visit> getVisitsPageForDoctor(Long doctorId, Pageable pageable);

    Page<Visit> getVisitsPageForUser(Long userId, Pageable pageable);

    void cancelVisitForAdmin(Long visitId);

    void cancelVisitForDoctor(Long visitId, Long doctorId);

    void cancelVisitForPatient(Long visitId, Long patientId);

    void approveVisitForAdmin(Long visitId);

    void approveVisitForDoctor(Long visitId, Long doctorId);

    void approveVisitsAuto();

    Visit getVisit(Long id);

}
