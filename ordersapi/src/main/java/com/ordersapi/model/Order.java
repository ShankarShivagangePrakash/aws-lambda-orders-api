package com.ordersapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private int id;
    private String itemName;
    private int qty;
}
