package Service;

import Model.Invoice;
import Model.Payement;
import Repository.impl.jdbc.JdbcInvoiceRepository;

public class InvoiceService {

    private static JdbcInvoiceRepository jdbcInvoiceRepository = new JdbcInvoiceRepository() ;

    public void save(Invoice invoice, Payement payement){
        jdbcInvoiceRepository.save(invoice,payement) ;
    }

}