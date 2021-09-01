package pl.dmcs.brozga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.dmcs.brozga.model.VisitHours;
import pl.dmcs.brozga.model.VisitHoursDTO;
import pl.dmcs.brozga.repository.VisitHoursRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VisitHoursServiceImpl implements VisitHoursService {

    private VisitHoursRepo visitHoursRepo;
    private AppUserService appUserService;

    public VisitHoursServiceImpl(VisitHoursRepo visitHoursRepo, AppUserService appUserService) {
        this.visitHoursRepo = visitHoursRepo;
        this.appUserService = appUserService;
    }

    @Override
    public void addVisitHours(VisitHoursDTO editedVisitHours) {
        VisitHours visitHours = new VisitHours();

        visitHours.setStartDate(editedVisitHours.getStartDate());
        visitHours.setVisitCost(editedVisitHours.getVisitCost());
        visitHours.setVisitLength(editedVisitHours.getVisitLength());
        visitHours.setVisitsCount(editedVisitHours.getVisitsCount());
        visitHours.setDoctor(appUserService.getAppUser(editedVisitHours.getDoctorId()));
        visitHours.setEndDate(visitHours.getStartDate().plusMinutes(visitHours.getVisitLength() * visitHours.getVisitsCount()));

        visitHoursRepo.save(visitHours);
    }

    @Override
    public boolean hasDoctorVisitingHours(Long doctorId, LocalDateTime startDate, LocalDateTime endDate) {
        return visitHoursRepo.existsByCancelledIsFalseAndDoctorIdAndStartDateIsBetweenOrEndDateIsBetween(
                doctorId,
                startDate,
                endDate,
                startDate,
                endDate);
    }

    @Override
    public void cancelVisitHoursForAdmin(Long id) {
        Optional<VisitHours> visitHours = visitHoursRepo.findById(id);
        visitHours.ifPresent(hours -> {
            hours.setCancelled(true);
            visitHoursRepo.save(hours);
        });
    }

    @Override
    public void cancelVisitHoursForDoctor(Long doctorId, Long id) {
        Optional<VisitHours> visitHours = visitHoursRepo.findByIdAndDoctorId(id, doctorId);
        visitHours.ifPresent(hours -> {
            hours.setCancelled(true);
            visitHoursRepo.save(hours);
        });
    }

    @Override
    public Page<VisitHours> getVisitHoursNotCancelledForAdmin(Pageable pageable) {
        return visitHoursRepo.findAllByCancelledIsFalseOrderByStartDate(pageable);
    }

    @Override
    public Page<VisitHours> getVisitHoursNotCancelledForDoctor(Long doctorId, Pageable pageable) {
        return visitHoursRepo.findAllByCancelledIsFalseAndDoctorIdOrderByStartDate(doctorId, pageable);
    }
}
