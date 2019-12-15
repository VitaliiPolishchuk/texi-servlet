package the.best.dao;

import lombok.extern.slf4j.Slf4j;
import the.best.persistence.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractDao<T, E> implements EntityDao<T, E> {

    protected static final String COLUMN_ID = "id";

    @Override
    public T getById(E id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    public T getById(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        T result = null;

        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query);) {
            statementMapper.map(preparedStatement);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapper.map(resultSet);
                }
            }
        } catch (SQLException e) {
            log.error("Exception while getting all entities", e);
        }

        return result;
    }

    public List<T> getAll(String query, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = mapper.map(resultSet);

                result.add(entity);
            }

        } catch (SQLException e) {
            log.error("Exception while getting all entities", e);
        }

        return result;
    }

    public boolean createUpdate(String query, StatementMapper<T> statementMapper) {
        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query);) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            log.error("Could not create entity!!", e);
        }

        return false;
    }
}
