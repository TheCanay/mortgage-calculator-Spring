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


    //Get request controller for showing main page of mortgage calculator
    @GetMapping()
    public String basePage(Model model) {
        //On this page, a user should be able to see the list
        //of all earlier created banks and create/edit/remove the bank
        model.addAttribute("banks", banksDAO.getBanks());

        return "banks/banks";
    }

    //Get request controller for showing page with detailed banks info
    @GetMapping("/{id}")
    public String bank(@PathVariable("id") int id, Model model) {

        model.addAttribute("bank", banksDAO.getBank(id));

        return "banks/bankInfo";
    }

    //Get request controller for showing page with new bank creation
    @GetMapping("/new")
    public String newBank(Model model) {

        model.addAttribute("bank", new Bank());

        return "banks/bankCreation";
    }

    //POST request controller for adding new person to a DB after /banks/new page was opened
    @PostMapping
    public String createBank(@ModelAttribute("bank") Bank bank) {
        banksDAO.save(bank);
        return "redirect:/banks";
    }

    //Get request controller for showing bank editing page
    @GetMapping("/{id}/edit")
    public String editBank(@PathVariable("id") int id, Model model) {

        model.addAttribute("bank", banksDAO.getBank(id));

        return "banks/bankEdit";
    }

    //UPDATE (PATCH) request controller for updating bank data after /banks/{id}/edit page was opened
    @PatchMapping("/{id}")
    public String updateBank(@ModelAttribute("bank") Bank bank, @PathVariable("id") int id) {
        banksDAO.update(id, bank);

        return "redirect:/banks";
    }

    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable("id") int id) {

        banksDAO.deleteBank(id);

        return "redirect:/banks";
    }
}
