package edu.theCanay.webApp.DAO;

import edu.theCanay.webApp.Models.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BanksDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BanksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bank> getBanks() {
        //With the usage of query method and BankMapper class gets list of banks
        return jdbcTemplate.query("SELECT * FROM Bank", new BeanPropertyRowMapper<>(Bank.class));
    }

    public Bank getBank(int id) {
        //return the bank with the specified id or null
        return jdbcTemplate.query("SELECT * FROM Bank WHERE Id =?", new Object[] {id},
                new BeanPropertyRowMapper<>(Bank.class)).stream().findAny().orElse(null);
    }

    public void save(Bank bank) {

        jdbcTemplate.update("INSERT INTO Bank (\"name\", \"interestRate\", \"maximumLoan\", \"minimumDownPayment\", \"loanTermMonths\") VALUES (?, ?, ?, ?, ?);",
                bank.getName(),
                bank.getInterestRate(),
                bank.getMaximumLoan(),
                bank.getMinimumDownPayment(),
                bank.getLoanTermMonths());

    }

    public void update(int id, Bank updatedBank) {
        jdbcTemplate.update("UPDATE Bank SET name=?, interestrate=?, maximumloan=?, minimumdownpayment=?, loantermmonths=? WHERE id=?",
                updatedBank.getName(),
                updatedBank.getInterestRate(),
                updatedBank.getMaximumLoan(),
                updatedBank.getMinimumDownPayment(),
                updatedBank.getLoanTermMonths(),
                id);
    }

    public void deleteBank(int id) {

        jdbcTemplate.update("DELETE FROM Bank WHERE id=?", id);

    }

}
