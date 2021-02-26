package salon_za_avtomobili.salon.service.impl;

import org.springframework.stereotype.Service;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.repository.AvtoSalonRep;
import salon_za_avtomobili.salon.service.AvtoSalonService;

import java.util.List;
import java.util.Optional;

@Service
public class AvtoSalonServiceImpl implements AvtoSalonService {
    private final AvtoSalonRep avtoSalonRepository;

    public AvtoSalonServiceImpl(AvtoSalonRep avtoSalonRepository) {
        this.avtoSalonRepository = avtoSalonRepository;
    }

    @Override
    public List<AvtoSalon> listAll() {
        return this.avtoSalonRepository.findAll();
    }

    @Override
    public Optional<AvtoSalon> findById(Long id) {
        return this.avtoSalonRepository.findById(id);
    }

    @Override
    public AvtoSalon save(String name, String grad, String lokacija, String kapacitet) {
        AvtoSalon a = new AvtoSalon(name, grad, lokacija, kapacitet);
        this.avtoSalonRepository.save(a);
        return a;
    }

    @Override
    public void deleteById(Long id) {
        this.avtoSalonRepository.deleteById(id);
    }
}
