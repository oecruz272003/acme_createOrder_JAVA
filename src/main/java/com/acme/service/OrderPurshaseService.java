package com.acme.service;

import com.acme.client.InventoryClient;
import com.acme.client.CreateOrderClient;
import com.acme.model.*;

import jakarta.ws.rs.WebApplicationException;

public class OrderPurshaseService {

    private InventoryClient inventoryClient = new InventoryClient();
    private CreateOrderClient createOrderClient = new CreateOrderClient();

    public OrderResponse createOrderPurshase(String transactionID, String date, String apiKey, OrderRequest req) {

        Product product = inventoryClient.checkProductInventoryFull(req, transactionID, date, apiKey);

        if (product == null) {
            throw new WebApplicationException(JsonApiResponse.error("404", "Product not found", transactionID), 404);
        }

        if (product.getUnits() < req.getUnits()) {
            throw new WebApplicationException(JsonApiResponse.error("409", "Insufficient stock", transactionID), 409);
        }

        OrderResponse createResp = createOrderClient.createOrder(transactionID, date, apiKey, req);

        if (createResp == null) {
            throw new WebApplicationException(JsonApiResponse.error("502", "Upstream createOrder failed", transactionID), 502);
        }

        return createResp;
    }
}
