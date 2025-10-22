package br.com.tp.lncr.core.applications.payment.usercases.mercadopago;

import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.enums.PaymentStatus;
import br.com.tp.lncr.core.exceptions.PaymentException;
import br.com.tp.lncr.core.interfaces.payment.PaymentGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class GetPaymentMercadoPagoQRUseCase {

    private final PaymentGateway<PaymentMercadopagoQR> paymentGateway;

    public GetPaymentMercadoPagoQRUseCase(PaymentGateway<PaymentMercadopagoQR> paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public PaymentMercadopagoQR getById(Integer paymentId) {
        LoggerUtil.info("Iniciando busca de pagamento Mercado Pago QR pelo id: " + paymentId);
        PaymentMercadopagoQR paymentMercadopagoQR = this.paymentGateway.getPaymentById(paymentId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ paymentId, 404);
        }
        LoggerUtil.info("Pagamento Mercado Pago QR encontrado com sucesso, id: " + paymentId);
        return paymentMercadopagoQR;
    }

    public PaymentMercadopagoQR getByCustomerOrderId(Integer customerOrderId) {
        LoggerUtil.info("Iniciando busca de pagamento Mercado Pago QR pelo id do pedido do cliente: " + customerOrderId);
        PaymentMercadopagoQR paymentMercadopagoQR = this.paymentGateway.getPaymentByCustomerOrderId(customerOrderId);
        if (paymentMercadopagoQR == null){
            throw new PaymentException("Não encontrado pagamento pelo id: "+ customerOrderId, 404);
        }
        LoggerUtil.info("Pagamento do Mercado Pago QR pelo pedido do cliente id: "+ customerOrderId+ " encontrado");
        return paymentMercadopagoQR;
    }

    public List<PaymentMercadopagoQR> getByStatusList(List<String> paymentStatusList) {
        LoggerUtil.info("Buscando Pagamentos por Status: "+paymentStatusList);
        List<Integer> statusIdsList = getStatusListIds(paymentStatusList);
        List<PaymentMercadopagoQR> paymentMercadopagoQRList = this.paymentGateway.getPaymentMercadoPagoQRList(statusIdsList);
        if (paymentMercadopagoQRList == null || paymentStatusList.isEmpty()){
            throw new PaymentException("Não encontradas ordens com os status:" + paymentStatusList,404);
        }
        LoggerUtil.info("Encontrado pagamentos pelos status: "+paymentStatusList);
        return paymentMercadopagoQRList;
    }


    private List<Integer> getStatusListIds(List<String> statusList){
        LoggerUtil.debug("Convertendo Status por ids: "+ statusList);
        List<Integer> statusListIds = new ArrayList<>();
        for(String status : statusList){
            statusListIds.add(PaymentStatus.fromDescription(status).getId());
        }
        LoggerUtil.debug("Statu convertidos para os ids: "+statusListIds);
        return statusListIds;
    }
}
