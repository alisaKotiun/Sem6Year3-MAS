//package mas;
//
//import lombok.RequiredArgsConstructor;
//import mas.model.*;
//import mas.repository.*;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Component
//@RequiredArgsConstructor
//public class DataInitializer {
//
//    //DROP TABLE ORDERED_BOOK, BOOK_CART,  COLLECTION_CART, EMPLOYEE_ROLE, COST_UTIL, CUSTOMER_MAX_DISCOUNT,EMPLOYEE_BENEFIT  , CONTRACT, PUBLISHING_HOUSE, GENRE, SHOPPING_CART, INDIVIDUAL, COMPANY, B_ORDER, CUSTOMER, EMPLOYEE, BOOK_AUTHOR, AUTHOR , PERSON, BOOK, COLLECTION
//
//    private final CostUtilRepository costUtilRepository;
//    private final CustomerDiscountRepository customerDiscountRepository;
//    private final EmployeeBenefitRepository employeeBenefitRepository;
//    private final CollectionRepository collectionRepository;
//    private final PublishingHouseRepository publishingHouseRepository;
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//    private final EmployeeRepository employeeRepository;
//    private final PersonRepository personRepository;
//    private final IndividualRepository individualRepository;
//    private final CompanyRepository companyRepository;
//    private final OrderRepository orderRepository;
//    private final ContractRepository contractRepository;
//    private final BookAuthorRepository bookAuthorRepository;
//    private final OrderedBookRepository orderedBookRepository;
//
//    @EventListener
//    public void atStart(ContextRefreshedEvent event) {
//        System.out.println("EVENT: " + event.toString());
//
//        List<CostUtil> costUtils = costUtilFactory();
//        costUtilRepository.saveAll(costUtils);
//
//        List<CustomerMaxDiscount> customerMaxDiscounts = cmdFactory();
//        customerDiscountRepository.saveAll(customerMaxDiscounts);
//
//        List<EmployeeBenefit> employeeBenefits = employeeBenefitFactory();
//        employeeBenefitRepository.saveAll(employeeBenefits);
//
//        List<Collection> collections = collectionFactory();
//        collectionRepository.saveAll(collections);
//
//        List<PublishingHouse> publishingHouses = publishingHouseFactory();
//        publishingHouseRepository.saveAll(publishingHouses);
//
//        List<Author> authors = authorFactory();
//        authorRepository.saveAll(authors);
//
//        List<Book> books = bookFactory(collections);
//        bookRepository.saveAll(books);
//
//        List<Employee> employees = employeeFactory();
//        employeeRepository.saveAll(employees);
//
//        List<Individual> individuals = individualFactory();
//        individualRepository.saveAll(individuals);
//
//        List<Company> companies = companyFactory();
//        companyRepository.saveAll(companies);
//
//        List<Order> orders = orderFactory(companies.get(0));
//        orderRepository.saveAll(orders);
//
//        //
//
//        List<BookAuthor> bookAuthors = createBookAuthor(books, authors);
//        bookAuthorRepository.saveAll(bookAuthors);
//
//        List<Contract> contracts = createContract(List.of(authors.get(5), authors.get(4)), List.of(publishingHouses.get(0), publishingHouses.get(1)));
//        contractRepository.saveAll(contracts);
//
//        List<OrderedBook> orderedBooks = createOrderBooks(orders.get(0), List.copyOf(collections.get(0).getBooks()));
//        orderedBookRepository.saveAll(orderedBooks);
//
//        publishingHouseRepository.saveAll(publishingHouses);
//        bookRepository.saveAll(books);
//        orderRepository.saveAll(orders);
//        collectionRepository.saveAll(collections);
//        authorRepository.saveAll(authors);
//        companyRepository.saveAll(companies);
//
//    }
//
//    private List<CostUtil> costUtilFactory() {
//        List<CostUtil> result = new ArrayList<>();
//
//            result.add(CostUtil.builder()
//                    .discount(20)
//                    .build());
//        return result;
//    }
//
//    private List<CustomerMaxDiscount> cmdFactory(){
//        List<CustomerMaxDiscount> result = new ArrayList<>();
//
//        CustomerMaxDiscount cmd1 = CustomerMaxDiscount.builder()
//                .status(LoyaltyStatus.NEW)
//                .discount(5)
//                .build();
//
//        CustomerMaxDiscount cmd2 = CustomerMaxDiscount.builder()
//                .status(LoyaltyStatus.LOYAL)
//                .discount(10)
//                .build();
//
//        CustomerMaxDiscount cmd3 = CustomerMaxDiscount.builder()
//                .status(LoyaltyStatus.BLACKLIST)
//                .discount(0)
//                .build();
//
//        result.add(cmd1);
//        result.add(cmd2);
//        result.add(cmd3);
//
//        return result;
//    }
//
//    private List<EmployeeBenefit> employeeBenefitFactory(){
//        List<EmployeeBenefit> result = new ArrayList<>();
//
//        EmployeeBenefit eb1 = EmployeeBenefit.builder()
//                .role(EmployeeRole.MANAGER)
//                .benefit(1250.5)
//                .build();
//
//        EmployeeBenefit eb2 = EmployeeBenefit.builder()
//                .role(EmployeeRole.CONSULTANT)
//                .benefit(850.0)
//                .build();
//
//        EmployeeBenefit eb3 = EmployeeBenefit.builder()
//                .role(EmployeeRole.COURIER)
//                .benefit(750.5)
//                .build();
//
//        result.add(eb1);
//        result.add(eb2);
//        result.add(eb3);
//
//        return  result;
//    }
//
//    private List<Collection> collectionFactory() {
//        List<Collection> result = new ArrayList<>();
//
//        Collection collection1 = Collection.builder()
//                    .name("Women of XXI century")
//                    .status(CollectionStatus.ACTIVE)
//                    .build();
//
//        Collection collection2 = Collection.builder()
//                .name("Polish fantasy")
//                .status(CollectionStatus.ACTIVE)
//                .build();
//
//        result.add(collection1);
//        result.add(collection2);
//        return result;
//    }
//
//    private List<PublishingHouse> publishingHouseFactory() {
//        List<PublishingHouse> result = new ArrayList<>();
//            PublishingHouse ph1 = PublishingHouse.builder()
//                .name("BookHouse")
//                .email("bh@gmail.com")
//                .address(new Address("Poland", "Warsaw", "Koszykowa", "2a", 1))
//                .build();
//
//        PublishingHouse ph2 = PublishingHouse.builder()
//                .name("ReadCo")
//                .email("read@rc.com")
//                .address(new Address("Poland", "Warsaw", "Sowinskiego", "113", 10))
//                .build();
//        result.add(ph1);
//        result.add(ph2);
//        return result;
//    }
//
//    private List<Author> authorFactory() {
//        List<Author> result = new ArrayList<>();
//
//        Author author1 = Author.builder()
//                    .firstName("Emily")
//                    .lastName("Bronte")
//                    .dateOfBirth(Date.valueOf("1831-04-13"))
//                    .build();
//        Author author2 = Author.builder()
//                .firstName("Jane")
//                .lastName("Austen")
//                .dateOfBirth(Date.valueOf("1791-04-13"))
//                .build();
//        Author author3 = Author.builder()
//                .firstName("Charlotte")
//                .lastName("Bronte")
//                .dateOfBirth(Date.valueOf("1891-04-13"))
//                .build();
//        Author author4 = Author.builder()
//                .firstName("Andrzej")
//                .lastName("Sapkowski")
//                .dateOfBirth(Date.valueOf("1971-04-13"))
//                .build();
//        Author author5 = Author.builder()
//                .firstName("Jaroslaw")
//                .lastName("Grzedowicz")
//                .dateOfBirth(Date.valueOf("1991-04-13"))
//                .build();
//        Author author6 = Author.builder()
//                .firstName("Jakub")
//                .lastName("Cwiek")
//                .dateOfBirth(Date.valueOf("1981-04-13"))
//                .build();
//
//        result.add(author1);
//        result.add(author2);
//        result.add(author3);
//        result.add(author4);
//        result.add(author5);
//        result.add(author6);
//        return result;
//    }
//
//    private List<Book> bookFactory (List<Collection> collections) {
//        List<Book> result = new ArrayList<>();
//
//        Book book1 = Book.builder()
//                .ISBN("1234567890123")
//                .name("Wuthering Heights") //
//                .publishDate(Date.valueOf("1847-01-01"))
//                .cost(new Cost(56.5, 0))
//                .genres(Set.of("Romantic"))
//                .picture("https://img.smyk.com/681x/filters:no_upscale()/https://beta-cdn.smyk.com/media/product/1600/1/wuthering-heights-6802464.jpg")
//                .build();
//        book1.setCollection(collections.get(0));
//        book1.setCoverOfCollection(collections.get(0));
//        collections.get(0).getBooks().add(book1);
//        collections.get(0).setCover(book1);
//
//        Book book2 = Book.builder()
//                .ISBN("1234567890124")
//                .name("Pride and Prejudice") //Jane Austen
//                .publishDate(Date.valueOf("1813-01-01"))
//                .cost(new Cost(85.23, 0))
//                .genres(Set.of("Romantic", "Drama"))
//                .picture("http://prodimage.images-bn.com/pimages/9781499369748_p0_v3_s1200x630.jpg")
//                .build();
//        book2.setCollection(collections.get(0));
//        collections.get(0).getBooks().add(book2);
//
//        Book book3 = Book.builder()
//                .ISBN("1234567890153")
//                .name("Villette") //Charlotte Bronte
//                .publishDate(Date.valueOf("1901-01-01"))
//                .cost(new Cost(69.2, 0))
//                .genres(Set.of("Romantic", "Tragedy"))
//                .picture("https://www.swiatksiazki.pl/media/catalog/product/cache/eaf55611dc9c3a2fa4224fad2468d647/x/1/x199906722401.jpg")
//                .build();
//        book3.setCollection(collections.get(0));
//        collections.get(0).getBooks().add(book3);
//
//        Book book4 = Book.builder()
//                .ISBN("1234567890253")
//                .name("Blood of Elves") //Andrzej Sapkowski
//                .publishDate(Date.valueOf("2019-01-01"))
//                .cost(new Cost(111.0, 0))
//                .genres(Set.of("Fantasy"))
//                .picture("https://upload.wikimedia.org/wikipedia/en/6/61/Blood_of_Elves_UK.jpg")
//                .build();
//        book4.setCollection(collections.get(1));
//        book4.setCoverOfCollection(collections.get(1));
//        collections.get(1).getBooks().add(book4);
//        collections.get(1).setCover(book4);
//
//        Book book5 = Book.builder()
//                .ISBN("1234267890253")
//                .name("Pan Lodowego Ogrodu") //Jarosław Grzędowicz
//                .publishDate(Date.valueOf("2020-01-01"))
//                .cost(new Cost(93.0, 0))
//                .genres(Set.of("Fantasy", "History"))
//                .picture("https://ecsmedia.pl/c/pan-lodowego-ogrodu-tom-1-b-iext103941892.jpg")
//                .build();
//        book5.setCollection(collections.get(1));
//        collections.get(1).getBooks().add(book5);
//
//        Book book6 = Book.builder()
//                .ISBN("1224567890253")
//                .name("Bog marnotrawny") //Jakub Ćwiek
//                .publishDate(Date.valueOf("2017-01-01"))
//                .cost(new Cost(56.0, 0))
//                .genres(Set.of("Fantasy"))
//                .picture("https://www.swiatksiazki.pl/media/catalog/product/cache/eaf55611dc9c3a2fa4224fad2468d647/3/8/3899903750338-7553.jpg")
//                .build();
//        book6.setCollection(collections.get(1));
//        collections.get(1).getBooks().add(book6);
//
//        result.add(book1);
//        result.add(book2);
//        result.add(book3);
//        result.add(book4);
//        result.add(book5);
//        result.add(book6);
//        return result;
//    }
//
//    private List<Employee> employeeFactory () {
//        List<Employee> result = new ArrayList<>();
//
//        Employee emp1 = Employee.builder()
//                    .firstName("John")
//                    .lastName("Marul")
//                    .dateOfBirth(Date.valueOf("2002-01-01"))
//                    .login("login")
//                    .password("pass")
//                    .salary(1230.30)
//                    .roles(Set.of(EmployeeRole.MANAGER))
//                    .build();
//
//        Employee emp2 = Employee.builder()
//                .firstName("Ann")
//                .lastName("Varli")
//                .dateOfBirth(Date.valueOf("1988-03-01"))
//                .login("login")
//                .password("pass")
//                .salary(2230.30)
//                .roles(Set.of(EmployeeRole.MANAGER, EmployeeRole.CONSULTANT))
//                .build();
//
//        Employee emp3 = Employee.builder()
//                .firstName("Varwo")
//                .lastName("Mei")
//                .dateOfBirth(Date.valueOf("1973-03-12"))
//                .login("login")
//                .password("pass")
//                .salary(2130.30)
//                .roles(Set.of(EmployeeRole.COURIER))
//                .build();
//
//        result.add(emp1);
//        result.add(emp2);
//        result.add(emp3);
//        return result;
//    }
//
//    private List<Individual> individualFactory() {
//        List<Individual> result = new ArrayList<>();
//
//        Individual ind1 = Individual.builder()
//                    .person(createPerson("Alisa", "Kotiun", "2002-04-13"))
//                    .email("akotiun@gmail.com")
//                    .phoneNumber("123-343-123")
//                    .address(new Address("Poland", "Warsaw", "ul. Grojecka", "12a", 12))
//                    .status(LoyaltyStatus.NEW)
//                    .discount(0)
//                    .description(null)
//                    .build();
//
//        Individual ind2 = Individual.builder()
//                .person(createPerson("Ann", "Joy", "2000-02-19"))
//                .email("jao@gmail.com")
//                .phoneNumber("123-343-321")
//                .address(new Address("Poland", "Warsaw", "ul. Radosna", "45", null))
//                .status(LoyaltyStatus.LOYAL)
//                .discount(0)
//                .description(null)
//                .build();
//
//        Individual ind3 = Individual.builder()
//                .person(createPerson("Tom", "Dowlow", "1998-02-29"))
//                .email("ilower21@gmail.com")
//                .phoneNumber("1233-343-321")
//                .address(new Address("Malta", "Valetta", "ul. Ksira", "4A5", null))
//                .status(LoyaltyStatus.LOYAL)
//                .discount(0)
//                .description(null)
//                .build();
//
//        result.add(ind1);
//        result.add(ind2);
//        result.add(ind3);
//        return result;
//    }
//
//    private List<Company> companyFactory() {
//        List<Company> result = new ArrayList<>();
//
//        Company com1 = Company.builder()
//                    .name("Book Co")
//                    .REGON("1133456789")
//                    .email("book@bokk.com")
//                    .phoneNumber("123-4322-23")
//                    .address(new Address("Poland", "Warsaw", "ul. Jana Pawla ", "1", null))
//                    .status(LoyaltyStatus.NEW)
//                    .discount(0)
//                    .description(null)
//                    .build();
//
//        Company com2 = Company.builder()
//                .name("BookStoreCo")
//                .REGON("1143453789")
//                .email("bookCo@bk.com")
//                .phoneNumber("123-422-23")
//                .address(new Address("Poland", "Warsaw", "ul. Jana Pawla ", "102", 11))
//                .status(LoyaltyStatus.NEW)
//                .discount(0)
//                .description(null)
//                .build();
//
//        result.add(com1);
//        result.add(com2);
//        return result;
//    }
//
//    private List<Order> orderFactory(Customer customer) {
//        List<Order> result = new ArrayList<>();
//
//        Order or1 = Order.builder()
//                    .creationDate(Date.valueOf(LocalDate.now()))
//                    .address(customer.getAddress())
//                    .isDelivered(false)
//                    .isSubmitted(true)
//                    .customer(customer)
//                    .build();
//
//        customer.getOrders().add(or1);
//        result.add(or1);
//        return result;
//    }
//
//    private List<BookAuthor> createBookAuthor(List<Book> books, List<Author> authors) {
//        List<BookAuthor> result = new ArrayList<>();
//
//        for(int i = 0; i < books.size(); i++) {
//            BookAuthor ba = BookAuthor.builder()
//                    .book(books.get(i))
//                    .author(authors.get(i))
//                    .build();
//            result.add(ba);
//
//            books.get(i).getBookAuthors().add(ba);
//            authors.get(i).getBookAuthors().add(ba);
//        }
//
//        return  result;
//    }
//
//    private List<Contract> createContract(List<Author> authors, List<PublishingHouse> publishingHouses) {
//        List<Contract> result = new ArrayList<>();
//
//        for(int i = 0; i < authors.size(); i++) {
//            Contract contract = Contract.builder()
//                    .author(authors.get(i))
//                    .publishingHouse(publishingHouses.get(i))
//                    .startDate(Date.valueOf("2020-02-01"))
//                    .endDate(Date.valueOf("2024-02-01"))
//                    .build();
//
//            result.add(contract);
//            authors.get(i).getContracts().add(contract);
//            publishingHouses.get(i).getContracts().add(contract);
//        }
//
//        return  result;
//    }
//
//    private List<OrderedBook> createOrderBooks(Order order, List<Book> books) {
//        List<OrderedBook> result = new ArrayList<>();
//
//        for(Book book : books) {
//            OrderedBook ob = OrderedBook.builder()
//                    .order(order)
//                    .book(book)
//                    .quantity(1)
//                    .build();
//
//            result.add(ob);
//            book.getOrderedBooks().add(ob);
//        }
//        return result;
//    }
//
//    private Person createPerson(String name, String lName, String dob) {
//        Person person = Person.builder()
//                .firstName(name)
//                .lastName(lName)
//                .dateOfBirth(Date.valueOf(dob))
//                .build();
//
//        personRepository.save(person);
//        return person;
//    }
//}
