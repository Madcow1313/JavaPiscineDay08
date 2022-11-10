package printer;

import renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {

    Renderer renderer;
    String prefix;

    public PrinterWithPrefixImpl(Renderer renderer){
        this.renderer = renderer;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void print(String message) {
        if (prefix.length() > 0){
            message = prefix + " " + message;
        }
        renderer.print(message);
    }
}
