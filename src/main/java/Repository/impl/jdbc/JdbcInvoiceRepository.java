package Repository.impl.jdbc;

import Mapper.InvoiceMapper;
import Model.Invoice;
import Model.Payement;
import Model.Reservation;
import Repository.InvoiceRepository;
import Util.MoneyUtils;
import db.DatabaseConnection;
import Exception.DatabaseException ;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class JdbcInvoiceRepository implements InvoiceRepository {

    private final Connection connection = DatabaseConnection.getInstance().getConnection();
    private static final String saveQuery = "INSERT INTO invoices (id, payment_id, invoice_number, subtotal_ht, vat_amount, total_ttc, issued_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String findByIdQuery = "SELECT * FROM invoices WHERE id = ?";
    private static final String findByReservationIdQuery = "SELECT * FROM invoices WHERE payment_id = ?";

    @Override
    public Invoice save(Invoice invoice , Payement payement) {
        try (PreparedStatement statement = connection.prepareStatement(saveQuery)) {
            statement.setObject(1, invoice.getId());
            statement.setObject(2, payement.getId());
            statement.setString(3, invoice.getInvoice_number());
            statement.setBigDecimal(4, invoice.getSubtotal_ht());
            statement.setBigDecimal(5, invoice.getTva_amount());
            statement.setBigDecimal(6, invoice.getTotal_ttc());
            statement.setTimestamp(7, invoice.getIssued_at());
            statement.executeUpdate();
            return invoice;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DatabaseException("Le payement ne passe pas") ;
        }
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        try (PreparedStatement statement = connection.prepareStatement(findByIdQuery)) {
            statement.setObject(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(InvoiceMapper.getInvoice(result));
                }
                return null ;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null ;
        }
    }

    @Override
    public Optional<Invoice> findByPayement(UUID paymentId) {
        try (PreparedStatement statement = connection.prepareStatement(findByReservationIdQuery)) {
            statement.setObject(1, paymentId);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return Optional.of(InvoiceMapper.getInvoice(result));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DatabaseException("on peut pas trouvée ce reservation id");
        }
    }


}