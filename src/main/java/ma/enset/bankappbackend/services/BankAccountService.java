package ma.enset.bankappbackend.services;

import ma.enset.bankappbackend.dtos.*;
import ma.enset.bankappbackend.entities.*;
import ma.enset.bankappbackend.enums.OperationType;
import ma.enset.bankappbackend.mappers.BankAccountMapper;
import ma.enset.bankappbackend.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @Transactional @AllArgsConstructor
public class BankAccountService {
    private BankAccountRepository accountRepo;
    private CustomerRepository customerRepo;
    private AccountOperationRepository operationRepo;
    private BankAccountMapper mapper;

    public BankAccountDTO saveAccount(BankAccountDTO dto, Long customerId) {
        Customer c = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        BankAccount acc = (dto instanceof CurrentAccountDTO) ?
                new CurrentAccount(UUID.randomUUID().toString(), dto.getBalance(),
                        LocalDate.now(), dto.getStatus(), c, ((CurrentAccountDTO) dto).getOverDraft()) :
                new SavingsAccount(UUID.randomUUID().toString(), dto.getBalance(),
                        LocalDate.now(), dto.getStatus(), c, ((SavingsAccountDTO) dto).getInterestRate());
        return mapper.fromBankAccount(accountRepo.save(acc));
    }

    public List<BankAccountDTO> listAccounts() {
        return accountRepo.findAll().stream().map(mapper::fromBankAccount).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BankAccountDTO getAccount(String id) {
        BankAccount acc = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return mapper.fromBankAccount(acc);
    }

    public AccountOperationDTO debit(String id, double amount) {
        BankAccount acc = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (amount > acc.getBalance() + (acc instanceof CurrentAccount ? ((CurrentAccount) acc).getOverDraft() : 0))
            throw new RuntimeException("Insufficient balance");
        AccountOperation op = new AccountOperation(null, LocalDate.now(), amount, OperationType.DEBIT, acc);
        acc.setBalance(acc.getBalance() - amount);
        operationRepo.save(op);
        return mapper.fromOperation(op);
    }

    public AccountOperationDTO credit(String id, double amount) {
        BankAccount acc = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        AccountOperation op = new AccountOperation(null, LocalDate.now(), amount, OperationType.CREDIT, acc);
        acc.setBalance(acc.getBalance() + amount);
        operationRepo.save(op);
        return mapper.fromOperation(op);
    }

    public List<AccountOperationDTO> accountHistory(String id) {
        BankAccount acc = accountRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return acc.getOperations().stream().map(mapper::fromOperation).collect(Collectors.toList());
    }
}