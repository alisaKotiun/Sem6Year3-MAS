package mas.service;

public interface IOrderService {
    public abstract Double getFinalPrice(Long id);
    public abstract void deliver(Long id);
    public abstract void submit(Long id);
}
