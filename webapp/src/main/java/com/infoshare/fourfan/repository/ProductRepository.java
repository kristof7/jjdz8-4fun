package com.infoshare.fourfan.repository;

import com.infoshare.fourfan.domain.datatypes.Product;
import com.infoshare.fourfan.domain.datatypes.Shop;

import javax.ejb.Local;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Local
public interface ProductRepository {

    Optional<Product> findProductByName(String name) throws IOException;

    Optional<Product> findProductById(Integer id) throws IOException;

    List<Product> findAllJson() throws IOException;

    void saveToJson(Product product) throws IOException;

    void deleteProductFromJson(Product product) throws IOException;

    List<Product> filterByCategory(Integer category);

    List<Product> filterByCalories(Integer caloriesMin, Long caloriesMax);

    Map<Shop, List<Product>> filterByPriceAndGroupByShop(Integer priceMin, Integer priceMax);

}
