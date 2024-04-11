package com.example.service;

import com.example.model.Order;
import com.example.model.User;
import com.example.request.OrderRequest;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;
    public Order updateOrder(Long orderId,String orderStatus) throws Exception;
    public void cancelOrder(Long OrderId) throws Exception;
    public List<Order> getUserOrders(Long userid) throws Exception;
    public List<Order> getRestaurantOrders(Long restaurantId,String orderStatus)throws Exception;
    public Order findOrderById(Long orderId) throws Exception;

}
