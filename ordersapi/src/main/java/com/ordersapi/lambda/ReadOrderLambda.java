package com.ordersapi.lambda;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordersapi.model.Order;

public class ReadOrderLambda {
    public APIGatewayProxyResponseEvent getOrder(APIGatewayProxyRequestEvent request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return new APIGatewayProxyResponseEvent().withStatusCode(200)
                .withBody(objectMapper.writeValueAsString(new Order(123, "Mac Book Pro", 3)));
    }
}
