package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.CreatePaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.GetPaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.UpdatePaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.interfaces.payment.PaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PaymentMercadoPagoQrControllerImplTest {
    private PaymentGateway paymentGateway;
    private PaymentMercadopagoQrDTO dto;

    @BeforeEach
    void setUp() {
        paymentGateway = mock(PaymentGateway.class);
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(1)
                .orderId(2)
                .status("PAID")
                .amount(25.99)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("123")
                .qrData("qr_data")
                .meliId("meli_123")
                .build();
    }

    @Test
    void testCreatePaymentCharge() {
        PaymentMercadopagoQrDTO input = new PaymentMercadopagoQrDTO(1, 25.99);
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(1)
                .orderId(1)
                .status("CHARGED")
                .amount(30.00)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("meli_456")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);

        when(paymentGateway.getPaymentByCustomerOrderId(1)).thenReturn(null);
        when(paymentGateway.createPaymentOrder(any(PaymentMercadopagoQR.class))).thenReturn(paymentMercadopagoQR);
        CreatePaymentMercadoPagoQRUseCase useCase = new CreatePaymentMercadoPagoQRUseCase(paymentGateway);
        PaymentMercadopagoQR result = useCase.createCharge(input);
        assertNotNull(result);
    }

    @Test
    void testGetPaymentById() {

        //Testando diretamente o UseCase com o gateway mockado
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(2)
                .orderId(2)
                .status("PAID")
                .amount(30.00)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("meli_456")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);

        when(paymentGateway.getPaymentById(2)).thenReturn(paymentMercadopagoQR);
        GetPaymentMercadoPagoQRUseCase useCase = new GetPaymentMercadoPagoQRUseCase(paymentGateway);
        PaymentMercadopagoQR result = useCase.getById(2);
        assertNotNull(result);
    }

    @Test
    void testGetPaymentByCustomerOrderId() {
        // Testando diretamente o UseCase com o gateway mockado
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(2)
                .orderId(2)
                .status("PAID")
                .amount(30.00)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("meli_456")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);

        when(paymentGateway.getPaymentByCustomerOrderId(2)).thenReturn(paymentMercadopagoQR);

        GetPaymentMercadoPagoQRUseCase useCase = new GetPaymentMercadoPagoQRUseCase(paymentGateway);
        PaymentMercadopagoQR result = useCase.getByCustomerOrderId(2);
        assertNotNull(result);
        verify(paymentGateway).getPaymentByCustomerOrderId(2);
    }

    @Test
    void testCancelPaymentByOrderId() {
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(2)
                .orderId(2)
                .status("CHARGED")
                .amount(30.00)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("meli_456")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);
        when(paymentGateway.getPaymentByCustomerOrderId(2)).thenReturn(paymentMercadopagoQR);
        when(paymentGateway.savePayment(paymentMercadopagoQR)).thenReturn(paymentMercadopagoQR);
        UpdatePaymentMercadoPagoQRUseCase useCase = new UpdatePaymentMercadoPagoQRUseCase(paymentGateway);
        PaymentMercadopagoQR result = useCase.cancelByCustomerOrderId(2);
        assertNotNull(result);
    }

    @Test
    void testGetPaymentByStatusList() {
        List<String> statusList = List.of("CHARGED", "PAID", "CANCELLED");
        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(2)
                .orderId(2)
                .status("PAID")
                .amount(30.00)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("meli_456")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);
        when(paymentGateway.getPaymentMercadoPagoQRList(any())).thenReturn(List.of(paymentMercadopagoQR));
        GetPaymentMercadoPagoQRUseCase useCase = new GetPaymentMercadoPagoQRUseCase(paymentGateway);
        List<PaymentMercadopagoQR> result = useCase.getByStatusList(statusList);
        assertNotNull(result);
    }

    @Test
    void testProcessPaymentReceived() {
        Map<String, Object> body = Map.of(
            "action", "order.processed",
            "api_version", "v1",
            "application_id", "4844709428317965",
            "data", Map.of(
                "external_reference", "6",
                "id", "ORDTST01K0FPKQXG1TYQZABC4Q34EHWF",
                "status", "processed",
                "status_detail", "accredited",
                "total_amount", "91.44",
                "total_paid_amount", "91.44",
                "transactions", Map.of(
                    "payments", List.of(
                        Map.of(
                            "amount", "91.44",
                            "id", "PAY01K0A8YZ1ACXYH4A81YX2GHMP6",
                            "paid_amount", "91.44",
                            "payment_method", Map.of(
                                "id", "account_money",
                                "installments", 1,
                                "type", "account_money"
                            ),
                            "reference", Map.of(
                                "id", "118827066920"
                            ),
                            "status", "processed",
                            "status_detail", "accredited"
                        )
                    )
                ),
                "type", "qr",
                "version", 2
            ),
            "date_created", "2025-07-16T18:47:54.861388166Z",
            "live_mode", false,
            "type", "order",
            "user_id", "2425846779"
        );

        dto = new PaymentMercadopagoQrDTO.Builder()
                .id(6)
                .orderId(6)
                .status("CHARGED")
                .amount(91.44)
                .paymentProvider("MERCADO_PAGO")
                .paymentMethod("QR")
                .created(null)
                .updated(null)
                .externalPaymentId("456")
                .qrData("qr_data_456")
                .meliId("PAY01K0A8YZ1ACXYH4A81YX2GHMP6")
                .build();
        PaymentMercadopagoQR paymentMercadopagoQR = new PaymentMercadopagoQR(dto);

        when(paymentGateway.getPaymentByCustomerOrderId(6)).thenReturn(paymentMercadopagoQR);
        when(paymentGateway.savePayment(paymentMercadopagoQR)).thenReturn(paymentMercadopagoQR);
        UpdatePaymentMercadoPagoQRUseCase useCase = new UpdatePaymentMercadoPagoQRUseCase(paymentGateway);
        PaymentMercadopagoQR result = useCase.processPaymentReceived("6", "PAY01K0A8YZ1ACXYH4A81YX2GHMP6", body);
        assertNotNull(result);
    }
}
