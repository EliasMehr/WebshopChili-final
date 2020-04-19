package services;

import dao.ProductDAO;
import interfaces.IndexLocal;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class IndexSessionBean implements IndexLocal {

    @Inject
    ProductDAO productDAO;


    @Override
    public List<Product> searchProduct(String searchInput, List<Product> list) {

        return list.stream()
                .filter(product -> product.getName().toLowerCase().contains(searchInput.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> fetchProducts() {
        return productDAO.findAll();
    }

    @Override
    public void modal(Product product) {
        // Diskussion hur vi kan lösa det enklare
    }
}
