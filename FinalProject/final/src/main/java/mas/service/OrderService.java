package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.Order;
import mas.model.OrderedBook;
import mas.repository.CustomerRepository;
import mas.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{

    private final OrderRepository orderRepository;

    @Override
    public Double getFinalPrice(Long id) {
        Optional<Order> order = orderRepository.findOrderById(id);

        if (order.isEmpty()) return 0.;

        double finalPrice = 0;

        for(OrderedBook orderedBook : order.get().getOrderedBooks()){
            finalPrice += orderedBook.getBook().getCost().getFinalCost();
        }
        return finalPrice;
    }

    @Override
    public void deliver(Long id) {
        Optional<Order> order = orderRepository.findOrderById(id);

        if(order.isPresent()) {
            order.get().setDelivered(true);
        }
    }

    @Override
    public void submit(Long id) {
        Optional<Order> order = orderRepository.findOrderById(id);

        if(order.isPresent()) {
            order.get().setSubmitted(true);
        }
    }
}
