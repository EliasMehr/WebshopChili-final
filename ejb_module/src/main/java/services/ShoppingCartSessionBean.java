package services;

import dao.OrderDAO;
import interfaces.ShoppingCartLocal;
import model.Order;
import model.OrderItem;
import model.Product;
import model.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;

@Stateless
public class ShoppingCartSessionBean implements ShoppingCartLocal {

    @Inject
    private OrderDAO orderDAO;

    private User currentUser;
    private Order currentOrder = new Order();

    @Override
    public Order add(Product selectedProduct, int quantity) {
        boolean isAlreadyCart = false;

        for(OrderItem orderItem : currentOrder.getOrderItems()) {
            if (orderItem.getProduct().equals(selectedProduct)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                isAlreadyCart = true;
            }
        }

        if (!isAlreadyCart) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(selectedProduct);
        orderItem.setPrice(selectedProduct.getPrice());
        orderItem.setQuantity(quantity);

        currentOrder.addOrderItem(orderItem);
        }

        return currentOrder;
    }

    @Override
    public Order remove(OrderItem orderItem) {
        currentOrder.removeOrderItem(orderItem);
        return currentOrder;
    }

    @Override
    public Order clear() {
        currentOrder.getOrderItems().clear();
        return currentOrder;
    }

    @Override
    public Order processOrder() {
        try {
            currentOrder.setDate(new Date());
            currentUser.addOrder(currentOrder);
            orderDAO.create(currentOrder);
            currentOrder = new Order();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentOrder;
    }

    @Override
    public double updateOrderAmount() {
        return currentOrder.getOrderItems()
                .stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
    }

    @Override
    public void initializeUser(User user) {
        currentUser = user;
    }

    @Override
    public boolean isLoggedIn() {
        return currentUser != null;
    }

}
