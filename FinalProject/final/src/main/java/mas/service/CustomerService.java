package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.*;
import mas.repository.CustomerDiscountRepository;
import mas.repository.CustomerRepository;
import mas.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService{

    protected final CustomerRepository customerRepository;
    protected final OrderRepository orderRepository;
    private final CustomerDiscountRepository customerDiscountRepository;

    @Override
    public Integer calculateMaxDiscount(Long id) {
        Optional<Customer> customer = customerRepository.findCustomerById(id);
        if(customer.isPresent()){
            CustomerMaxDiscount cmd = customerDiscountRepository.findFirstByStatus(customer.get().getStatus());
            return cmd.getDiscount();
        }
        return 0;
    }

    @Override
    public void changeStatus(Long id, LoyaltyStatus status) {
        Iterable<Customer> customers = customerRepository.findAllById(getIterableFromIterator(Set.of(id).iterator()));
        for(Customer customer : customers) {
            customer.setStatus(status);
        }
    }

    @Override
    public List<Order> findCurrentOrders(Long id) {
        Iterable<Customer> iterable = customerRepository.findAllById(getIterableFromIterator(Set.of(id).iterator()));
        List<Customer> customers = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());

        if(customers.size() != 1) return null;

        Iterable<Order> iterableOrders = orderRepository.findOrderByCustomerIs(customers.get(0));

        return StreamSupport
                .stream(iterableOrders.spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Double calculatePrice(Long id, Long bookId) {
        return 0.;
    }

    private static <T> Iterable<T> getIterableFromIterator(Iterator<T> iterator) {
        return () -> iterator;
    }
}
