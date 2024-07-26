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
            CategoryEnum.BULGARIAN, "Bulgarian cuisine is exceptionally diverse and delicious, consisting of various salads, breadstuffs, stews, and other local dishes.",
            CategoryEnum.JAPANESE, "The traditional cuisine of Japan is based on rice with miso soup and other dishes with an emphasis on seasonal ingredients.",
            CategoryEnum.ITALIAN, "The Mediterranean diet forms the basis of Italian cuisine, rich in pasta, fish, fruits and vegetables.",
            CategoryEnum.TURKISH, "Turkish cuisine is known for its rich flavors and diverse dishes."
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
