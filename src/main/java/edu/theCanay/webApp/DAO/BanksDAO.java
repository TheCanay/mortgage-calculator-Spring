package edu.theCanay.webApp.DAO;

import edu.theCanay.webApp.Models.Bank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BanksDAO {


    private final List<Bank> banks = new ArrayList<>();

    {
        banks.add(new Bank("PrivatBank", 7, 300000, 60000));
        banks.add(new Bank("OschadBank", 10, 250000, 60000));
        banks.add(new Bank("AlfaBank", 9, 350000, 55000));
        banks.add(new Bank("Universal", 8, 300000, 60000));
        banks.add(new Bank("MonoBank", 8, 200000, 30000));
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public Bank getBank(String name) {

        return banks.stream().filter(bank -> bank.getName().equals(name)).findAny().orElse(null);
    }

    public void save(Bank bank) {
        banks.add(bank);
    }


}
