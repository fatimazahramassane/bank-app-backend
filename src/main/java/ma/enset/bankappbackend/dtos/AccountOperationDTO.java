package ma.enset.bankappbackend.dtos;


import ma.enset.bankappbackend.enums.OperationType;
import lombok.Data; import java.time.LocalDate;

@Data
public class AccountOperationDTO {
    private Long id; private LocalDate operationDate;
    private double amount; private OperationType type;
}