package salon_za_avtomobili.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.model.User;

import javax.persistence.Id;

@Repository
public interface UserRep extends JpaRepository<User, Long> {
}
