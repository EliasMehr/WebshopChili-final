package services;

import dao.ProductDAO;
import interfaces.ProductLocal;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductBean implements ProductLocal {

    @Inject
    private ProductDAO productDAO;

    @Override
    public List<Product> loadAllProducts() {
        return productDAO.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProduct(String searchInput, List<Product> list) {

        return list.stream()
                .filter(product -> product.getName().toLowerCase().contains(searchInput.toLowerCase()))
                .distinct()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
    }

}