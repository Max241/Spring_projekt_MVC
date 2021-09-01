package pl.dmcs.brozga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.Visit;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface VisitRepo extends JpaRepository<Visit, Long> {
}
