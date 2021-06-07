package world.bank.atm.atm.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import world.bank.atm.atm.emums.CurrencyType;
import world.bank.atm.atm.serializer.BigDecimalSerializer;

import java.math.BigDecimal;

@Data
public class MoneyDto {

    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal amount;

    CurrencyType currencyType;
}
