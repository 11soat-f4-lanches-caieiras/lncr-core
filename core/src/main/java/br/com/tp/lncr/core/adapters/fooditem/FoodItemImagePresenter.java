package br.com.tp.lncr.core.adapters.fooditem;

import br.com.tp.lncr.core.domain.fooditem.FoodItemImage;
import br.com.tp.lncr.core.dtos.fooditem.FoodItemImageDTO;

import java.util.List;

public class FoodItemImagePresenter {

    private final FoodItemMapper foodItemMapper;

    public FoodItemImagePresenter(FoodItemMapper foodItemMapper) {
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemImageDTO created(FoodItemImage foodItemImage, String imageLocationPrefix) {
        return formatFoodItemImageDTO(foodItemMapper.foodItemImageToDTO(foodItemImage), imageLocationPrefix, false);
    }

    public FoodItemImageDTO getById(FoodItemImage foodItemImage, String imageLocationPrefix) {
        return formatFoodItemImageDTO(foodItemMapper.foodItemImageToDTO(foodItemImage), imageLocationPrefix, true);
    }

    public FoodItemImageDTO updateById(FoodItemImage updateFoodItemImage, String imageLocationPrefix) {
        return formatFoodItemImageDTO(foodItemMapper.foodItemImageToDTO(updateFoodItemImage), imageLocationPrefix, false);
    }

    public List<FoodItemImageDTO> getAllImagesByFoodItemId(List<FoodItemImage> foodItemImageList, String imageLocationPrefix, Boolean includeData) {
        return foodItemImageDTOList(foodItemImageList.stream().map(foodItemMapper::foodItemImageToDTO).toList(), imageLocationPrefix, includeData);
    }

    private FoodItemImageDTO formatFoodItemImageDTO(FoodItemImageDTO foodItemImageDTO, String imageLocationPrefix, Boolean includeData) {
        Integer id = foodItemImageDTO.getId();
        String location = imageLocationPrefix + "/" + id;
        foodItemImageDTO.setId(id);
        foodItemImageDTO.setFoodItemId(null);
        foodItemImageDTO.setFileName(null);
        foodItemImageDTO.setFileExtension(null);
        if (Boolean.TRUE.equals(includeData)) {
            foodItemImageDTO.setLocation(null);
        } else {
            foodItemImageDTO.setLocation(location);
        }
        return foodItemImageDTO;
    }

    private List<FoodItemImageDTO> foodItemImageDTOList(List<FoodItemImageDTO> foodItemImageDTOList, String imageLocationPrefix, Boolean includeData) {
        for (FoodItemImageDTO image : foodItemImageDTOList) {
            formatFoodItemImageDTO(image, imageLocationPrefix, includeData);
        }
        return foodItemImageDTOList;
    }



}
