package br.com.tp.lncr.core.adapters.payment.mercadopago;

import br.com.tp.lncr.core.domain.payment.PaymentMercadopagoQR;
import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;

public class PaymentMercadopagoQRMapper {

    private PaymentMercadopagoQRMapper() {
    }

    public PaymentMercadopagoQrDTO paymentMercadopagoQrToDTO(PaymentMercadopagoQR entity) {
        if (entity == null) return null;
        return new PaymentMercadopagoQrDTO.PaymentMercadopagoQrDtoBuilder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .paymentProvider(entity.getPaymentProvider())
                .paymentMethod(entity.getPaymentMethod())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .externalPaymentId(entity.getExternalPaymentId())
                .qrData(entity.getQrData())
                .meliId(entity.getMeliId() != null ? entity.getMeliId() : null)
                .build();
    }

    public PaymentMercadopagoQR paymentMercadopagoQrToDomain(PaymentMercadopagoQrDTO dto) {
        if (dto == null) return null;
        return new PaymentMercadopagoQR(dto);
    }
}
