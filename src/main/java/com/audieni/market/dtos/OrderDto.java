package com.audieni.market.dtos;

import com.audieni.market.models.Order;
import com.audieni.market.models.OrderProduct;
import com.audieni.market.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private User user;
    private List<OrderProductDto> products;

    public Order toOrder() {
        Order order = new Order();
        List<OrderProduct> productList = new ArrayList<>();

        for (OrderProductDto product : products) {
            productList.add(new OrderProduct(order, product.getProduct(), product.getStock()));
        }

        order.setUser(this.user);
        order.setOrderProduct(productList);

        return order;
    }
}
