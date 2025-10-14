package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;
import br.com.tp.lncr.core.utils.StatusOrderUtils;

import java.util.List;

public class PaymentMercadoPagoQrPresenter {

    private final PaymentMercadopagoQRMapper paymentMercadopagoQRMapper;

    public PaymentMercadoPagoQrPresenter(PaymentMercadopagoQRMapper paymentMercadopagoQRMapper) {
        this.paymentMercadopagoQRMapper = paymentMercadopagoQRMapper;
    }

    public PaymentMercadopagoQrDTO createdCharge(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO getById(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO getByCustomerOrderId(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public PaymentMercadopagoQrDTO cancelByCustomerOrderId(PaymentMercadopagoQR paymentMercadopagoQR) {
        return this.paymentMercadopagoQRMapper.paymentMercadopagoQrToDTO(paymentMercadopagoQR);
    }

    public List<PaymentMercadopagoQrDTO> getByStatusList(List<PaymentMercadopagoQR> paymentMercadopagoQRList, List<String> paymentStatusList) {
        return StatusOrderUtils.sortByStatusOrder(paymentMercadopagoQRList,paymentStatusList)
                .stream()
                .map(paymentMercadopagoQRMapper::paymentMercadopagoQrToDTO)
                .toList();
    }
}
