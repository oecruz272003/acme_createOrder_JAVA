package com.acme.controller;

import com.acme.model.*;
import com.acme.service.OrderPurshaseService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/MS/purshase/MScreateOrderPurshase/V1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreateOrderPurshaseResource {

    private OrderPurshaseService service = new OrderPurshaseService();

    @POST
    @Path("/createOrder")
    public Response createOrderPurshase(@HeaderParam("transactionID") String transactionID,
                                        @HeaderParam("date") String date,
                                        @HeaderParam("X-API-Key") String apiKey,
                                        OrderRequest request) {

        if (transactionID == null || date == null || apiKey == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(JsonApiResponse.error("400", "Headers missing", transactionID))
                    .build();
        }

        try {
            OrderResponse result = service.createOrderPurshase(transactionID, date, apiKey, request);
            return Response.ok(JsonApiResponse.success(transactionID, result)).build();
        } catch (WebApplicationException wae) {
            throw wae;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(JsonApiResponse.error("500", "Internal Server Error", transactionID))
                    .build();
        }
    }
}
