package br.com.tp.lncr.core.dtos.customerorder;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerOrderDTO {
    private Integer id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private String status;
    private Double totalCost;
    private CustomerOrderCustomerDTO customer;
    private List<CustomerOrderFoodItemDTO> foodItems;

    public CustomerOrderDTO() {}

    public CustomerOrderDTO(Integer id, String status, Double totalCost, LocalDateTime created, LocalDateTime updated, CustomerOrderCustomerDTO customer, List<CustomerOrderFoodItemDTO> foodItems) {
        this.id = id;
        this.status = status;
        this.totalCost = totalCost;
        this.created = created;
        this.updated = updated;
        this.customer = customer;
        this.foodItems = foodItems;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public LocalDateTime getCreated() { return created; }
    public void setCreated(LocalDateTime created) { this.created = created; }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public CustomerOrderCustomerDTO getCustomer() {return customer;}
    public void setCustomer(CustomerOrderCustomerDTO customer) {this.customer = customer;}

    public List<CustomerOrderFoodItemDTO> getFoodItems() { return foodItems; }
    public void setFoodItems(List<CustomerOrderFoodItemDTO> foodItems) { this.foodItems = foodItems; }
}

