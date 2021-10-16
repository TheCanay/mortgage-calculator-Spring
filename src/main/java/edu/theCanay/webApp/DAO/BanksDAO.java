package edu.theCanay.webApp.DAO;

import edu.theCanay.webApp.Models.Bank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BanksDAO {

    private static int BANK_COUNTER;

    private final List<Bank> banks = new ArrayList<>();

    {
        banks.add(new Bank(++BANK_COUNTER, "PrivatBank", 7, 300000, 60000));
        banks.add(new Bank(++BANK_COUNTER, "OschadBank", 10, 250000, 60000));
        banks.add(new Bank(++BANK_COUNTER, "AlfaBank", 9, 350000, 55000));
        banks.add(new Bank(++BANK_COUNTER, "Universal", 8, 300000, 60000));
        banks.add(new Bank(++BANK_COUNTER, "MonoBank", 8, 200000, 30000));
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public Bank getBank(int id) {

        return banks.stream().filter(bank -> bank.getId() == id).findAny().orElse(null);
        //return banks.stream().filter(bank -> bank.getName().equals(name)).findAny().orElse(null);
    }

    public void save(Bank bank) {
        bank.setId(++BANK_COUNTER);
        banks.add(bank);
    }

    public void update(int id, Bank updatedBank) {
        Bank bankToBeUpdated = getBank(id);

        bankToBeUpdated.setName(updatedBank.getName());
        bankToBeUpdated.setInterestRate(updatedBank.getInterestRate());
        bankToBeUpdated.setMaximumLoan(updatedBank.getMaximumLoan());
        bankToBeUpdated.setMinimumDownPayment(updatedBank.getMinimumDownPayment());
    }

    public void deleteBank(int id) {
        banks.removeIf(bank -> bank.getId() == id);
    }

}
