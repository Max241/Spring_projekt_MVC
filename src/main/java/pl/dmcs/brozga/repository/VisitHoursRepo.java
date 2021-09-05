package pl.dmcs.brozga.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.dmcs.brozga.model.VisitHours;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VisitHoursRepo extends JpaRepository<VisitHours, Long> {

    Optional<VisitHours> findById(Long id);

    Optional<VisitHours> findByIdAndDoctorId(Long id, Long doctorId);

    Page<VisitHours> findAllByCancelledIsFalseAndDoctorIdOrderByStartDate(Long doctorId, Pageable pageable);

    Page<VisitHours> findAllByCancelledIsFalseOrderByStartDate(Pageable pageable);

    List<VisitHours> findAllByCancelledIsFalseAndDoctorIdAndEndDateAfterOrderByStartDateAsc(Long doctorId, LocalDateTime now);

    @Query("SELECT COUNT(V) FROM VisitHours AS V WHERE V.doctor.id = :doctorId AND V.cancelled = false AND ((V.startDate >= :startDate AND V.startDate <= :endDate) OR (V.endDate >= :startDate AND V.endDate <= :endDate) OR (:startDate >= V.startDate AND :startDate <= V.endDate))")
    Long findCountByDoctorIdAndStartDateAndEndDate(@Param("doctorId") Long doctorId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
