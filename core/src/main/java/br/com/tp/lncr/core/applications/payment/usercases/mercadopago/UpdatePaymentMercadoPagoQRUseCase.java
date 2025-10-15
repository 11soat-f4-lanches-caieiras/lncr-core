package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.enums.PaymentStatus;
import br.com.tp.lncr.core.exceptions.PaymentException;
import br.com.tp.lncr.core.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

import java.util.Map;

public class UpdatePaymentMercadoPagoQRUseCase {

    private final PaymentGateway<PaymentMercadopagoQR> paymentGateway;

    public UpdatePaymentMercadoPagoQRUseCase(PaymentGateway<PaymentMercadopagoQR> paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR cancelByCustomerOrderId(Integer customerOrderId) {
        LoggerUtil.info("Iniciando cancelamento do pagamento para o pedido id: "+customerOrderId);
        PaymentMercadopagoQR paymentMercadopagoQR =this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null) {
            throw new PaymentException("Não encontrado pagamento pelo id: " + customerOrderId, 404);
        }
        String previousStatus = paymentMercadopagoQR.getStatus();
        paymentMercadopagoQR.setStatus(PaymentStatus.CANCELLED.getDescription());
        paymentMercadopagoQR = this.paymentGateway.savePayment(paymentMercadopagoQR);

        switch (previousStatus.toUpperCase()) {
            case "CHARGED":
                cancelPaymentCharged(paymentMercadopagoQR);
                break;
            case "PAID":
                cancelPaymentPaid(paymentMercadopagoQR);
                break;
            default:
                break;
        }
        LoggerUtil.info("Pagamento do pedido do cliente id: " + customerOrderId + "cancelado");
        return paymentMercadopagoQR;
    }

    public PaymentMercadopagoQR processPaymentReceived(String externalReference, String dataId, Map<String, Object> body) {
        Integer externalReferenceId = Integer.valueOf(externalReference);
        if (Boolean.TRUE.equals(validadeOrderPayment(body))) {
            PaymentMercadopagoQR paymentMercadopagoQR = this.paymentGateway.getPaymentByCustomerOrderId(externalReferenceId);
            if (paymentMercadopagoQR != null && paymentMercadopagoQR.getMeliId().equals(dataId)) {
                if (paymentMercadopagoQR.getStatus().equalsIgnoreCase(PaymentStatus.CHARGED.getDescription())) {
                    paymentMercadopagoQR.setStatus(PaymentStatus.PAID.getDescription());
                    paymentMercadopagoQR = this.paymentGateway.savePayment(paymentMercadopagoQR);
                    this.paymentGateway.updateCustomerOrderStatus(externalReferenceId, "Received");
                    this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_PAID", paymentMercadopagoQR.getOrderId(), "Pagamento com id: " + paymentMercadopagoQR.getOrderId() + " finalizado.");
                    return paymentMercadopagoQR;
                }
                throw new PaymentException("Status do pagamento inválido: " + paymentMercadopagoQR.getStatus(), 400);
            }
            throw new PaymentException("Não encontrado pedido com o id: " + externalReferenceId, 404);
        }
        throw new PaymentException("Status do pagamento inválido" + body.toString(), 400);
    }

    private void cancelPaymentCharged(PaymentMercadopagoQR paymentMercadopagoQR) {
        LoggerUtil.debug("Realizando cancelamento de ordem de pagamento no Mercado Pago");
        this.paymentGateway.cancelPaymentOrderByProviderId(paymentMercadopagoQR.getMeliId());
        this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_CANCELLED", paymentMercadopagoQR.getOrderId(), "Cobrança criada com id: " + paymentMercadopagoQR.getOrderId() + " cancelada");
    }

    private void cancelPaymentPaid(PaymentMercadopagoQR paymentMercadopagoQR) {
        LoggerUtil.debug("Solicitando estorno do pagamento no Mercado Pago");
        this.paymentGateway.refundPaymentOrderByProviderId(paymentMercadopagoQR.getMeliId());
        this.paymentGateway.sendNotification("PAYMENT_MERCADOPAGO_QR_REFUND", paymentMercadopagoQR.getOrderId(), "Solicitado estorno para cobrança id: " + paymentMercadopagoQR.getOrderId());
    }

    private Boolean validadeOrderPayment(Map<String, Object> body) {
        LoggerUtil.debug("Validando status do pagamento recebido");
        if (body == null) return false;
        Object dataObj = body.get("data");
        if (dataObj instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) dataObj;
            String status = (String) data.get("status");
            String statusDetail = (String) data.get("status_detail");
            return "processed".equals(status) && "accredited".equals(statusDetail);
        }
        return false;
    }


}
