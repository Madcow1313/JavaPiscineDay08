package preprocessor;

public class PreprocessorToUpperImpl implements PreProcessor {
    public String preproc(String str) {
        return str.toUpperCase();
    }
}
