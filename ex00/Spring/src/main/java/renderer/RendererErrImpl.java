package renderer;

import preprocessor.PreProcessor;

import java.io.Console;

public class RendererErrImpl implements Renderer{
    PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    public void print(String message) {
        System.err.println("\u001B[31m" + preProcessor.preproc(message) + "\u001B[0m");

    }
}
