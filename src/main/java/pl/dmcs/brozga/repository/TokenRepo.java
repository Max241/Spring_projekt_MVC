package pl.dmcs.brozga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.Token;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

}
