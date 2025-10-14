package br.com.tp.lncr.core.adapters.kitchenorder;

import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrder;
import br.com.tp.lncr.core.domain.kitchenorder.KitchenOrderFoodItem;
import br.com.tp.lncr.core.dtos.kitchenorder.KitchenOrderDTO;
import br.com.tp.lncr.core.dtos.kitchenorder.KitchenOrderFoodItemDTO;

public class KitchenOrderMapper {
    public KitchenOrderDTO kitchenOrderToDTO(KitchenOrder order) {
        if (order == null) return null;
        KitchenOrderDTO dto = new KitchenOrderDTO();
        dto.setId(order.getId());
        dto.setCustomerOrderId(order.getCustomerOrderId());
        dto.setStatus(order.getStatus());
        dto.setCreated(order.getCreated());
        dto.setUpdated(order.getUpdated());
        if (order.getFoodItems() != null) {
            dto.setFoodItems(order.getFoodItems().stream()
                .map(this::kitchenOrderFoodItemToDTO).toList());
        }
        return dto;
    }

    public KitchenOrder kichenOrderToDomain(KitchenOrderDTO dto) {
        if (dto == null) return null;
        KitchenOrder order = new KitchenOrder();
        order.setId(dto.getId());
        order.setCustomerOrderId(dto.getCustomerOrderId());
        order.setStatus(dto.getStatus());
        order.setCreated(dto.getCreated());
        order.setUpdated(dto.getUpdated());
        if (dto.getFoodItems() != null) {
            order.setFoodItems(dto.getFoodItems().stream()
                .map(this::kichenOrderFoodItemToDomain)
                    .toList());
        }

        return order;
    }

    public KitchenOrderFoodItemDTO kitchenOrderFoodItemToDTO(KitchenOrderFoodItem item) {
        if (item == null) return null;
        KitchenOrderFoodItemDTO dto = new KitchenOrderFoodItemDTO();
        dto.setId(item.getId());
        dto.setKitchenOrderId(item.getKitchenOrderId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setNotes(item.getNotes());
        return dto;
    }

    public KitchenOrderFoodItem kichenOrderFoodItemToDomain(KitchenOrderFoodItemDTO dto) {
        if (dto == null) return null;
        KitchenOrderFoodItem item = new KitchenOrderFoodItem();
        item.setId(dto.getId());
        item.setKitchenOrderId(dto.getKitchenOrderId());
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setNotes(dto.getNotes());
        return item;
    }
}

