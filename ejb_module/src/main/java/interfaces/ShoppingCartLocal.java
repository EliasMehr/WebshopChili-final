package interfaces;

import model.Order;
import model.OrderItem;
import model.Product;
import model.User;

import javax.ejb.Local;

@Local
public interface ShoppingCartLocal {

    Order add(Product selectedProduct, int quantity);

    Order remove(OrderItem orderItem);

    Order clear();

    Order processOrder(User user);

    Order decrementItemQuantity(OrderItem item);

    Order incrementItemQuantity(OrderItem item);

    double updateOrderAmount();

    Order updateOrderPricing(double discount);

}
