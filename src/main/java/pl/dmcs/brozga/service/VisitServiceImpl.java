package pl.dmcs.brozga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.AppUser;
import pl.dmcs.brozga.model.Visit;
import pl.dmcs.brozga.model.VisitHours;
import pl.dmcs.brozga.repository.VisitRepo;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VisitServiceImpl implements VisitService {
    private VisitHoursService visitHoursService;
    private VisitRepo visitRepo;


    public VisitServiceImpl(VisitHoursService visitHoursService, VisitRepo visitRepo) {
        this.visitHoursService = visitHoursService;
        this.visitRepo = visitRepo;
    }

    @Override
    public boolean addVisit(AppUser user, Long visitHoursId) {
        VisitHours visitHours = visitHoursService.getSingleVisitHours(visitHoursId);
        if (isBookingAvailable(visitHoursId, user.getId()) && !visitHours.getDoctor().getId().equals(user.getId())) {
            Visit visit = new Visit();
            visit.setPatient(user);
            visit.setVisitHours(visitHours);
            visitRepo.save(visit);
            return true;
        }
        return false;
    }

    @Override
    public boolean isBookingAvailable(Long visitHoursId, Long userId) {
        VisitHours visitHours = visitHoursService.getSingleVisitHours(visitHoursId);
        if (visitHours != null) {
            LocalDateTime visitDate = visitHours.getStartDate().plusMinutes(visitHours.getVisitLength());
            return (!visitRepo.existsByCancelledIsFalseAndVisitHoursId(visitHoursId) &&
                    visitDate.isAfter(LocalDateTime.now()) &&
                    !visitRepo.existsByCancelledIsFalseAndVisitHoursIdAndPatientId(visitHoursId, userId));
        }
        return false;
    }

    @Override
    public Page<Visit> getVisitsPageForAdmin(Pageable pageable) {
        return visitRepo.findAllByOrderByVisitHoursStartDate(pageable);
    }

    @Override
    public Page<Visit> getVisitsPageForDoctor(Long doctorId, Pageable pageable) {
        return visitRepo.findAllByVisitHoursDoctorIdOrderByVisitHoursStartDate(doctorId, pageable);
    }

    @Override
    public Page<Visit> getVisitsPageForUser(Long userId, Pageable pageable) {
        return visitRepo.findAllByPatientIdOrderByVisitHoursStartDate(userId, pageable);
    }

    @Override
    public void cancelVisitForAdmin(Long visitId) {
        Optional<Visit> optVisit = visitRepo.findById(visitId);
        optVisit.ifPresent(visit -> {
            visit.setCancelled(true);
            visitRepo.save(visit);
        });
    }

    @Override
    public void cancelVisitForDoctor(Long visitId, Long doctorId) {
        Optional<Visit> optVisit = visitRepo.findByIdAndVisitHoursDoctorIdAndCancelledIsFalse(visitId, doctorId);
        optVisit.ifPresent(visit -> {
            visit.setCancelled(true);
            visitRepo.save(visit);
        });
    }

    @Override
    public void cancelVisitForPatient(Long visitId, Long patientId) {
        Optional<Visit> optVisit = visitRepo.findByIdAndPatientIdAndCancelledIsFalse(visitId, patientId);
        optVisit.ifPresent(visit -> {
            visit.setCancelled(true);
            visitRepo.save(visit);
        });
    }

    @Override
    public void approveVisitForAdmin(Long visitId) {
        Optional<Visit> optVisit = visitRepo.findById(visitId);
        optVisit.ifPresent(visit -> {
            visit.setApproved(true);
            visitRepo.save(visit);
        });
    }

    @Override
    public void approveVisitForDoctor(Long visitId, Long doctorId) {
        Optional<Visit> optVisit = visitRepo.findByIdAndVisitHoursDoctorIdAndCancelledIsFalse(visitId, doctorId);
        optVisit.ifPresent(visit -> {
            visit.setApproved(true);
            visitRepo.save(visit);
        });
    }

    @Override
    public Visit getVisit(Long id) {
        return visitRepo.findById(id).orElse(null);
    }
    
    @Override
    public void approveVisitsAuto() {
        List<Visit> visits = visitRepo.findAllByApprovedIsFalseAndCancelledIsFalse();
        visits.forEach(visit -> {
            visit.setApproved(true);
            visitRepo.save(visit);
        });
    }

}
