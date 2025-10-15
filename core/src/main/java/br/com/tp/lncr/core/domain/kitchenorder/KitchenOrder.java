package br.com.tp.lncr.core.domain.kitchenorder;


import br.com.tp.lncr.core.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.enums.KitchenOrderStatus;
import br.com.tp.lncr.core.exceptions.KitchenOrderException;
import br.com.tp.lncr.core.interfaces.SortedByStatusCreated;
import br.com.tp.lncr.core.utils.EnumUtils;

import java.time.LocalDateTime;
import java.util.List;

public class KitchenOrder implements SortedByStatusCreated {
    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Integer customerOrderId;
    private String status;
    private List<KitchenOrderFoodItem> foodItems;


    public KitchenOrder(Integer id, Integer customerOrderId, String status, List<KitchenOrderFoodItem> foodItems, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.customerOrderId = customerOrderId;
        this.status = status;
        this.foodItems = foodItems;
        this.created = created;
        this.updated = updated;
    }

    public KitchenOrder() {
    }

    public KitchenOrder(KitchenOrderDTO dto) {
        this.id = dto.getId();
        this.customerOrderId = dto.getCustomerOrderId();
        this.status = dto.getStatus();
        if (dto.getFoodItems() != null) {
            this.foodItems = dto.getFoodItems().stream()
                .map(KitchenOrderFoodItem::new)
                .toList();
        }
        if (dto.getCreated() != null) {
            this.created = dto.getCreated();
        }
        if (dto.getUpdated() != null) {
            this.updated = dto.getUpdated();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(Integer customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status, Boolean forceUpdate) {
        this.status = validateNewStatusRules(status,forceUpdate);
    }

    public void setStatus(String status) {
        this.status = validateNewStatusRules(status,true);
    }

    public List<KitchenOrderFoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<KitchenOrderFoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    private String validateNewStatusRules(String newStatus, Boolean forceUpdate) {
        return EnumUtils.validateNewStatusRules(KitchenOrderStatus.class, this.getStatus(), newStatus, forceUpdate,
                message -> new KitchenOrderException(message, 400));
    }
}

