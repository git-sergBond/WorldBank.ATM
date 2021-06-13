package world.bank.atm.atm.dto;

import lombok.Data;

@Data
public class TransactionDto {

    Long sourceId;

    Long destinationId;

    MoneyDto money;
}
