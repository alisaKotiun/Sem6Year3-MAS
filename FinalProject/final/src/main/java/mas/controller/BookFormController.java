package mas.controller;

import lombok.RequiredArgsConstructor;
import mas.model.*;
import mas.model.Collection;
import mas.repository.CostUtilRepository;
import mas.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
public class BookFormController {
    private final CollectionService collectionService;
    private final CartService cartService;
    private final BookService bookService;
    private final OrderService orderService;
    private final PHService phService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final CostUtilRepository costUtilRepository;

    //collection
    @GetMapping(value = "/collection")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<CollectionDTO> getCollection() {
        List<CollectionDTO> result = new ArrayList<>();
        for(Collection col : collectionService.findAll()){
            CollectionDTO dto = new CollectionDTO(col.getId(), col.getName(), col.getStatus(), col.getCover(), col.getFinalPrice(), col.getItemsNumber());
            result.add(dto);
        }
        return  result;
        //return collectionService.findAll();
    }

    @GetMapping(value = "/collection/books/{collectionId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<Book> findAllBooks(@PathVariable Long collectionId) {
        try {
            return collectionService.findAllBooks(collectionId);
        } catch (IllegalArgumentException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }


    //cart
    @PostMapping(value = "/cart")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody ShoppingCart saveCart() {
        ShoppingCart cart = ShoppingCart.builder()
                .creationDate(Date.valueOf(LocalDate.now()))
                .build();

        cartService.save(cart);
        return cart;
    }

    @DeleteMapping(value = "/cart")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void deleteCarts() {
        cartService.deleteAll();
    }

    @GetMapping(value = "/cart/price/{cartId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Double getPrice(@PathVariable Long cartId) {
        return cartService.getPrice(cartId);
    }

    @PostMapping(value = "/collection-cart/{collectionId}/{cartId}/{quantity}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void saveCollectionToCart(@PathVariable Long collectionId, @PathVariable Long cartId, @PathVariable Integer quantity) {
        cartService.saveCollection(collectionId, cartId, quantity);
    }


    //book
    @GetMapping(value = "/book/after")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<Book> findBooksPublishedAfter(@RequestBody Date date) {
        return bookService.findBooksPublishedAfter(date);
    }

    @GetMapping(value = "/book/ISBN/{ISBN}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<Book> findBooksByISBN(@PathVariable String ISBN) {
        return bookService.findBooksByISBN(ISBN);
    }

    //maxDiscountImplemented
    @PostMapping(value = "/book")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Book createBook(@RequestBody Book book) {
        List<CostUtil> costUtils = StreamSupport.stream(costUtilRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        Integer maxDiscount = costUtils.size() == 0 ? 100 : costUtils.get(0).getDiscount();
        if(book.getCost().getDiscount() > maxDiscount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount invalid value", new IllegalArgumentException());
        }
        return bookService.save(book);
    }

    //order
    @GetMapping(value = "/order/price/{orderId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Double getFinalPrice(@PathVariable Long orderId) {
        return orderService.getFinalPrice(orderId);
    }

    @PatchMapping(value = "/order/deliver/{orderId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void isDelivered(@PathVariable Long orderId) {
        orderService.deliver(orderId);
    }

    @PatchMapping(value = "/order/submit/{orderId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void isSubmitted(@PathVariable Long orderId) {
        orderService.submit(orderId);
    }


    //publish house
    @GetMapping(value = "/publishingHouse/authors/{phId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<Author> findWorkingAuthors(@PathVariable Long phId) {
        return phService.findWorkingAuthors(phId);
    }


    //customer
    @PatchMapping(value = "/customer/{customerId}/status/{status}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void changeStatus(@PathVariable Long customerId, @PathVariable LoyaltyStatus status){
        customerService.changeStatus(customerId, status);
    }

    @GetMapping(value = "/customer/currentOrders/{customerId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody List<Order> findCurrentOrders(@PathVariable Long customerId){
        return customerService.findCurrentOrders(customerId);
    }

    @GetMapping(value = "/customer/{customerId}/price/{bookId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Double calculatePrice(@PathVariable Long bookId, @PathVariable Long customerId) {
        return customerService.calculatePrice(customerId, bookId);
    }

    @GetMapping(value = "/customer/maxDiscount/{customerId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Integer calculateMaxDiscount(@PathVariable Long customerId) {
        return customerService.calculateMaxDiscount(customerId);
    }


    //employee
    @PatchMapping(value = "/employee/{empId}/addRole/{role}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void addRole(@PathVariable EmployeeRole role, @PathVariable Long empId) {
        employeeService.addRole(empId, role);
    }

    @PatchMapping(value = "/employee/{empId}/deleteRole/{role}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody void deleteRole(@PathVariable EmployeeRole role, @PathVariable Long empId) {
        try {
            employeeService.deleteRole(empId, role);
        } catch (IllegalArgumentException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
    }

    @GetMapping(value = "/employee/salary/{empId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public @ResponseBody Double calculateSalary(@PathVariable Long empId, @RequestBody Boolean withBenefit) {
        return employeeService.calculateSalary(empId, withBenefit);
    }

    private static <T> Iterable<T> getIterableFromIterator(Iterator<T> iterator) {
        return () -> iterator;
    }
}
