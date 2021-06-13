package world.bank.atm.atm.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import world.bank.atm.atm.dto.MoneyDto;
import world.bank.atm.atm.emums.CurrencyType;
import world.bank.atm.atm.service.TransactionService;

import java.math.BigDecimal;

@RestController("/ext/atm/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transact/money/from/{sourceId}/to/{destinationId}")
    public void pay(@PathVariable Long sourceId, @PathVariable Long destinationId, @RequestBody MoneyDto money) {
        transactionService.pay(sourceId,destinationId, money);
    }

    @PostMapping("/put/money/{destinationId}")
    public void putMoney(@PathVariable Long destinationId, @PathVariable MoneyDto money) {

    }
}
