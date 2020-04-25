package services;

import dao.ProductDAO;
import interfaces.ProductLocal;
import interfaces.UserManagementLocal;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductBean implements ProductLocal {

    @Inject
    private ProductDAO productDAO;

    @Inject
    private UserManagementLocal userManagement;

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

    @Override
    public List<Product> updateProductPricing(List<Product> originalList) {
        System.out.println(originalList);
        System.out.println(originalList.get(0).getPrice());

        List<Product> list = new ArrayList<>(originalList);
        System.out.println(list);

        list.forEach(product -> product.setPrice(product.getPrice() * userManagement.getUser().getRole().getDiscountMultiplier()));
        return list;
    }
}
