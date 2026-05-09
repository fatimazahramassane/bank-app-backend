package ma.enset.bankappbackend.dtos;


import ma.enset.bankappbackend.enums.OperationType;
import lombok.Data; import java.time.LocalDate;
import java.util.Date;

@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}