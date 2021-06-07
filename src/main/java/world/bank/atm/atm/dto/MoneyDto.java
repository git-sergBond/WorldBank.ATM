package world.bank.atm.atm.dto;

import lombok.Data;
import world.bank.atm.atm.emums.CurrencyType;

import java.math.BigDecimal;

@Data
public class MoneyDto {
    BigDecimal amount;
    CurrencyType currencyType;
}
