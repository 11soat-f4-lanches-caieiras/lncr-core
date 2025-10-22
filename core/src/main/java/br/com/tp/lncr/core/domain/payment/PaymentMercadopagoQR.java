package br.com.tp.lncr.core.domain.payment;

import br.com.tp.lncr.core.dtos.payment.PaymentMercadopagoQrDTO;

import java.time.LocalDateTime;

public class PaymentMercadopagoQR extends Payment{
    private static final String PROVIDER = "mercadopago";
    private static final String METHOD = "qrcode";
    private String meliId;
    private String qrData;

    private PaymentMercadopagoQR(Builder builder) {
        super(builder.id, builder.orderId, builder.status, builder.amount, builder.externalPaymentId, builder.created, builder.updated);
        this.meliId = builder.meliId;
        this.qrData = builder.qrData;
    }

    public PaymentMercadopagoQR(PaymentMercadopagoQrDTO dto) {
        super(
            dto.getId(),
            dto.getOrderId(),
            dto.getStatus(),
            dto.getAmount(),
            dto.getExternalPaymentId(),
            dto.getCreated(),
            dto.getUpdated()
        );
        this.qrData = dto.getQrData();
        this.meliId = dto.getMeliId();
    }

    public String getMeliId() {
        return meliId;
    }

    public void setMeliId(String meliId) {
        this.meliId = meliId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    @Override
    public String getPaymentProvider() {
        return PROVIDER;
    }

    @Override
    public String getPaymentMethod() {
        return METHOD;
    }

    public static class Builder {
        private Integer id;
        private Integer orderId;
        private String status;
        private Double amount;
        private String externalPaymentId;
        private LocalDateTime created;
        private LocalDateTime updated;
        private String meliId;
        private String qrData;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder orderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder externalPaymentId(String externalPaymentId) {
            this.externalPaymentId = externalPaymentId;
            return this;
        }

        public Builder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Builder updated(LocalDateTime updated) {
            this.updated = updated;
            return this;
        }

        public Builder meliId(String meliId) {
            this.meliId = meliId;
            return this;
        }

        public Builder qrData(String qrData) {
            this.qrData = qrData;
            return this;
        }

        public PaymentMercadopagoQR build() {
            return new PaymentMercadopagoQR(this);
        }
    }
}
