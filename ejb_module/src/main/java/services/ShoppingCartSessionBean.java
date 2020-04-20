package services;

import dao.OrderDAO;
import interfaces.ShoppingCartLocal;
import model.Order;
import model.OrderItem;
import model.Product;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.stream.Stream;

@Stateless
public class ShoppingCartSessionBean implements ShoppingCartLocal {

//    @Inject
//    private OrderDAO orderDAO;

    @Override
    public Order add(Order shoppingCartOrder, Product selectedProduct, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(selectedProduct);
        orderItem.setPrice(selectedProduct.getPrice());
        orderItem.setQuantity(quantity);

        shoppingCartOrder.addOrderItem(orderItem);
        return shoppingCartOrder;
    }

    @Override
    public Order remove(Order shoppingCartOrder, OrderItem orderItem) {
        shoppingCartOrder.removeOrderItem(orderItem);
        return shoppingCartOrder;
    }

    @Override
    public Order clear(Order order) {
        order.getOrderItems().clear();
        return order;
    }

    @Override
    public boolean processOrder(Order order) {
//        try {
//            orderDAO.create(order);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return false;
    }

    @Override
    public double updateOrderAmount(Order shoppingCart) {
        return shoppingCart.getOrderItems().stream()
                .mapToDouble(orderItem ->  orderItem.getPrice()*orderItem.getQuantity())
                .sum();
    }
}
