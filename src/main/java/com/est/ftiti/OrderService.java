package com.est.ftiti;

public class OrderService {

    private PaymentService paymentService; 

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public boolean placeOrder(Order order) {
        try {
            
            boolean paymentSuccessful = paymentService.processPayment(order.getAmount());
            if (paymentSuccessful) {
               
                return true;
            } else {
                return false; 
            }
        } catch (PaymentFailedException e) {
           
            System.out.println("Payment failed: " + e.getMessage());
            return false; 
        }
    }
}
