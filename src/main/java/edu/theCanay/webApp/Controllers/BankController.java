package edu.theCanay.webApp.Controllers;

import edu.theCanay.webApp.DAO.BanksDAO;
import edu.theCanay.webApp.Models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BanksDAO banksDAO;

    @Autowired
    public BankController(BanksDAO banksDAO) {
        this.banksDAO = banksDAO;
    }

    @GetMapping()
    public String basePage(Model model) {
        //On this page, a user should be able to see the list
        //of all earlier created banks and create/edit/remove the bank
        model.addAttribute("banks", banksDAO.getBanks());

        return "banks/banks";
    }

    @GetMapping("/{name}")
    public String bank(@PathVariable("name") String name, Model model) {

        model.addAttribute("bank", banksDAO.getBank(name));

        return "banks/bankInfo";
    }

    @GetMapping("/new")
    public String newBank(Model model) {

        model.addAttribute("bank", new Bank());

        return "banks/bankCreation";
    }

    @PostMapping
    public String createBank(@ModelAttribute("bank") Bank bank) {
        banksDAO.save(bank);
        return "redirect:/banks";
    }

}
