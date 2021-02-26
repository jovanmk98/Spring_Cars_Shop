package salon_za_avtomobili.salon.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import salon_za_avtomobili.salon.model.User;
import salon_za_avtomobili.salon.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/list")
    public String getUsersPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<User> usersList = this.userService.listAll();
        model.addAttribute("usersList", usersList);
        return "usersPage";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        this.userService.deleteById(id);
        return "redirect:/users/list";
    }

    @GetMapping("/adduser")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        User user = new User();

        theModel.addAttribute("user", user);

        return "add-form";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {

        User user = this.userService.findById(id).get();
        this.userService.deleteById(id);
        model.addAttribute("user", user);
        return "add-form";


    }


    @PostMapping("/add")
    public String saveUser(

            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email

    ) {
        this.userService.save(name, surname, email);
        return "redirect:/users/list";
    }

}
