package hu.masterfield.bankproject.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import hu.masterfield.bankproject.configuration.XMLApplicationConfiguration;
import hu.masterfield.bankproject.datatypes.Confirmation;
import hu.masterfield.bankproject.interfaces.TransferService;

//@SpringBootApplication
public class TranferTestSpringXML {

    
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(XMLApplicationConfiguration.class);

        TransferService service = (TransferService) context.getBean("transferService");

        Confirmation conf = service.transfer("Pénzes Péter", "Szegény Szilárd", 100);

        System.out.println(conf);
    }

}