package br.com.tp.lncr.core.domain.payment;

import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class PaymentMercadopagoQRTest {
    @Test
    void testConstructorAndGetters() {
        LocalDateTime now = LocalDateTime.now();
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO.Builder()
                .id(1)
                .orderId(10)
                .status("Aprovado")
                .amount(50.0)
                .externalPaymentId("ext123")
                .created(now)
                .updated(now)
                .qrData("qrdata")
                .meliId("meli123")
                .build();

        PaymentMercadopagoQR payment = new PaymentMercadopagoQR(dto);
        Assertions.assertEquals(1, payment.getId());
        Assertions.assertEquals(10, payment.getOrderId());
        Assertions.assertEquals("Aprovado", payment.getStatus());
        Assertions.assertEquals(50.0, payment.getAmount());
        Assertions.assertEquals("ext123", payment.getExternalPaymentId());
        Assertions.assertEquals("meli123", payment.getMeliId());
        Assertions.assertEquals("qrdata", payment.getQrData());
        Assertions.assertEquals("mercadopago", payment.getPaymentProvider());
        Assertions.assertEquals("qrcode", payment.getPaymentMethod());
    }

    @Test
    void testSetters() {
        LocalDateTime now = LocalDateTime.now();
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO.Builder()
                .id(2)
                .orderId(20)
                .status("CHARGED")
                .amount(100.0)
                .externalPaymentId("ext456")
                .created(now)
                .updated(now)
                .qrData("qrdata2")
                .meliId("meli456")
                .build();

        PaymentMercadopagoQR payment = new PaymentMercadopagoQR(dto);
        payment.setId(3);
        payment.setOrderId(30);
        payment.setStatus("Cancelled");
        payment.setAmount(200.0);
        payment.setExternalPaymentId("ext789");
        payment.setMeliId("meli789");
        payment.setQrData("qrdata3");
        Assertions.assertEquals(3, payment.getId());
        Assertions.assertEquals(30, payment.getOrderId());
        Assertions.assertEquals("Cancelled", payment.getStatus());
        Assertions.assertEquals(200.0, payment.getAmount());
        Assertions.assertEquals("ext789", payment.getExternalPaymentId());
        Assertions.assertEquals("meli789", payment.getMeliId());
        Assertions.assertEquals("qrdata3", payment.getQrData());
    }
}
