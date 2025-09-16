package com.spring.boot.vm;

public interface OrderProductProjection {
    Long getOrderId();
    String getOrderCode();
    double getTotalPrice();
    double getTotalNumber();
    String getProductName();
}
