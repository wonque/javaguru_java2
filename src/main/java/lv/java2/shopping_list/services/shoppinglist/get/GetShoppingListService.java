package lv.java2.shopping_list.services.shoppinglist.get;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.ShoppingListDBValidator;
import lv.java2.shopping_list.web.dto.ShoppingListDTO;
import lv.java2.shopping_list.web.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetShoppingListService {

    private final ShoppingListRepository repository;
    private final ShoppingListMapper mapper;

    @Autowired
    public GetShoppingListService(ShoppingListRepository repository,
                                  ShoppingListMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public List<ShoppingListDTO> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public ShoppingListDTO getSingleById(Long userId, Long listId) {
        return repository.findByUserIdAndListId(userId, listId).map(mapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                MessageFormat.format("ShoppingList with ID = {0} not found!", listId)));
    }

}
