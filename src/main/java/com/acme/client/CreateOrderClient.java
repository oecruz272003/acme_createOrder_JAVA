package com.acme.client;

import com.acme.model.OrderRequest;
import com.acme.model.OrderResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.entity.EntityBuilder;

public class CreateOrderClient {

    private static final String CREATE_ORDER_URL = "http://host/QA/MS/purshase/MScreateOrder/V1/createOrder";

    public OrderResponse createOrder(String transactionID, String date, String apiKey, OrderRequest req) {
        try {
            ObjectMapper om = new ObjectMapper();
            String reqJson = om.writeValueAsString(req);

            String response = Request.post(CREATE_ORDER_URL)
                    .addHeader("transactionID", transactionID)
                    .addHeader("date", date)
                    .addHeader("X-API-Key", apiKey)
                    .body(EntityBuilder.create().setText(reqJson).setContentType("application/json").build())
                    .execute().returnContent().asString();

            JsonNode node = om.readTree(response);
            // try to extract data or return entire node converted to OrderResponse if possible
            if (node.has("data")) {
                JsonNode d = node.get("data");
                OrderResponse or = new OrderResponse();
                if (d.has("orderID")) or.setOrderID(d.get("orderID").asText());
                if (d.has("orderstatus")) or.setOrderstatus(d.get("orderstatus").asText());
                return or;
            } else {
                return om.treeToValue(node, OrderResponse.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
