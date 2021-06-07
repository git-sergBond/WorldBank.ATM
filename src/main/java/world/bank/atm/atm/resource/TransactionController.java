package world.bank.atm.atm.resource;

import org.springframework.web.bind.annotation.*;
import world.bank.atm.atm.dto.MoneyDto;

@RestController("/ext/atm/transaction")
public class TransactionController {

    @PostMapping("/transact/money/from/{sourceId}/to/{destinationId}")
    public void pay(@PathVariable Long sourceId, @PathVariable Long destinationId, @RequestBody MoneyDto money) {

    }

    @PostMapping("/put/money/{destinationId}")
    public void putMoney(@PathVariable Long destinationId, @PathVariable MoneyDto money) {

    }
}
