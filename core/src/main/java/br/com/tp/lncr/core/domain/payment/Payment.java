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
    private LocalDateTime _created;
    private LocalDateTime _updated;

    protected Payment(Integer id, Integer orderId, String status, Double amount, String externalPaymentId, LocalDateTime _created, LocalDateTime _updated) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.amount = amount;
        this.externalPaymentId = externalPaymentId;
        this._created = _created;
        this._updated = _updated;
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
        return _created;
    }
    public void setCreated(LocalDateTime _created) {
        this._created = _created;
    }
    public LocalDateTime getUpdated() {
        return _updated;
    }
    public void setUpdated(LocalDateTime _updated) {
        this._updated = _updated;
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
