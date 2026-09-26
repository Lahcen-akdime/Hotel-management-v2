package Repository.impl.jdbc;

import Enums.PayementMethod;
import Mapper.PayementMapper;
import Model.Payement;
import Repository.PayementRepository;
import db.DatabaseConnection;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class JdbcPayementRepository implements PayementRepository {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private static final String saveQuery = "INSERT INTO payments (id, reservation_id, amount, payment_method, status, created_at) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String findByIdQuery = "SELECT * FROM payments WHERE id = ?";
    private static final String FindByReservationIdQuery = "SELECT * FROM payments WHERE reservation_id = ?";

    @Override
    public Payement save(Payement payement) {
        payement.setId(payement.getId());
        try (PreparedStatement statement = connection.prepareStatement(saveQuery)) {
            statement.setObject(1, payement.getId());
            statement.setObject(2, payement.getReservation_id());
            statement.setBigDecimal(3, payement.getAmount());
            statement.setString(4, payement.getPayementMethod().name());
            statement.setString(5, payement.getStatus().name());
            statement.setTimestamp(6, payement.created_at());
            statement.executeUpdate() ;
            return payement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @Override
    public Optional<Payement> findById(UUID id) {
        try (PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {
            statement.setObject(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(PayementMapper.setPayementProperties(result));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }

    @Override
    public Optional<Payement> findByReservationId(UUID reservationId) {
        try (PreparedStatement statement = connection.prepareStatement(FindByReservationIdQuery)) {
            statement.setObject(1, reservationId);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(PayementMapper.setPayementProperties(result));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null ;
    }
}