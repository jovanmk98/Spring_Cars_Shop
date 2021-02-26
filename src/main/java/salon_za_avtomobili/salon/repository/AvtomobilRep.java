package salon_za_avtomobili.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.model.Avtomobil;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvtomobilRep extends JpaRepository<Avtomobil, Long> {
    public Optional<Avtomobil> findById(Long id);
    public List<Avtomobil> findByAvtoSaloniContaining(AvtoSalon avtoSalon);
}
