package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import preprocessor.PreProcessor;
import preprocessor.PreProcessorToLowerImpl;
import preprocessor.PreprocessorToUpperImpl;
import printer.Printer;
import printer.PrinterWithDateTimeImpl;
import printer.PrinterWithPrefixImpl;
import renderer.Renderer;
import renderer.RendererErrImpl;
import renderer.RendererStandardImpl;

public class App {

    public static void main(String[] args){
        PreProcessor preProcessor = new PreprocessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix");
        printer.print("Hello!");

        PreProcessor preProcessor1 = new PreProcessorToLowerImpl();
        Renderer renderer1 = new RendererStandardImpl(preProcessor1);
        PrinterWithDateTimeImpl printer1 = new PrinterWithDateTimeImpl(renderer1);
        printer1.print("HIIIII!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        PrinterWithPrefixImpl printer2 = context.getBean(PrinterWithPrefixImpl.class);
        printer2.print("Shakalaka");

        Printer printer3 = context.getBean("printerWithPrefix", Printer.class);
        printer3.print("Shit, here we go again");
        printer2.setPrefix("Boom");
        printer3.print("Not again");
        printer2.setPrefix("Hallelujah");
        printer2.print("Yeeeees");

        PrinterWithPrefixImpl printer4 = context.getBean(PrinterWithPrefixImpl.class);
        printer4.print("Why");
    }
}
