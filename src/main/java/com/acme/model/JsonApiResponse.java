package com.acme.model;

import java.util.HashMap;
import java.util.Map;

public class JsonApiResponse {
    private Map<String,Object> meta = new HashMap<>();
    private Object data;

    public static JsonApiResponse success(String transactionID, Object dataObj) {
        JsonApiResponse r = new JsonApiResponse();
        r.meta.put("status","200");
        r.meta.put("message","Solicitud exitosa");
        r.meta.put("transactionID", transactionID);
        r.data = dataObj;
        return r;
    }

    public static JsonApiResponse error(String status, String message, String transactionID) {
        JsonApiResponse r = new JsonApiResponse();
        r.meta.put("status", status);
        r.meta.put("message", message);
        r.meta.put("transactionID", transactionID);
        r.data = null;
        return r;
    }

    public Map<String, Object> getMeta() { return meta; }
    public void setMeta(Map<String, Object> meta) { this.meta = meta; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}
