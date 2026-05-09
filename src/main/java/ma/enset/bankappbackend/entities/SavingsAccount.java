package ma.enset.bankappbackend.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("SAVINGS")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount extends BankAccount {
    private double interestRate;
}