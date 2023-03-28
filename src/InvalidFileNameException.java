import java.io.FileNotFoundException;

public class InvalidFileNameException extends FileNotFoundException {

    public InvalidFileNameException(String msg) {
        super(msg);
    }
}
