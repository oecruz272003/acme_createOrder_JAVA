package com.acme.client;

import com.acme.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;

public class InventoryClient {

    private static final String CHECK_INVENTORY_BASE = "http://host/QA/MS/purshase/MScheckInventory/V1/chekProductInventory/";

    public Product checkProductInventory(String productId, String transactionID) {
        try {
            String url = CHECK_INVENTORY_BASE + productId;
            String response = Request.get(url)
                    .addHeader("transactionID", transactionID)
                    .addHeader("date", java.time.OffsetDateTime.now().toString())
                    .addHeader("X-API-Key", "dummy")
                    .connectTimeout(5000)
                    .execute().returnContent().asString();

            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readTree(response);
            if (node.has("product") && node.get("product").has("productID")) {
                Product p = new Product();
                p.setProductID(node.get("product").get("productID").asText());
                p.setUnits(node.get("product").get("units").asInt(0));
                return p;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Product checkProductInventoryFull(OrderRequest req, String transactionID, String date, String apiKey) {
        try {
            String url = CHECK_INVENTORY_BASE + req.getProductId();
            String response = Request.get(url)
                    .addHeader("transactionID", transactionID)
                    .addHeader("date", date)
                    .addHeader("X-API-Key", apiKey)
                    .connectTimeout(5000)
                    .execute().returnContent().asString();

            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readTree(response);
            if (node.has("product") && node.get("product").has("productID")) {
                Product p = new Product();
                p.setProductID(node.get("product").get("productID").asText());
                p.setUnits(node.get("product").get("units").asInt(0));
                return p;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
