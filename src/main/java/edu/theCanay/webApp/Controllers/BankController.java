package edu.theCanay.webApp.Controllers;

import edu.theCanay.webApp.DAO.BanksDAO;
import edu.theCanay.webApp.Models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import edu.theCanay.webApp.Services.mortgageCalculatorService;

import javax.validation.Valid;

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

        return "banks/banksList";
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
    //Valid annotation to comply with the validation rules described in Bank.java
    @PostMapping
    public String createBank(@ModelAttribute("bank") @Valid Bank bank,
                             BindingResult bindingResult) {

        //if Bank failed validation return to the new bank creation page
        if (bindingResult.hasErrors()) {
            return "banks/bankCreation";
        }

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
    //Valid annotation to comply with the validation rules described in Bank.java
    @PatchMapping("/{id}")
    public String updateBank(@ModelAttribute("bank") @Valid Bank bank,
                             BindingResult bindingResult,
                             @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "banks/bankEdit";
        }

        banksDAO.update(id, bank);

        return "redirect:/banks";
    }

    //DELETE request handler for deleting chosen bank from model
    @DeleteMapping("/{id}")
    public String deleteBank(@PathVariable("id") int id) {

        banksDAO.deleteBank(id);

        return "redirect:/banks";
    }

    @GetMapping("/mortgageCalculator")
    public String mortgageCalculator(Model model) {

        model.addAttribute("banks", banksDAO.getBanks());

        return "banks/mortgageCalculator";
    }

    @PostMapping("/mortgageCalculator")
    public String mortgageCalculatorResult(@RequestParam("selectedBankId") Integer selectedBankId,
                                           @RequestParam("initLoan") Integer initLoan,
                                           @RequestParam("downPayment") Integer downPayment,
                                           Model model) {

        String calculationResult;

        boolean isError;

        if (mortgageCalculatorService.isValuesValid(initLoan, downPayment, banksDAO.getBank(selectedBankId))) {
            double mortgageMonthlyPayment = mortgageCalculatorService.calculateMonthlyMortgagePayment(initLoan,
                    banksDAO.getBank(selectedBankId));

            calculationResult = "The monthly payment in the bank " + banksDAO.getBank(selectedBankId).getName() +
                    " on the loan in the amount of " + initLoan + " is: " + String.format("%.2f", mortgageMonthlyPayment);

            isError = false;

        } else {
            isError = true;

            calculationResult = "Check the information on your maximum loan amount and monthly payment at the selected bank";
        }

        model.addAttribute("banks", banksDAO.getBanks());
        model.addAttribute("isError", isError);
        model.addAttribute("selectedBank", banksDAO.getBank(selectedBankId));
        model.addAttribute("calculationResult", calculationResult);

        return "banks/mortgageCalculator";
    }
}
