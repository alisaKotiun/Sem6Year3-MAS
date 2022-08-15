package mas.service;

import lombok.RequiredArgsConstructor;
import mas.model.*;
import mas.repository.CollectionRepository;
import mas.repository.Collection_CartRepository;
import mas.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ShoppingCartRepository cartRepository;
    private final CollectionRepository collectionRepository;
    private final Collection_CartRepository collectionCartRepository;

    @Override
    public Double getPrice(Long id) {
        Optional<ShoppingCart> cart = cartRepository.findShoppingCartById(id);

        if (cart.isEmpty()) return 0.;

        return getBooksPrice(cart.get()) + getCollectionsPrice(cart.get());
    }

    @Override
    public void save(ShoppingCart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteAll() {
        cartRepository.deleteAll();
    }

    @Override
    public void saveCollection(Long collectionId, Long cartId, Integer quantity) {
        Optional<Collection> collection = collectionRepository.findCollectionById(collectionId);
        Optional<ShoppingCart> cart = cartRepository.findShoppingCartById(cartId);

        if(collection.isPresent() && cart.isPresent()) {
            Collection_Cart collectionCart = Collection_Cart.builder()
                    .collection(collection.get())
                    .shoppingCart(cart.get())
                    .quantity(quantity)
                    .build();

            collection.get().getCollectionCarts().add(collectionCart);
            cart.get().getCollectionCarts().add(collectionCart);

            collectionCartRepository.save(collectionCart);
            collectionRepository.save(collection.get());
            cartRepository.save(cart.get());
        }
    }

    private Double getBooksPrice(ShoppingCart cart) {
        double finalPrice = 0;

        for(Book_Cart bookCart : cart.getBookCarts()){
            finalPrice += bookCart.getBook().getCost().getFinalCost();
        }
        return finalPrice;
    }

    private Double getCollectionsPrice(ShoppingCart cart) {
        double finalPrice = 0;

        for(Collection_Cart collectionCart : cart.getCollectionCarts()){
            for(Book book : collectionRepository.findBooksById(collectionCart.getCollection().getId()))
            finalPrice += book.getCost().getFinalCost();
        }
        return finalPrice;
    }
}
