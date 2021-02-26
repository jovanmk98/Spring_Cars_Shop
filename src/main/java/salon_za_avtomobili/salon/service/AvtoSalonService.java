package salon_za_avtomobili.salon.service;

import salon_za_avtomobili.salon.model.AvtoSalon;

import java.util.List;
import java.util.Optional;

public interface AvtoSalonService {
    List<AvtoSalon> listAll();

    Optional<AvtoSalon> findById(Long id);

    AvtoSalon save(String name, String grad, String lokacija, String kapacitet);

    void deleteById(Long id);
}
