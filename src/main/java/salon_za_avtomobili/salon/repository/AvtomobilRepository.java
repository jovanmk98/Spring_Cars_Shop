package salon_za_avtomobili.salon.repository;

import org.springframework.stereotype.Repository;
import salon_za_avtomobili.salon.bootstrap.DataHolder;
import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class AvtomobilRepository {
    public List<Avtomobil> listAll() {
        return DataHolder.carList;
    }

    public Optional<Avtomobil> findById(Long id) {
        return DataHolder.carList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


    public void deleteById(Long id) {
        DataHolder.carList.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Avtomobil> save(String name, String price, String year, String horsepower, String image
    ) {
        DataHolder.carList.removeIf(i -> i.getName().equals(name));
        Avtomobil car = new Avtomobil(name, price, year, horsepower, image);
        DataHolder.carList.add(car);
        return Optional.of(car);
    }
}
