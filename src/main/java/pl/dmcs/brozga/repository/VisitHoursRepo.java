package pl.dmcs.brozga.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dmcs.brozga.model.VisitHours;

import java.time.LocalDateTime;
import java.util.Optional;

public interface VisitHoursRepo extends JpaRepository<VisitHours, Long> {

    Optional<VisitHours> findById(Long id);

    Optional<VisitHours> findByIdAndDoctorId(Long id, Long doctorId);

    boolean existsByCancelledIsFalseAndDoctorIdAndStartDateIsBetweenOrEndDateIsBetween(Long doctor_id, LocalDateTime startDate, LocalDateTime startDate2, LocalDateTime endDate, LocalDateTime endDate2);

    Page<VisitHours> findAllByCancelledIsFalseAndDoctorIdOrderByStartDate(Long doctorId, Pageable pageable);

    Page<VisitHours> findAllByCancelledIsFalseOrderByStartDate(Pageable pageable);


}
