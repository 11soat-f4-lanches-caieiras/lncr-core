package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentMercadopagoQRMapperTest {
    private final PaymentMercadopagoQRMapper mapper = new PaymentMercadopagoQRMapper();

    @Test
    void testPaymentMercadopagoQrToDTO_Null() {
        assertNull(mapper.paymentMercadopagoQrToDTO(null));
    }

    @Test
    void testPaymentMercadopagoQrToDomain_Null() {
        assertNull(mapper.paymentMercadopagoQrToDomain(null));
    }

    @Test
    void testPaymentMercadopagoQrToDTO_AndBack() {
        LocalDateTime now = LocalDateTime.now();
        PaymentMercadopagoQrDTO inputDto = new PaymentMercadopagoQrDTO.Builder()
                .id(1)
                .orderId(123)
                .status("Paid")
                .amount(100.0)
                .externalPaymentId("EXT123")
                .created(now)
                .updated(now)
                .qrData("qrdata")
                .meliId("MELI123")
                .build();

        PaymentMercadopagoQR domain = new PaymentMercadopagoQR(inputDto);
        PaymentMercadopagoQrDTO dto = mapper.paymentMercadopagoQrToDTO(domain);
        assertNotNull(dto);
        PaymentMercadopagoQR domain2 = mapper.paymentMercadopagoQrToDomain(dto);
        assertNotNull(domain2);
    }
}
