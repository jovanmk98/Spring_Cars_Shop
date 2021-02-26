package salon_za_avtomobili.salon.service.impl;

import org.springframework.stereotype.Service;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.model.excep.InvalidArgumentException;
import salon_za_avtomobili.salon.repository.AvtomobilRep;
import salon_za_avtomobili.salon.service.AvtoSalonService;
import salon_za_avtomobili.salon.service.AvtomobilService;

import java.util.List;
import java.util.Optional;

@Service
public class AvtomobilServiceImpl implements AvtomobilService {
    private final AvtomobilRep avtomobilRepository;
    private final AvtoSalonService avtoSalonService;

    public AvtomobilServiceImpl(AvtomobilRep avtomobilRepository, AvtoSalonService avtoSalonService) {
        this.avtomobilRepository = avtomobilRepository;
        this.avtoSalonService = avtoSalonService;
    }

    @Override
    public List<Avtomobil> listAll() {
        return this.avtomobilRepository.findAll();
    }

    @Override
    public Optional<Avtomobil> findById(Long id) {
        return this.avtomobilRepository.findById(id);
    }

    @Override
    public Avtomobil save(String name, Integer price, String year, String horsepower, String image,Long salonId) {
        Avtomobil a = new Avtomobil(name, price, year, horsepower, image);
        AvtoSalon salon=this.avtoSalonService.findById(salonId).orElse(null);
        a.getAvtoSaloni().add(salon);
        this.avtomobilRepository.save(a);
        return a;
    }

    @Override
    public void deleteById(Long id) {
        this.avtomobilRepository.deleteById(id);
    }

    @Override
    public void update(Long id,String name, Integer price, String year, String horsepower, String image, Long salon) {
        Avtomobil avtomobil=this.avtomobilRepository.findById(id).orElseThrow(InvalidArgumentException::new);
        avtomobil.setName(name);
        avtomobil.setYear(year);
        avtomobil.setHorsepower(horsepower);
        AvtoSalon avtoSalon=this.avtoSalonService.findById(salon).orElseThrow(InvalidArgumentException::new);
        if(!avtomobil.getAvtoSaloni().contains(avtoSalon))
            avtomobil.getAvtoSaloni().add(avtoSalon);
        avtomobil.setImage(image);
        this.avtomobilRepository.save(avtomobil);


    }

    @Override
    public List<Avtomobil> listFromSaloon(Long id) {
        AvtoSalon avtoSalon=this.avtoSalonService.findById(id).orElseThrow(InvalidArgumentException::new);
        return this.avtomobilRepository.findByAvtoSaloniContaining(avtoSalon);
    }

}
