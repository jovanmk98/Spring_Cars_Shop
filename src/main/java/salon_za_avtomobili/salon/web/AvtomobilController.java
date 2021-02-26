package salon_za_avtomobili.salon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salon_za_avtomobili.salon.model.AvtoSalon;
import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.service.AvtoSalonService;
import salon_za_avtomobili.salon.service.AvtomobilService;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class AvtomobilController {
    private final AvtomobilService avtomobilService;
    private final AvtoSalonService avtoSalonService;

    public AvtomobilController(AvtomobilService avtomobilService, AvtoSalonService avtoSalonService) {
        this.avtomobilService = avtomobilService;
        this.avtoSalonService = avtoSalonService;
    }


    @GetMapping("/list")
    public String getCarsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Avtomobil> carsList = this.avtomobilService.listAll();
        model.addAttribute("carsList", carsList);
        return "carsPage";
    }

    @GetMapping("/list/{id}")
    public String getCarsFromSaloon(@RequestParam(required = false) String error,@PathVariable Long id, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Avtomobil> carsList = this.avtomobilService.listFromSaloon(id);
        model.addAttribute("carsList", carsList);
        return "carsPage";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.avtomobilService.deleteById(id);
        return "redirect:/cars/list";
    }

    @GetMapping("/addCar")
    public String showFormForAddAvtomobil(Model theModel) {

        // create model attribute to bind form data
        Avtomobil car = new Avtomobil();

        theModel.addAttribute("car", car);
        List<AvtoSalon> avtosaloni=this.avtoSalonService.listAll();
        theModel.addAttribute("saloni",avtosaloni);

        return "add-car";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {

        Avtomobil car = this.avtomobilService.findById(id).get();
        model.addAttribute("car", car);
        List<AvtoSalon> avtosaloni=this.avtoSalonService.listAll();
        model.addAttribute("saloni",avtosaloni);
        return "add-car";


    }

    @PostMapping("/add/{id}")
    public String update(@PathVariable Long id,
            @RequestParam Long salon,
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String year,
            @RequestParam String horsepower,
            @RequestParam String image,
                         Model model
    ) {
        this.avtomobilService.update(id,name, price, year, horsepower, image,salon);
        return "redirect:/cars/list";
    }

    @PostMapping("/add")
    public String saveUser(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String year,
            @RequestParam String horsepower,
            @RequestParam Long salon,
            @RequestParam String image, Model model
    ) {
        Avtomobil avtomobil=this.avtomobilService.save(name, price, year, horsepower, image, salon);
        return "redirect:/cars/list";
    }
}
