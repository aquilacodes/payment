package saga.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private Map<String, Boolean> paymentStore = new HashMap<>();
    private boolean simulateFailure = false;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate payment processing
            paymentStore.put("payment123", true);
            return ResponseEntity.ok("Payment processed");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Payment processing failed");
        }
    }

    @PostMapping("/compensate")
    public ResponseEntity<String> compensatePayment() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate payment compensation
            paymentStore.remove("payment123");
            return ResponseEntity.ok("Payment compensation successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Payment compensation failed");
        }
    }

    @PostMapping("/simulateFailure")
    public void simulateFailure(@RequestParam boolean simulateFailure) {
        this.simulateFailure = simulateFailure;
    }
}