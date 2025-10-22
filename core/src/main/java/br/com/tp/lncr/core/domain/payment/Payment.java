package br.com.tp.lncr.core.domain.payment;

import br.com.tp.lncr.core.enums.PaymentStatus;
import br.com.tp.lncr.core.interfaces.SortedByStatusCreated;

import java.time.LocalDateTime;

public abstract class Payment implements SortedByStatusCreated {
    private Integer id;
    private Integer orderId;
    private String status;
    private Double amount;
    private String externalPaymentId;
    private LocalDateTime created;
    private LocalDateTime updated;

    protected Payment(Integer id, Integer orderId, String status, Double amount, String externalPaymentId, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.externalPaymentId = externalPaymentId;
        this.created = created;
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = PaymentStatus.fromDescription(status).getDescription();
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    public LocalDateTime getUpdated() {
        return updated;
    }
    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
    public String getExternalPaymentId() {
        return externalPaymentId;
    }
    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }
    public abstract String getPaymentProvider();
    public abstract String getPaymentMethod();

    }
