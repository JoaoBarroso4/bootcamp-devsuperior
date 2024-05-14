package com.devsuperior.bootcamp.dto;

import com.devsuperior.bootcamp.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    public CategoryDTO(Category entity) {
        id = entity.getId();
        name = entity.getName();
    }

    private Long id;
    private String name;
}
