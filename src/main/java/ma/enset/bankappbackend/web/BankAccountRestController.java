package ma.enset.bankappbackend.web;

import ma.enset.bankappbackend.dtos.*;
import ma.enset.bankappbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @AllArgsConstructor @RequestMapping("/api/accounts")
public class BankAccountRestController {
    private BankAccountService service;

    @PostMapping
    public BankAccountDTO create(@RequestBody BankAccountDTO dto, @RequestParam Long customerId) {
        return service.saveAccount(dto, customerId);
    }

    @GetMapping
    public List<BankAccountDTO> list() { return service.listAccounts(); }

    @GetMapping("/{id}")
    public BankAccountDTO get(@PathVariable String id) { return service.getAccount(id); }

    @PostMapping("/{id}/debit")
    public AccountOperationDTO debit(@PathVariable String id, @RequestParam double amount) {
        return service.debit(id, amount);
    }

    @PostMapping("/{id}/credit")
    public AccountOperationDTO credit(@PathVariable String id, @RequestParam double amount) {
        return service.credit(id, amount);
    }

    @GetMapping("/{id}/operations")
    public List<AccountOperationDTO> history(@PathVariable String id) {
        return service.accountHistory(id);
    }
}