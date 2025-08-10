# acme_createOrder

Proyecto Java (JAX-RS) que contiene:
- MScreateOrder
- MScreateOrderPurshase (nuevo) que orquesta MScheckInventory -> MScreateOrder
- MScheckInventory is expected to be available at http://host/QA/MS/purshase/MScheckInventory/V1/chekProductInventory/{productId}

Build:
  mvn clean package

Run:
  java -jar target/acme_createOrder-1.0.0-SNAPSHOT.jar

Test example (curl):
  curl -X POST "http://localhost:8080/MS/purshase/MScreateOrderPurshase/V1/createOrder" \
    -H "Content-Type: application/json" \
    -H "transactionID: BSKUE-091FGGE-FFSD33" \
    -H "date: 2025-07-14T16:55:44.992" \
    -H "X-API-Key: dummy" \
    -d '{"productId":"000000000000001234","units":1,"price":100,"clientID":"CUST001"}'
