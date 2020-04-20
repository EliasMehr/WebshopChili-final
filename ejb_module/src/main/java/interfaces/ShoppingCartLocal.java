package interfaces;

import model.Order;
import model.OrderItem;
import model.Product;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {

    Order add(Order shoppingCart, Product selectedProduct, int quantity);
    Order remove(Order shoppingCart, OrderItem orderItem);
    Order clear(Order order);

    boolean processOrder(Order order);
    double updateOrderAmount (Order shoppingCart);
}
