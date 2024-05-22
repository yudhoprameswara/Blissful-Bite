package com.BlissfulBite.BlissfulBite.service;

import com.BlissfulBite.BlissfulBite.dto.admin.FoodListDTO;
import com.BlissfulBite.BlissfulBite.dto.admin.UpsertFoodDTO;
import com.BlissfulBite.BlissfulBite.entity.Food;
import com.BlissfulBite.BlissfulBite.repository.FoodRepository;
import com.BlissfulBite.BlissfulBite.utility.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private FoodRepository foodRepository;

    public Page<FoodListDTO> GetList(String name, Integer page){
        var pageable = PageRequest.of(page - 1, 10, Sort.by("id"));
        var rows= foodRepository.getList(name,pageable);
        return rows;
    };

    public UpsertFoodDTO findOne(Long id){
        var entity = foodRepository.findById(id).get();
        var dto = new UpsertFoodDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setCategory(entity.getCategory());
            dto.setPrice(entity.getPrice());
            dto.setAvailability(entity.getAvailability());
            dto.setDescription(entity.getDescription());
            dto.setImagePath(entity.getImagePath());
        return dto;
    }

    public void imageFileHandler(UpsertFoodDTO dto){
        var multipartFile = dto.getImage();
        var isMultipartEmpty = multipartFile.isEmpty();
        var imagePath = ((dto.getImagePath() == null || dto.getImagePath().equals(""))
                && isMultipartEmpty) ? null : dto.getImagePath();
        try{
            if(!isMultipartEmpty){
                imagePath = FileHelper.uploadProductPhoto(imagePath, multipartFile);
            }
            dto.setImagePath(imagePath);

        } catch (Exception exception){
            System.out.println("eh kena");
        }
    }

    public void save(UpsertFoodDTO dto){
        var entity = new Food();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        if(dto.getAvailability() == null){
            entity.setAvailability(false);
        }
        entity.setAvailability(dto.getAvailability());
        entity.setDescription(dto.getDescription());
        if(dto.getImagePath() == null){
            var imagePath =foodRepository.findById(dto.getId()).get().getImagePath();
            entity.setImagePath(imagePath);
        } else if (dto.getImagePath() != null) {
            entity.setImagePath(dto.getImagePath());
        }
        foodRepository.save(entity);
    }

    public void delete(Long id){
        String imagePath = foodRepository.getImagePath(id);
        FileHelper.deleteProductPhoto(imagePath);
        foodRepository.deleteById(id);
    }
}
