package br.com.tp.lncr.core.dtos.payment;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentMercadopagoQrDTOTest {
    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO.Builder()
                .id(1)
                .orderId(2)
                .status("PAID")
                .amount(100.0)
                .paymentProvider("MP")
                .paymentMethod("QR")
                .created(now)
                .updated(now)
                .externalPaymentId("extId")
                .qrData("qrdata")
                .meliId("meliid")
                .build();

        assertEquals(1, dto.getId());
        assertEquals(2, dto.getOrderId());
        assertEquals("PAID", dto.getStatus());
        assertEquals(100.0, dto.getAmount());
        assertEquals("MP", dto.getPaymentProvider());
        assertEquals("QR", dto.getPaymentMethod());
        assertEquals(now, dto.getCreated());
        assertEquals(now, dto.getUpdated());
        assertEquals("extId", dto.getExternalPaymentId());
        assertEquals("qrdata", dto.getQrData());
        assertEquals("meliid", dto.getMeliId());
    }

    @Test
    void testDefaultConstructor() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO();
        assertNull(dto.getId());
        assertNull(dto.getOrderId());
        assertNull(dto.getStatus());
        assertNull(dto.getAmount());
        assertNull(dto.getPaymentProvider());
        assertNull(dto.getPaymentMethod());
        assertNull(dto.getCreated());
        assertNull(dto.getUpdated());
        assertNull(dto.getExternalPaymentId());
        assertNull(dto.getQrData());
        assertNull(dto.getMeliId());
    }

    @Test
    void testPartialConstructor() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO("qrdata", "meliid");
        assertNull(dto.getId());
        assertNull(dto.getOrderId());
        assertNull(dto.getStatus());
        assertNull(dto.getAmount());
        assertNull(dto.getPaymentProvider());
        assertNull(dto.getPaymentMethod());
        assertNull(dto.getCreated());
        assertNull(dto.getUpdated());
        assertNull(dto.getExternalPaymentId());
        assertEquals("qrdata", dto.getQrData());
        assertEquals("meliid", dto.getMeliId());
    }

    @Test
    void testNullValuesConstructor() {
        PaymentMercadopagoQrDTO dto = new PaymentMercadopagoQrDTO.Builder()
                .id(null)
                .orderId(null)
                .status(null)
                .amount(null)
                .paymentProvider(null)
                .paymentMethod(null)
                .created(null)
                .updated(null)
                .externalPaymentId(null)
                .qrData(null)
                .meliId(null)
                .build();

        assertNull(dto.getId());
        assertNull(dto.getOrderId());
        assertNull(dto.getStatus());
        assertNull(dto.getAmount());
        assertNull(dto.getPaymentProvider());
        assertNull(dto.getPaymentMethod());
        assertNull(dto.getCreated());
        assertNull(dto.getUpdated());
        assertNull(dto.getExternalPaymentId());
        assertNull(dto.getQrData());
        assertNull(dto.getMeliId());
    }
}
