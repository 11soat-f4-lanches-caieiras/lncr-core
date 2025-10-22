package br.com.tp.lncr.core.applications.fooditem;

import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import br.com.tp.lncr.core.dtos.fooditem.FoodItemImageDTO;
import br.com.tp.lncr.core.exceptions.FoodItemException;
import br.com.tp.lncr.core.interfaces.fooditem.FoodItemGateway;
import br.com.tp.lncr.core.utils.FoodItemImageRules;
import br.com.tp.lncr.core.utils.LoggerUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CreateFoodItemImageUseCase {

    private final FoodItemGateway foodItemGateway;
    private final FoodItemImageRules foodItemImageRules;

    public CreateFoodItemImageUseCase(FoodItemGateway foodItemGateway, FoodItemImageRules foodItemImageRules) {
        this.foodItemGateway = foodItemGateway;
        this.foodItemImageRules = foodItemImageRules;
    }

    public FoodItemImage execute(Integer foodItemId, FoodItemImageDTO foodItemImageDTO) {
        LoggerUtil.info("Iniciando criação de imagem para o item de alimentação com id: " + foodItemId);
        Integer maxImages = foodItemImageRules.getMaxNumberOfImages();
        List<FoodItemImage> foodItemImageList = foodItemGateway.getAllImagesByFoodItemId(foodItemId, false);

        if (foodItemImageList.size() >= maxImages) {
            throw new FoodItemException("O item de alimentação com id " + foodItemId + " já possui "+maxImages+" imagens. Subistitua uma imagem já existente", 409);
        }

        FoodItemImage foodItemImage = new FoodItemImage(foodItemImageDTO, foodItemImageRules);

        if (foodItemImage.getImageError() != null) {
            throw new FoodItemException(foodItemImage.getImageError(), 404);
        }

        setNewImageInfo(foodItemId, foodItemImage, foodItemImageList, maxImages);
        foodItemGateway.createFoodItem(foodItemImage);
        LoggerUtil.info("Imagem criada com sucesso para o item de alimentação com id: " + foodItemId);
        return foodItemImage;
    }


    private void setNewImageInfo(Integer foodItemId, FoodItemImage foodItemImage, List<FoodItemImage> foodItemImageList, Integer maxImages) {
        LoggerUtil.debug("Definindo informações da imagem para o item de alimentação com id: " + foodItemId);
        List<Integer> possiblesIds = IntStream.rangeClosed(1, maxImages).map(i -> foodItemId * 10 + i).boxed().collect(Collectors.toList()); //Criando lista de possíveis ids para o Item de Alimentação
        List<Integer> existsIds = foodItemImageList.stream().map(FoodItemImage::getId).toList();         //Lista de ids existentes
        possiblesIds.removeAll(existsIds);//Removendo id existentes da lista de ids possíveis

        //Alterando informações da imagem
        foodItemImage.setFoodItemId(foodItemId);
        foodItemImage.setId(possiblesIds.stream().min(Integer::compareTo).orElse(null));
        foodItemImage.setFileName(foodItemImage.getId() + "." + foodItemImage.getFileExtension());
    }
}
