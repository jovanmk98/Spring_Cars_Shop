package salon_za_avtomobili.salon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import salon_za_avtomobili.salon.model.Korisnik;
import salon_za_avtomobili.salon.model.excep.InvalidArgumentException;
import salon_za_avtomobili.salon.service.KorisnikService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final KorisnikService korisnikService;

    public LoginController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        Korisnik korisnik = null;
        try {
            korisnik = this.korisnikService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("korisnik", korisnik);
            return "redirect:/home";
        } catch (InvalidArgumentException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

}
