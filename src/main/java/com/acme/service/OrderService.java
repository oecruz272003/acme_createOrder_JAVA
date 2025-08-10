package com.acme.service;

import com.acme.client.InventoryClient;
import com.acme.model.*;

import java.util.UUID;

public class OrderService {

    private InventoryClient inventoryClient = new InventoryClient();
    private OrderRepository repo = new OrderRepository();

    public OrderResponse createOrder(String transactionID, OrderRequest req) {
        Product inventory = inventoryClient.checkProductInventory(req.getProductId(), transactionID);

        if (inventory == null) {
            throw new javax.ws.rs.WebApplicationException(JsonApiResponse.error("404", "Product not found", transactionID), 404);
        }

        if (inventory.getUnits() < req.getUnits()) {
            throw new javax.ws.rs.WebApplicationException(JsonApiResponse.error("409", "Insufficient stock", transactionID), 409);
        }

        String orderId = UUID.randomUUID().toString();

        OrderResponse resp = new OrderResponse();
        resp.setOrderID(orderId);
        resp.setOrderstatus("success");
        repo.saveOrder(orderId, req);

        return resp;
    }
}
