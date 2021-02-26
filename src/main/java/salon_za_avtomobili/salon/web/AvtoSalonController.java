package salon_za_avtomobili.salon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.model.User;
import salon_za_avtomobili.salon.service.AvtoSalonService;

import java.util.List;

@Controller
@RequestMapping("/salon")
public class AvtoSalonController {
    private final AvtoSalonService avtoSalonService;

    public AvtoSalonController(AvtoSalonService avtoSalonService) {
        this.avtoSalonService = avtoSalonService;
    }

    @GetMapping("/list")
    public String getSalonPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<AvtoSalon> salonList = this.avtoSalonService.listAll();
        model.addAttribute("salonList", salonList);
        return "salonPage";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSalon(@PathVariable Long id) {
        this.avtoSalonService.deleteById(id);
        return "redirect:/salon/list";
    }

    @GetMapping("/addsalon")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        AvtoSalon salon = new AvtoSalon();

        theModel.addAttribute("salon", salon);

        return "add-salon";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {

        AvtoSalon salon = this.avtoSalonService.findById(id).get();
        this.avtoSalonService.deleteById(id);
        model.addAttribute("salon", salon);
        return "add-salon";


    }


    @PostMapping("/add")
    public String saveSalon(

            @RequestParam String name,
            @RequestParam String grad,
            @RequestParam String lokacija,
            @RequestParam String kapacitet

    ) {
        this.avtoSalonService.save(name, grad, lokacija, kapacitet);
        return "redirect:/salon/list";
    }
}
