package lv.java2.shopping_list.services.shoppinglist;

import lv.java2.shopping_list.domain.ShoppingList;
import lv.java2.shopping_list.dto.ShoppingListDTO;
import lv.java2.shopping_list.dto.mappers.ShoppingListMapper;
import lv.java2.shopping_list.repository.ShoppingListRepository;
import lv.java2.shopping_list.services.shoppinglist.validation.ShoppingListValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListRepository repository;
    private final ShoppingListMapper mapper;
    private final ShoppingListValidationService validator;

    @Autowired
    public ShoppingListService(ShoppingListRepository repository, ShoppingListMapper mapper,
                               ShoppingListValidationService validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public ShoppingListDTO create(ShoppingListDTO listDTO) {
        validator.validate(listDTO);

        ShoppingList newEntry = mapper.toDomain(listDTO);
        newEntry.markAsActive();
        repository.save(newEntry);

        return mapper.toDTO(newEntry);
    }

    public List<ShoppingListDTO> getAllByUserId(Long userId) {
        return repository.findAllByUserId(userId)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public ShoppingListDTO getSingleList(Long userId, Long listId) {
        return repository.findByUserIdAndListId(userId, listId).map(mapper::toDTO)
                .orElseThrow(() ->
                        new EntityNotFoundException("Shopping list not found, id = " + listId));
    }

    public ShoppingListDTO update(ShoppingListDTO listDTO) {
        validator.validate(listDTO);

        ShoppingList toUpdate = mapper.toDomain(listDTO);

        repository.save(toUpdate);

        return mapper.toDTO(toUpdate);
    }

    @Transactional
    public void removeById(ShoppingListDTO dto) {
        repository.findByUserIdAndListId(dto.getUserId(), dto.getId())
                .ifPresent(repository::delete);
    }

    //TODO Implement own delete method

}
