package salon_za_avtomobili.salon.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import salon_za_avtomobili.salon.model.Korisnik;
import salon_za_avtomobili.salon.model.Role;
import salon_za_avtomobili.salon.model.excep.InvalidArgumentException;
import salon_za_avtomobili.salon.repository.KorisnikRep;
import salon_za_avtomobili.salon.service.KorisnikService;

@Service
public class KorisnikServiceImpl implements KorisnikService {
    private final KorisnikRep korisnikRep;
    private final PasswordEncoder passwordEncoder;
    public KorisnikServiceImpl( KorisnikRep korisnikRep, PasswordEncoder passwordEncoder) {
        this.korisnikRep = korisnikRep;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Korisnik login(String username, String password) {

        return korisnikRep.findByUsernameAndPassword(username,
                password).orElseThrow();

    }

    @Override
    public Korisnik register(String username, String password, String repeatPassword, String name, String surname, Role role) {
       if (username == null || username.isEmpty() || password == null || password.isEmpty())
       {
           throw new InvalidArgumentException();
       }
       if (!password.equals(repeatPassword))
           throw new InvalidArgumentException();
       if (this.korisnikRep.findByUsername(username).isPresent()
           || !this.korisnikRep.findByUsername(username).isEmpty()){
            throw new InvalidArgumentException();
        }
       Korisnik korisnik = new Korisnik(username, passwordEncoder.encode(password), name, surname, role);
        return korisnikRep.save(korisnik);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return korisnikRep.findByUsername(s).orElseThrow(()-> new UsernameNotFoundException(s));
    }
}
