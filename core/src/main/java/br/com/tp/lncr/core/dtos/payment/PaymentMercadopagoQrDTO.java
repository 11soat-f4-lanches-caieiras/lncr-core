package br.com.tp.lncr.core.dtos.payment;

import java.time.LocalDateTime;

public class PaymentMercadopagoQrDTO extends PaymentDTO {
    private String qrData;
    private String meliId;

    public PaymentMercadopagoQrDTO() {
        super();
    }

    public PaymentMercadopagoQrDTO(String qrData, String meliId) {
        this.qrData = qrData;
        this.meliId = meliId;
    }

    public PaymentMercadopagoQrDTO(Integer orderId, Double amount) {
        super(orderId, amount);
    }

    private PaymentMercadopagoQrDTO(Builder builder) {
        this.setId(builder.id);
        this.setOrderId(builder.orderId);
        this.setStatus(builder.status);
        this.setAmount(builder.amount);
        this.setPaymentProvider(builder.paymentProvider);
        this.setPaymentMethod(builder.paymentMethod);
        this.setCreated(builder.created);
        this.setUpdated(builder.updated);
        this.setExternalPaymentId(builder.externalPaymentId);
        this.qrData = builder.qrData;
        this.meliId = builder.meliId;
    }

    public String getQrData() {
        return qrData;
    }

    public void setQrData(String qrData) {
        this.qrData = qrData;
    }

    public String getMeliId() {
        return meliId;
    }

    public void setMeliId(String meliId) {
        this.meliId = meliId;
    }

    public static class Builder {
        private Integer id;
        private Integer orderId;
        private String status;
        private Double amount;
        private String paymentProvider;
        private String paymentMethod;
        private LocalDateTime created;
        private LocalDateTime updated;
        private String externalPaymentId;
        private String qrData;
        private String meliId;

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

        public Builder paymentProvider(String paymentProvider) {
            this.paymentProvider = paymentProvider;
            return this;
        }

        public Builder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
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

        public Builder externalPaymentId(String externalPaymentId) {
            this.externalPaymentId = externalPaymentId;
            return this;
        }

        public Builder qrData(String qrData) {
            this.qrData = qrData;
            return this;
        }

        public Builder meliId(String meliId) {
            this.meliId = meliId;
            return this;
        }

        public PaymentMercadopagoQrDTO build() {
            return new PaymentMercadopagoQrDTO(this);
        }
    }
}
