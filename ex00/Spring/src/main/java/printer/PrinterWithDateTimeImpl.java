package printer;

import org.springframework.format.annotation.DateTimeFormat;
import renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {
    Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer){
        this.renderer = renderer;
    }

    public void print(String message){
        LocalDateTime currentDateTime = LocalDateTime.now();
        message += "(printed at " + currentDateTime + ")";
        renderer.print(message);
    }
}
