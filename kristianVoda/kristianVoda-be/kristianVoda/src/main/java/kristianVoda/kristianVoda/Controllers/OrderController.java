package kristianVoda.kristianVoda.Controllers;

import kristianVoda.kristianVoda.DTO.CartItemDTO;
import kristianVoda.kristianVoda.DTO.CreatedOrdersDTO;
import kristianVoda.kristianVoda.DTO.OrderDTO;
import kristianVoda.kristianVoda.Entity.CartItem;
import kristianVoda.kristianVoda.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class OrderController {

    @Autowired
   private OrderService service;


    @PreAuthorize("hasRole('USER')")
    @GetMapping("getAllCartItems/{id}")
    public List<CartItemDTO> getAllItems(
            @PathVariable final UUID id
    ) {
        return service.getAllCartItems(id);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("addCartItem/{id}")
    public CartItemDTO add(
            @PathVariable final UUID id,
            @RequestBody final CartItemDTO dto
            ) {
        return service.addItemToCart(id, dto);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping("createOrder/{id}")
    public List<CartItem> create(
            @PathVariable final UUID id,
            @RequestBody final OrderDTO dto
    ) {
        return service.createOrder(id, dto);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("deleteCartItem/{clientId}/{cartItemId}")
    public void deleteCartItem(
            @PathVariable final UUID clientId,
            @PathVariable final UUID cartItemId
    ) {
        service.deleteCartItem(clientId, cartItemId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("getAllOrderedItems/{clientId}")
    public List<CreatedOrdersDTO> getAllCartItems(
            @PathVariable final UUID clientId
    ) {
      return service.getAllOrderedItems(clientId);
    }
}
