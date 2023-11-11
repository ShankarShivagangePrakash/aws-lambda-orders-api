package com.ordersapi.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordersapi.model.Order;

public class CreateOrderLambda {
    public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = null;
        try{
          order = objectMapper.readValue(request.getBody(), Order.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        DynamoDB dynamoDB = new DynamoDB(AmazonDynamoDBClientBuilder.defaultClient());
        Table table = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
        Item item = new Item()
                .withPrimaryKey("id", order.getId())
                .withString("itemName", order.getItemName())
                .withInt("qty", order.getQty());
        table.putItem(item);
        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("order id: " + order.getId());
    }
}
