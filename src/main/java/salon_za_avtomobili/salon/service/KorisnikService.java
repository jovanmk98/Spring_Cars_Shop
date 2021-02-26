package salon_za_avtomobili.salon.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import salon_za_avtomobili.salon.model.Korisnik;
import salon_za_avtomobili.salon.model.Role;

public interface KorisnikService extends UserDetailsService {


    Korisnik login(String username, String password);

    Korisnik register(String username, String password, String repeatPassword, String name, String surname, Role role);



}
