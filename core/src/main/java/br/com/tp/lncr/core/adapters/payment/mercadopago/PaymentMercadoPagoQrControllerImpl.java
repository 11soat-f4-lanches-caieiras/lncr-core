package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.CreatePaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.GetPaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.applications.payment.usercases.mercadopago.UpdatePaymentMercadoPagoQRUseCase;
import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.interfaces.payment.PaymentController;
import br.com.tp.lncr.core.interfaces.payment.PaymentDatabase;
import br.com.tp.lncr.core.interfaces.payment.PaymentGateway;

import java.util.List;
import java.util.Map;

public class PaymentMercadoPagoQrControllerImpl implements PaymentController<PaymentMercadopagoQrDTO> {

    private final PaymentMercadopagoQRMapper paymentMercadopagoQRMapper;

    public PaymentMercadoPagoQrControllerImpl(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper) {
        this.paymentMercadopagoQRMapper = paymentMercadopagoQRMapper;
    }

    @Override
    public PaymentMercadopagoQrDTO createPaymentCharge(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, PaymentMercadopagoQrDTO paymentDTO) {
        PaymentMercadopagoQR paymentMercadopagoQR = new CreatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).createCharge(paymentDTO);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).createdCharge(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentById(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, Integer paymentId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getById(paymentId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getById(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO getPaymentByCustomerOrderId(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getByCustomerOrderId(customerOrderId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getByCustomerOrderId(paymentMercadopagoQR);
    }

    @Override
    public PaymentMercadopagoQrDTO cancelPaymentByOrderId(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, Integer customerOrderId) {
        PaymentMercadopagoQR paymentMercadopagoQR = new UpdatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).cancelByCustomerOrderId(customerOrderId);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).cancelByCustomerOrderId(paymentMercadopagoQR);
    }

    @Override
    public List<PaymentMercadopagoQrDTO> getPaymentByStatusList(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, List<String> paymentStatusList) {
        List<PaymentMercadopagoQR> paymentMercadopagoQRList = new GetPaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).getByStatusList(paymentStatusList);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).getByStatusList(paymentMercadopagoQRList,paymentStatusList);
    }

    @Override
    public PaymentMercadopagoQrDTO processPaymentReceived(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase, String externalReference, String dataId, Map<String, Object> body) {
        PaymentMercadopagoQR paymentMercadopagoQR = new UpdatePaymentMercadoPagoQRUseCase(createPaymentMercadoPagoQrGateway(paymentDatabase)).processPaymentReceived(externalReference, dataId, body);
        return new PaymentMercadoPagoQrPresenter(paymentMercadopagoQRMapper).cancelByCustomerOrderId(paymentMercadopagoQR);
    }

    private PaymentGateway<PaymentMercadopagoQR> createPaymentMercadoPagoQrGateway(PaymentDatabase<PaymentMercadopagoQrDTO> paymentDatabase){
        return new PaymentGatewayImpl(paymentDatabase, paymentMercadopagoQRMapper);
    }


}
