package lv.java2.shopping_list.db.jdbc;

import lv.java2.shopping_list.db.ShoppingListRepository;
import lv.java2.shopping_list.domain.ShoppingList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class ShoppingListRepositoryImpl extends PostgreJDBC implements ShoppingListRepository {


    @Override
    public Optional<Long> addToDataBase(ShoppingList shoppingList) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO java2.shopping_lists (ID, TITLE, CREATED, MODIFIED) VALUES (default, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, shoppingList.getTitle());
            preparedStatement.setTimestamp(2, shoppingList.getDateCreated());
            preparedStatement.setTimestamp(3, shoppingList.getDateModified());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.addProductToDB");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
        return Optional.empty();
    }
}
