package ma.enset.bankappbackend.entities;

import ma.enset.bankappbackend.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate operationDate;
    private double amount;
    @Enumerated(EnumType.STRING) private OperationType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private BankAccount bankAccount;
}