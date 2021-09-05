package pl.dmcs.brozga.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.dmcs.brozga.model.VisitHoursDTO;
import pl.dmcs.brozga.model.VisitHours;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitHoursService {

    void addVisitHours(VisitHoursDTO visitHoursDTO);

    boolean hasDoctorVisitingHours(Long doctorId, LocalDateTime startDate, LocalDateTime endDate);

    void cancelVisitHoursForAdmin(Long id);

    void cancelVisitHoursForDoctor(Long doctorId, Long id);

    Page<VisitHours> getVisitHoursNotCancelledForAdmin(Pageable pageable);

    Page<VisitHours> getVisitHoursNotCancelledForDoctor(Long doctorId, Pageable pageable);

    VisitHours getSingleVisitHours(Long id);

    List<VisitHours> getVisitHoursNotCancelledByDoctor(Long doctorId);
}
