package interfaces;

import model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IndexLocal {

    List<Product> searchProduct(String searchInput, List<Product> list);

    List<Product> fetchProducts();

    void modal(Product product);
}
