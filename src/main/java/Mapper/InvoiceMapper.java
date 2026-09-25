package Mapper;

import Enums.PayementMethod;
import Model.Invoice;
import Model.Payement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import Exception.DatabaseException ;

public class InvoiceMapper {

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

        UUID reservationId = result.getObject("reservation_id", UUID.class) ;
        String invoice_number = result.getString("invoice_number") ;
        Integer vat_rate = result.getInt("vat_rate") ;
        Integer vat_amount = result.getInt("vat_amount") ;
        Integer total_ttc = result.getInt("total_ttc") ;
        Integer subtotal_ht = result.getInt("subtotal_ht") ;
        Invoice invoice = new Invoice(reservationId,subtotal_ht,invoice_number,vat_rate,vat_amount,total_ttc);
        invoice.setId(result.getObject("id", UUID.class));
        return invoice ;

    }

}
