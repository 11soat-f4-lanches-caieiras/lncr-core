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

    private PaymentMercadopagoQrDTO(PaymentMercadopagoQrDtoBuilder paymentMercadopagoQrDtoBuilder) {
        this.setId(paymentMercadopagoQrDtoBuilder.id);
        this.setOrderId(paymentMercadopagoQrDtoBuilder.orderId);
        this.setStatus(paymentMercadopagoQrDtoBuilder.status);
        this.setAmount(paymentMercadopagoQrDtoBuilder.amount);
        this.setPaymentProvider(paymentMercadopagoQrDtoBuilder.paymentProvider);
        this.setPaymentMethod(paymentMercadopagoQrDtoBuilder.paymentMethod);
        this.setCreated(paymentMercadopagoQrDtoBuilder.created);
        this.setUpdated(paymentMercadopagoQrDtoBuilder.updated);
        this.setExternalPaymentId(paymentMercadopagoQrDtoBuilder.externalPaymentId);
        this.qrData = paymentMercadopagoQrDtoBuilder.qrData;
        this.meliId = paymentMercadopagoQrDtoBuilder.meliId;
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

    public static class PaymentMercadopagoQrDtoBuilder {
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

        public PaymentMercadopagoQrDtoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder orderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder status(String status) {
            this.status = status;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder paymentProvider(String paymentProvider) {
            this.paymentProvider = paymentProvider;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder paymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder updated(LocalDateTime updated) {
            this.updated = updated;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder externalPaymentId(String externalPaymentId) {
            this.externalPaymentId = externalPaymentId;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder qrData(String qrData) {
            this.qrData = qrData;
            return this;
        }

        public PaymentMercadopagoQrDtoBuilder meliId(String meliId) {
            this.meliId = meliId;
            return this;
        }

        public PaymentMercadopagoQrDTO build() {
            return new PaymentMercadopagoQrDTO(this);
        }
    }
}
