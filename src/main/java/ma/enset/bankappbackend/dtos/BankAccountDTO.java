package ma.enset.bankappbackend.dtos;


import ma.enset.bankappbackend.enums.AccountStatus;
import lombok.Data; import java.time.LocalDate;

@Data
public class BankAccountDTO {
    private String id; private double balance;
    private LocalDate createdAt; private AccountStatus status;
    private CustomerDTO customer;
}