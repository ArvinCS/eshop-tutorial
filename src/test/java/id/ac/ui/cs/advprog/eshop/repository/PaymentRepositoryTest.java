package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    private List<Payment> payments;
    private List<Order> orders;
    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order);

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("bankName", "ABC");
        paymentData1.put("referenceCode", "12324");
        Payment payment1 = new Payment("p23135-2132-abc2-2315vb",
                PaymentMethod.BANK_TRANSFER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData1,
                order);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP12345678DEF");
        Payment payment2 = new Payment("p23135-2132-abc2-12313b",
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData2,
                order);
        payments.add(payment2);

        Map<String, String> paymentData3 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP123DEF");
        Payment payment3 = new Payment("p23135-2132-abc2-a3gf2b",
                PaymentMethod.VOUCHER_CODE.getValue(),
                PaymentStatus.REJECTED.getValue(),
                paymentData3,
                order);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = this.payments.get(1);
        Payment result = paymentRepository.save(payment);
        assertEquals(payment.getId(), result.getId());

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = this.payments.get(1);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), PaymentStatus.REJECTED.getValue(), payment.getPaymentData(), orders.get(0));
        Payment result = paymentRepository.save(newPayment);
        assertEquals(payment.getId(), result.getId());

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : this.payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
        assertEquals(payments.get(1).getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : this.payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        for (Payment payment : this.payments) {
            paymentRepository.save(payment);
        }

        assertEquals(this.payments.size(), paymentRepository.getAllPayments().size());
    }
}
