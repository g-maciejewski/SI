package pl.Grzegorz.IO.Exception;

/**
 * Created by PanG on 07.03.2018.
 */
public class ContextFileException extends Exception {

    public ContextFileException(){
        super("Incorrect format of context file or file doesn't exists!");
    }
}
