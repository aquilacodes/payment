package saga.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentControllerTest {

    private final PaymentController paymentController = new PaymentController();

    @Test
    public void testProcessPayment_Success() {
        paymentController.simulateFailure(false);
        ResponseEntity<String> response = paymentController.processPayment();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Payment processed", response.getBody());
    }

    @Test
    public void testProcessPayment_Failure() {
        paymentController.simulateFailure(true);
        ResponseEntity<String> response = paymentController.processPayment();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Payment processing failed", response.getBody());
    }

    @Test
    public void testCompensatePayment_Success() {
        paymentController.simulateFailure(false);
        ResponseEntity<String> response = paymentController.compensatePayment();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Payment compensation successful", response.getBody());
    }

    @Test
    public void testCompensatePayment_Failure() {
        paymentController.simulateFailure(true);
        ResponseEntity<String> response = paymentController.compensatePayment();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Payment compensation failed", response.getBody());
    }
}
