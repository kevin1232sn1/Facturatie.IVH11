package avans.ivh11a1.facturatie.domain.Exception;

import lombok.Getter;

/**
 * Created by kevin on 21-3-2017.
 */
@Getter
public class SecurityException extends Exception {
    private String type;

    public SecurityException(String msg, String type) {
        super(msg);
        this.type = type;

    }
}
