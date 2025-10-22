package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.domain.fooditem.FoodItem;
import br.com.tp.lncr.core.exceptions.FoodItemException;
import br.com.tp.lncr.core.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class DeleteFoodItemUseCase {

    private final FoodItemGateway foodItemGateway;

    public DeleteFoodItemUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public void execute(Integer foodItemId) {
        LoggerUtil.info("Iniciando exclusão do item de alimentação com id: " + foodItemId);
        FoodItem foodItem = foodItemGateway.getFoodItemById(foodItemId, true);
        if (foodItem == null) {
            throw new FoodItemException("Não encontrado item de alimentação com id: " + foodItemId, 404);
        }
        foodItemGateway.deleteFoodItemImage(foodItem);
        LoggerUtil.info("Item de alimentação com id: " + foodItemId + " excluído com sucesso.");
    }
}
