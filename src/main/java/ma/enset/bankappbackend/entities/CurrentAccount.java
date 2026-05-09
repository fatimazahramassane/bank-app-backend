package ma.enset.bankappbackend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("CURRENT")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccount extends BankAccount {
    private double overDraft;
}