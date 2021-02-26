package salon_za_avtomobili.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.model.AvtoSalon;

@Repository
public interface AvtoSalonRep extends JpaRepository<AvtoSalon, Long> {
}
