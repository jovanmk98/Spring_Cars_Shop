package salon_za_avtomobili.salon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import salon_za_avtomobili.salon.model.Avtomobil;
import salon_za_avtomobili.salon.model.ShoppingCart;
import salon_za_avtomobili.salon.service.AvtomobilService;
import salon_za_avtomobili.salon.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final AvtomobilService avtomobilService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, AvtomobilService avtomobilService) {
        this.shoppingCartService = shoppingCartService;
        this.avtomobilService = avtomobilService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products", this.shoppingCartService.listAllProducts(shoppingCart.getId()));
        return "shopping-cart";
    }

    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            String username = req.getRemoteUser();
            ShoppingCart shoppingCart = this.shoppingCartService.addProductToShoppingCart(username, id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
    @GetMapping("/add/gear/{id}")
    public String showAddGear(@PathVariable Long id, Model model){
        model.addAttribute("id",id);
        return "add-gear";
    }
    @PostMapping("/gear/{id}")
    public String addGear(@PathVariable Long id,
                          @RequestParam(required = false) Boolean klima,
                          @RequestParam(required = false) Boolean turbo,
                          @RequestParam(required = false) Boolean bandasi,
                          @RequestParam(required = false) Boolean spoiler,
                          @RequestParam(required = false) Boolean kozniSedista,
                          @RequestParam(required = false) Boolean metalic,
                          HttpServletRequest req){
        String username = req.getRemoteUser();
        Avtomobil avtomobil = this.shoppingCartService.findById(id,username).get();
        if(klima!=null && avtomobil.getKlima()==0)
            avtomobil.setKlima(300);
        if(turbo!=null && avtomobil.getTurbo()==0)
            avtomobil.setTurbo(1000);
        if(spoiler!=null && avtomobil.getSpoiler()==0)
            avtomobil.setSpoiler(200);
        if(kozniSedista!=null && avtomobil.getKozniSedista()==0)
            avtomobil.setKozniSedista(500);
        if(metalic!=null && avtomobil.getMetalic()==0)
            avtomobil.setMetalic(700);
        if(bandasi!=null && avtomobil.getBandasi()==0)
            avtomobil.setBandasi(1500);

        return "redirect:/shopping-cart";
    }
}
