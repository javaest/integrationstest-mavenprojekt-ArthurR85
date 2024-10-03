package com.est.ftiti;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private PaymentService paymentService; 

    @InjectMocks
    private OrderService orderService; 

    @Test
    public void testSuccessfulPayment() {
        
        when(paymentService.processPayment(anyDouble()))
            .thenReturn(true); 

        Order order = new Order(100.0);
        boolean result = orderService.placeOrder(order);

        assertTrue(result, "Bestellung sollte erfolgreich abgeschlossen werden");

        
        verify(paymentService).processPayment(order.getAmount());
    }

    @Test
    public void testFailedPayment() {
        
        when(paymentService.processPayment(anyDouble()))
            .thenThrow(new PaymentFailedException("Payment failed"));

        Order order = new Order(100.0); 

        boolean result = orderService.placeOrder(order);

        assertFalse(result, "Bestellung sollte nicht abgeschlossen werden, wenn Zahlung fehlschlägt");

        verify(paymentService).processPayment(order.getAmount());
    }
}
