package renderer;

import preprocessor.PreProcessor;

public class RendererStandardImpl implements Renderer {
    PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }

    public void print(String message) {
        System.out.println(preProcessor.preproc(message));
    }
}
