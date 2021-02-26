package salon_za_avtomobili.salon.repository;

import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.bootstrap.DataHolder;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.model.Avtomobil;

import java.util.List;
import java.util.Optional;

@Repository
public class AvtoSalonRepository {
    public List<AvtoSalon> listAll() {
        return DataHolder.salonList;
    }

    public Optional<AvtoSalon> findById(Long id) {
        return DataHolder.salonList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


    public void deleteById(Long id) {
        DataHolder.salonList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<AvtoSalon> save(String name, String grad, String lokacija, String kapacitet
    ) {
        DataHolder.salonList.removeIf(i -> i.getName().equals(name));
        AvtoSalon salon = new AvtoSalon(name, grad, lokacija, kapacitet);
        DataHolder.salonList.add(salon);
        return Optional.of(salon);
    }
}
