package mas.service;

import mas.model.Book;
import mas.model.Customer;
import mas.repository.BookRepository;
import mas.repository.CustomerDiscountRepository;
import mas.repository.CustomerRepository;
import mas.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndividualService extends CustomerService implements IIndividualService{

    private final BookRepository bookRepository;

    public IndividualService(CustomerRepository customerRepository, OrderRepository orderRepository, CustomerDiscountRepository customerDiscountRepository, BookRepository bookRepository) {
        super(customerRepository, orderRepository, customerDiscountRepository);

        this.bookRepository = bookRepository;
    }


    @Override
    public Double calculatePrice(Long id, Long bookId) {
        Optional<Customer> customer = customerRepository.findCustomerById(id);
        Optional<Book> book = bookRepository.findBookById(bookId);

        if(customer.isEmpty() || book.isEmpty()) return 0.;

        double bookCost = book.get().getCost().getFinalCost();

        return bookCost - (bookCost * customer.get().getDiscount() / 100);
    }
}
