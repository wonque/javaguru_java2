package lv.java2.shopping_list.services.item.addition;

import lv.java2.shopping_list.domain.Item;
import lv.java2.shopping_list.repository.ItemRepository;
import lv.java2.shopping_list.services.item.validation.ItemValidationService;
import lv.java2.shopping_list.web.dto.ItemDTO;
import lv.java2.shopping_list.web.dto.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemAdditionServiceImpl implements ItemAdditionService {

    private ItemMapper mapper;
    private ItemValidationService validator;
    private ItemRepository repository;

    @Autowired
    public ItemAdditionServiceImpl(ItemMapper mapper, ItemValidationService validator, ItemRepository repository) {
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public ItemDTO addItem(ItemDTO itemDto) {
        validator.validate(itemDto);

        Item newItem = mapper.toDomain(itemDto);

        repository.save(newItem);
        return mapper.toDTO(newItem);
    }
}
