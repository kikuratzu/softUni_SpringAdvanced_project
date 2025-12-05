package kristianVoda.kristianVoda.Services;

import kristianVoda.kristianVoda.DTO.CartItemDTO;
import kristianVoda.kristianVoda.DTO.CreatedOrdersDTO;
import kristianVoda.kristianVoda.DTO.OrderDTO;
import kristianVoda.kristianVoda.Entity.Client;
import kristianVoda.kristianVoda.Entity.CartItem;
import kristianVoda.kristianVoda.Entity.Order;
import kristianVoda.kristianVoda.repo.CartItemRepository;
import kristianVoda.kristianVoda.Entity.OrderItem;
import kristianVoda.kristianVoda.repo.OrderRepository;
import kristianVoda.kristianVoda.repo.OrderedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CartItemRepository repo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private OrderedItemRepository orderedItemRepository;

    @Transactional(readOnly = true)
      private CartItem findCartItemById(final UUID id){
        return repo.findByIdOrNull(id);
    }

    @Transactional(readOnly = true)
    public List<CartItemDTO> getAllCartItems(final UUID id){
        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
        loginService.findClientById(id).getCartItems().forEach(cartItem -> {
            CartItemDTO cartItemDTO = new CartItemDTO();
            cartItemDTO.setId(cartItem.getId());
            cartItemDTO.setQuantity(cartItem.getQuantity());
            cartItemDTO.setType(cartItem.getWaterType());
            cartItemDTOList.add(cartItemDTO);
        });
        return cartItemDTOList;
    }

    @Transactional
    public CartItemDTO addItemToCart(final UUID id, final CartItemDTO dto){
        CartItem order = new CartItem();
        order.setQuantity(dto.getQuantity());
        order.setWaterType(dto.getType());
        order.setClient(loginService.findClientById(id));

        Client client = loginService.findClientById(id);
        client.getCartItems().add(order);
        repo.save(order);
        return dto;

    }

    @Transactional
    public List<CartItem> createOrder(final UUID id, final OrderDTO dto){
        Client client = loginService.findClientById(id);
        Order order = new Order();
        order.setAddress(dto.getAddress());
        order.setPhoneNumber(dto.getPhoneNumber());
        order.setClient(client);
        orderRepo.save(order);
        client.getCartItems().forEach(cartItem -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setQuantity(cartItem.getQuantity());
            item.setWaterType(cartItem.getWaterType());
            order.getOrderItems().add(cartItem);
            orderedItemRepository.save(item);
        });
           client.getCartItems().clear();
           repo.deleteAll();
        client.getOrders().add(order);

        return order.getOrderItems();
    }

    @Transactional
    public void deleteCartItem(final UUID clientId,final UUID cartItemId){
       repo.delete(findCartItemById(cartItemId));
    }

    @Transactional(readOnly = true)
    public List<CreatedOrdersDTO> getAllOrderedItems(UUID id){
        List<CreatedOrdersDTO> createdOrdersDTOS = new ArrayList<>();
        List<Order> orders = orderRepo.findByClient_Id(id);

        if (!orders.isEmpty()) {
            orders.stream()
                    .flatMap(order ->
                            orderedItemRepository.findAllOrderItemsByOrderId(order.getId()).stream()
                                    .flatMap(orderItems -> orderItems.stream()
                                            .map(orderItem ->
                                                    CreatedOrdersDTO.builder()
                                                            .orderId(order.getId())
                                                            .quantity(orderItem.getQuantity())
                                                            .types(orderItem.getWaterType())
                                                            .build()
                                            )
                                    )
                    )
                    .forEach(createdOrdersDTOS::add);
        }
        return createdOrdersDTOS;
    }

}
