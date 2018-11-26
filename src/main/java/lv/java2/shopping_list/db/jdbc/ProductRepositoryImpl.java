package lv.java2.shopping_list.db.jdbc;

import lv.java2.shopping_list.domain.Product;
import lv.java2.shopping_list.db.ProductRepository;
import lv.java2.shopping_list.domain.ProductFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class ProductRepositoryImpl extends PostgreJDBC implements ProductRepository {

    //case sensitive search sql query - select * from java2.products where title ILIKE <someTitle>
    @Autowired
    private ProductFactory productFactory;

    @Override
    public Product addToDataBase(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO java2.products (ID, TITLE, DESCRIPTION, CATEGORY, PRICE) VALUES (default, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setBigDecimal(4, product.getPrice());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long productId = resultSet.getLong(1);
                product.setId(productId);
                return product;
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseImpl.addProductToDB");
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return product;
    }

    @Override
    public Optional<Product> findByTitle(String title) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM java2.products WHERE TITLE ILIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            //executequery() returns ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = productFactory.createNewProductWithTitle(resultSet.getString("title"));
                product.setId(resultSet.getLong("id"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setCategory(resultSet.getString("category"));
            }
            return Optional.ofNullable(product);
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.findByTitle");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean remove(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM java2.products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getId());
            //executeUpdate() returns number of rows affected by sql query
            int numberOfRowsDeleted = preparedStatement.executeUpdate();
            return numberOfRowsDeleted == 1;
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.remove");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);

        }
    }

    @Override
    public List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM java2.products";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = productFactory.createNewProductWithTitle(resultSet.getString("title"));
                product.setId(resultSet.getLong("id"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setCategory(resultSet.getString("category"));
                products.add(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.getProductList");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
        return products;
    }

    //    @Override
    public void updateDescription(Product product, String description) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "UPDATE java2.products SET description = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, description);
            preparedStatement.setLong(2, product.getId());

            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductRepositoryImpl.updateProductDescription");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }

    //    @Override
    public void updatePrice(Product product, BigDecimal price) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "UPDATE java2.products SET price = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setLong(2, product.getId());

            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductRepositoryImpl.updatePrice");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }
}

