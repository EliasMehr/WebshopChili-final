package services;

import dao.ProductDAO;
import interfaces.ProductLocal;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductSessionBean implements ProductLocal {

    @Inject
    private ProductDAO productDAO;

    @Override
    public List<Product> loadAllProducts() {
        return productDAO.getAllProducts();
    }
}
