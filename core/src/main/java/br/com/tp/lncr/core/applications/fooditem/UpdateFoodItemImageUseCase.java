package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import br.com.tp.lncr.core.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.exceptions.FoodItemException;
import br.com.tp.lncr.core.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.utils.FoodItemImageRules;
import br.com.tp.lncr.core.utils.LoggerUtil;

public class UpdateFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;

    public UpdateFoodItemImageUseCase(FoodItemGateway foodItemGateway) {
        this.foodItemGateway = foodItemGateway;
    }

    public FoodItemImage updateImageById(Integer foodItemImageId, FoodItemImageDTO foodItemImageDTO, FoodItemImageRules foodItemImageRules) {
        LoggerUtil.info("Iniciando atualização de imagem com id: " + foodItemImageId);
        FoodItemImage existFoodItemImage = foodItemGateway.getFoodItemImageById(foodItemImageId);

        if (existFoodItemImage == null){
            throw new FoodItemException("Não encontrada imagem com o id: " + foodItemImageId,404);
        }
        foodItemImageDTO.setId(existFoodItemImage.getId());
        foodItemImageDTO.setFoodItemId(existFoodItemImage.getFoodItemId());
        FoodItemImage newFoodItemImage = foodItemGateway.createFoodItem(new FoodItemImage(foodItemImageDTO,foodItemImageRules));

        if (newFoodItemImage == null) {
            throw new FoodItemException("Erro ao atualizar imagem: retorno nulo do gateway.", 500);
        }

        if (!existFoodItemImage.getFileName().equals(newFoodItemImage.getFileName())){
            LoggerUtil.debug("Arquivo de imagem alterado, removendo arquivo antigo: " + existFoodItemImage.getFileName());
            foodItemGateway.deleteImageFile(existFoodItemImage.getFileName());
        }
        LoggerUtil.info("Imagem com id: " + foodItemImageId + " atualizada com sucesso.");
        return newFoodItemImage;
    }
}
