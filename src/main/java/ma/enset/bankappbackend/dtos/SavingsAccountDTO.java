package ma.enset.bankappbackend.dtos;

import lombok.Data; import lombok.EqualsAndHashCode;
@Data @EqualsAndHashCode(callSuper = true)
public class SavingsAccountDTO extends BankAccountDTO { private double interestRate; }