package db;

import lv.java2.shopping_list.config.SpringAppConfig;
import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.domain.builders.ProductBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
public class ProductRepositoryTest {

    /**
     * Testing integration with database
     */
    @Autowired
    private ProductRepository productRepository;

    private ProductBuilder productBuilder;
    private Product product;

    @Before
    public void init() {
        this.productBuilder = new ProductBuilder();
        this.product = productBuilder.buildInstance("UniqueProductTitleGoesHere");
    }

    @Test
    public void shouldAddToBaseAndReturnProductWithId() {
        assertNull(product.getId());
        productRepository.addToDataBase(product);
        assertNotNull(product.getId());
    }

    @Test
    public void returnTrueIfProductWithTitleIsInDatabase() {
        Optional<Product> result = productRepository.findByTitle("UniqueProductTitleGoesHere");
        //testing for case sensitive title
        Optional<Product> caseSensResult = productRepository.findByTitle("UniqueProductTitleGoesHere".toUpperCase());
        assertTrue(result.isPresent());
        assertTrue(caseSensResult.isPresent());
    }

    @Test
    public void returnTrueIfProductWithIDIsInDatabase() {
        Optional<Product> result = productRepository.findById(2L);
        assertTrue(result.isPresent());
    }
}
