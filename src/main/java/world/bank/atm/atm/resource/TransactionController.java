package world.bank.atm.atm.resource;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import world.bank.atm.atm.dto.MoneyDto;
import world.bank.atm.atm.dto.TransactionDto;
import world.bank.atm.atm.emums.CurrencyType;
import world.bank.atm.atm.service.TransactionService;

import java.math.BigDecimal;

@RestController("/ext/atm/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/money/transact")
    public void pay(@RequestBody TransactionDto transactionDto) {
        transactionService.pay(transactionDto);
    }

    @PostMapping("/put/money/{destinationId}")
    public void putMoney(@PathVariable Long destinationId, @PathVariable MoneyDto money) {

    }
}
