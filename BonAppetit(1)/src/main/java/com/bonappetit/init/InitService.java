package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.enums.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class InitService implements CommandLineRunner {

    public CategoryRepository categoryRepository;

    public InitService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(categoryRepository.count()==0){
            categoryRepository.saveAll(List.of(
                    new Category(CategoryName.MAIN_DISH,
                            "Heart of the meal, substantial and satisfying; main dish delights taste buds."),
                    new Category(CategoryName.DESSERT,
                            "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy."),
                    new Category(CategoryName.COCKTAIL,
                            "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass")
            ));
        }
    }
}
