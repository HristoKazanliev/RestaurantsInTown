package com.example.RestaurantsInTown.init;

import com.example.RestaurantsInTown.model.entity.Category;
import com.example.RestaurantsInTown.model.enums.CategoryEnum;
import com.example.RestaurantsInTown.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitCategories implements CommandLineRunner {
    private final Map<CategoryEnum, String> descriptions = Map.of(
            CategoryEnum.FAST_FOOD, "Fast food restaurants attract diners because of their price, convenience, and speed.",
            CategoryEnum.CAFE, "Cafes offer a relaxed atmosphere, often serving as a neighborhood hub where people meet over coffee and light bites.",
            CategoryEnum.PUB, "A pub is in several countries a drinking establishment licensed to serve alcoholic drinks for consumption on the premises.",
            CategoryEnum.FINE_DINING, "Fine dining restaurants offer more sophisticated, elevated, higher-quality dining experiences."
    );

    private final CategoryRepository categoryRepository;

    public InitCategories(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = this.categoryRepository.count();

        if (count > 0) {
            return;
        }

        for (CategoryEnum categoryEnum : descriptions.keySet()) {
            Category category = new Category(categoryEnum, descriptions.get(categoryEnum));

            this.categoryRepository.save(category);
        }
    }
}
