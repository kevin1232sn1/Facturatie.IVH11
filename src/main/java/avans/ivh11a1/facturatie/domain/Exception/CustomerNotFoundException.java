package avans.ivh11a1.facturatie.domain.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Tinne on 3-4-2017.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No customer has been found with this ID.")
public class CustomerNotFoundException extends RuntimeException  {
    private String message = "";

    public CustomerNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
