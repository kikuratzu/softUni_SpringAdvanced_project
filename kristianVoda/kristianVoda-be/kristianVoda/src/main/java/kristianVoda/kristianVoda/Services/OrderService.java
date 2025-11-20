package kristianVoda.kristianVoda.Services;

import kristianVoda.kristianVoda.DTO.CartItemDTO;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    LoginService loginService;
    @Autowired
    CartItemRepository repo;
    @Autowired
    OrderRepository orderRepo;
    @Autowired
    OrderedItemRepository orderedItemRepository;

      private CartItem findCartItemById(final Long id){
        return repo.findByIdOrNull(id);
    }


    public List<CartItemDTO> getAllCartItems(final Long id){
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

    public CartItemDTO addItemToCart(final Long id, final CartItemDTO dto){
        CartItem order = new CartItem();
        order.setQuantity(dto.getQuantity());
        order.setWaterType(dto.getType());
        order.setClient(loginService.findClientById(id));

        Client client = loginService.findClientById(id);
        client.getCartItems().add(order);
        repo.save(order);
        return dto;

    }

    public List<CartItem> createOrder(final Long id, final OrderDTO dto){
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

    public void deleteCartItem(final Long clientId,final Long cartItemId){
       repo.delete(findCartItemById(cartItemId));

    }


}
