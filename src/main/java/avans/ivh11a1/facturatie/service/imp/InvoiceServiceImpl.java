package avans.ivh11a1.facturatie.service.imp;

import avans.ivh11a1.facturatie.service.InvoiceService;
import com.pdfcrowd.*;

import org.springframework.stereotype.Service;


import java.io.*;

/**
 * Created by Thom on 12-3-2017.
 */
@Service("InvoiceService")
public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public void generatePdf(int url){
        try
        {
            FileOutputStream fileStream;

            // create an API client instance
            Client client = new Client("thomie88", "472b498204ef61dbdce97887999bb90a");

            // convert a web page and save the PDF to a file
            String urlNumber = Integer.toString(url);
            String URL = "https://proftaak23ivh11a1b.herokuapp.com/invoice/print/" + urlNumber;
            fileStream = new FileOutputStream("Invoice.pdf");
            client.convertURI(URL, fileStream);
            fileStream.close();

            Integer ncredits = client.numTokens();
        }
        catch(PdfcrowdError why) {
            System.err.println(why.getMessage());
        }
        catch(IOException exc) {
            // handle the exception
        }
    }
}
