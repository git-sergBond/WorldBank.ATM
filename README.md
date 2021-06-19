[SWAGGER-API](http://localhost:8090/atm/swagger-ui.html)
---

Run local for development
---
```
mvn install && mvn  spring-boot:run
```

Stack
---
* Lang: Java GraalVM
* CodeGen: lombok
* Builder: Maven
* Framework: Spring Boot, WebFlux, Kafka
* API: openapi-webflux-ui
* Env: Docker, Ubuntu Server


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

For run kafka broker:
```
sudo docker run --rm --net=host lensesio/fast-data-dev
```
<a name="advhostkafka"><b>If you want to have the services remotely accessible</b></a>, then you may need to pass in your machine's <IP> address
or hostname that other machines can use to access it:
```
sudo docker run --rm --net=host -e ADV_HOST=<IP> lensesio/fast-data-dev
```
Links:
- [UI for Kafka](http://127.0.0.1:3030/)
- [Documentation to the docker image](https://github.com/lensesio/fast-data-dev)


Troubleshooting
---
If you see this error message in logs, then see full info in unit [If you want to have the services remotely accessible](#advhostkafka)
```
2021-06-10 23:01:23.074  WARN 14396 --- [| adminclient-1] org.apache.kafka.clients.NetworkClient   : [AdminClient clientId=adminclient-1] Error connecting to node ubuntu-server-1:9092 (id: 0 rack: null)

java.net.UnknownHostException: ubuntu-server-1
    at java.base/java.net.InetAddress$CachedAddresses.get(InetAddress.java:796) ~[na:na]
```
