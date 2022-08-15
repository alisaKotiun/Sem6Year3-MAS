package mas.service;

import mas.model.ShoppingCart;

public interface ICartService {
    public abstract Double getPrice(Long id);
    public abstract void save(ShoppingCart cart);
    public abstract void deleteAll();
    public abstract void saveCollection(Long collectionId, Long cartId, Integer quantity);
}
