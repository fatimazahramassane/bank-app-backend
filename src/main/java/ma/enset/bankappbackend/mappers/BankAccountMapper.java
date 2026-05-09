package ma.enset.bankappbackend.mappers;
import ma.enset.bankappbackend.dtos.*;
import ma.enset.bankappbackend.entities.*;
import org.springframework.stereotype.Component;

@Component
public class BankAccountMapper {
    public CustomerDTO fromCustomer(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId()); dto.setName(c.getName()); dto.setEmail(c.getEmail());
        return dto;
    }

    public BankAccountDTO fromBankAccount(BankAccount acc) {
        BankAccountDTO dto;
        if (acc instanceof CurrentAccount) {
            CurrentAccountDTO c = new CurrentAccountDTO();
            c.setId(acc.getId()); c.setBalance(acc.getBalance());
            c.setCreatedAt(acc.getCreatedAt()); c.setStatus(acc.getStatus());
            c.setCustomer(fromCustomer(acc.getCustomer()));
            c.setOverDraft(((CurrentAccount) acc).getOverDraft());
            dto = c;
        } else if (acc instanceof SavingsAccount) {
            SavingsAccountDTO s = new SavingsAccountDTO();
            s.setId(acc.getId()); s.setBalance(acc.getBalance());
            s.setCreatedAt(acc.getCreatedAt()); s.setStatus(acc.getStatus());
            s.setCustomer(fromCustomer(acc.getCustomer()));
            s.setInterestRate(((SavingsAccount) acc).getInterestRate());
            dto = s;
        } else {
            dto = new BankAccountDTO();
            dto.setId(acc.getId()); dto.setBalance(acc.getBalance());
            dto.setCreatedAt(acc.getCreatedAt()); dto.setStatus(acc.getStatus());
            dto.setCustomer(fromCustomer(acc.getCustomer()));
        }
        return dto;
    }

    public AccountOperationDTO fromOperation(AccountOperation op) {
        AccountOperationDTO dto = new AccountOperationDTO();
        dto.setId(op.getId()); dto.setOperationDate(op.getOperationDate());
        dto.setAmount(op.getAmount()); dto.setType(op.getType());
        return dto;

    }
}