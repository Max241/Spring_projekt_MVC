package pl.dmcs.brozga.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.Visit;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {

    Optional<Visit> findById(Long id);

    Optional<Visit> findByIdAndVisitHoursDoctorIdAndCancelledIsFalse(Long id, Long doctorId);

    Optional<Visit> findByIdAndPatientIdAndCancelledIsFalse(Long id, Long patientId);

    Page<Visit> findAllByOrderByVisitHoursStartDate(Pageable pageable);

    Page<Visit> findAllByVisitHoursDoctorIdOrderByVisitHoursStartDate(Long doctorId, Pageable pageable);

    Page<Visit> findAllByPatientIdOrderByVisitHoursStartDate(Long patientId, Pageable pageable);

    List<Visit> findAllByApprovedIsFalseAndCancelledIsFalse();

    boolean existsByCancelledIsFalseAndVisitHoursId(Long visitHoursId);

    boolean existsByCancelledIsFalseAndVisitHoursIdAndPatientId(Long visitHoursId, Long patientId);
}
