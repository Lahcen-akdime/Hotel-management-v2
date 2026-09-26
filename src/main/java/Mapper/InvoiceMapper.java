package Mapper;

import Enums.PayementMethod;
import Model.Invoice;
import Model.Payement;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Exception.DatabaseException ;
import Repository.impl.jdbc.JdbcPayementRepository;

public class InvoiceMapper {

    private static JdbcPayementRepository jdbcPayementRepository = new JdbcPayementRepository() ;

    public static List<Invoice> getInvoices(ResultSet result)throws SQLException {
        List<Invoice> invoices = new ArrayList<>() ;
        while(result.next()){
            Invoice invoice = setInvoiceProperties(result) ;
            invoices.add(invoice) ;
        }
        return invoices ;
    }

    public static Invoice getInvoice(ResultSet result)throws SQLException {
        if(result.next()){
            Invoice invoice = setInvoiceProperties(result) ;
            return invoice ;
        }
        else {
            throw new DatabaseException("This invoice is not exist");
        }
    }

    public static Invoice setInvoiceProperties(ResultSet result)throws SQLException{

        String invoice_number = result.getString("invoice_number") ;
        Integer vat_amount = result.getInt("vat_amount") ;
        Integer total_ttc = result.getInt("total_ttc") ;
        BigDecimal subtotal_ht = result.getObject("subtotal_ht", BigDecimal.class) ;
        Payement payement = jdbcPayementRepository.findById(result.getObject("payement_id", UUID.class)).get() ;
        Invoice invoice = new Invoice(payement);
        invoice.setId(result.getObject("id", UUID.class));
        return invoice ;

    }

}
