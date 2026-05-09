package ma.enset.bankappbackend.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("SAVINGS")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class SavingsAccount extends BankAccount {
    private double interestRate;
}