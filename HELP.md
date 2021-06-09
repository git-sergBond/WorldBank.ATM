Kafka
---
Producer integration with
```
Correlation schema: MessageID to ReplyID
MessageID save to (kafka-topic)atm-callback
To payload of message put replyTo="atm-reply"

ms-atm => (kafka-topic)atm-request => ms-WorldBank
ms-atm => (kafka-topic)atm-callback
ms-atm <= (kafka-topic)atm-reply   <= ms-WorldBank
ms-atm <= (kafka-topic)atm-callback
```

For run kafka broker ([Documentation to the docker image](https://github.com/lensesio/fast-data-dev)):
```
sudo docker run --rm --net=host lensesio/fast-data-dev
```


