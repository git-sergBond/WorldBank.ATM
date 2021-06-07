package world.bank.atm.atm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import world.bank.atm.atm.dto.MoneyDto;

@Service
@AllArgsConstructor
public class TransactionService {
    public void pay(Long sourceId,Long destinationId, MoneyDto money) {

    }
}
