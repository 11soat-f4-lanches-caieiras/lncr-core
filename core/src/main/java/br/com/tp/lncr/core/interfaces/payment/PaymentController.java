package br.com.tp.lncr.core.interfaces.payment;

import java.util.List;
import java.util.Map;

public interface PaymentController<T> {
    T createPaymentCharge(PaymentDatabase<T> paymentDatabase, T paymentDTO);

    T getPaymentById(PaymentDatabase<T> paymentDatabase, Integer paymentId);

    T getPaymentByCustomerOrderId(PaymentDatabase<T> paymentDatabase, Integer customerOrderId);

    T cancelPaymentByOrderId(PaymentDatabase<T> paymentDatabase, Integer customerOrderId);

    List<T> getPaymentByStatusList(PaymentDatabase<T> paymentDatabase, List<String> paymentStatusList);

    T processPaymentReceived(PaymentDatabase<T> paymentDatabase, String externalReference, String dataId, Map<String, Object> body);
}
