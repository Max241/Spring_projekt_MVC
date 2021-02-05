package pl.dmcs.brozga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dmcs.brozga.model.AppUserRole;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface AppUserRoleRepo extends JpaRepository<AppUserRole, Long> {
    AppUserRole findByRole(String role);
}
