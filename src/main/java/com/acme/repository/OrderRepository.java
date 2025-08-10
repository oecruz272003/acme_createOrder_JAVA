package com.acme.repository;

import com.acme.model.OrderRequest;

import java.util.concurrent.ConcurrentHashMap;

public class OrderRepository {
    private static ConcurrentHashMap<String, OrderRequest> store = new ConcurrentHashMap<>();

    public void saveOrder(String id, OrderRequest request) {
        store.put(id, request);
    }
}
