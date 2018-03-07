package pl.Grzegorz.IO;

/**
 * Created by PanG on 07.03.2018.
 */
public enum ContextFile {
    QNP_12("had12.dat"),
    QNP_14("had14.dat"),
    QNP_16("had16.dat"),
    QNP_18("had18.dat"),
    QNP_20("had20.dat");

    private final String file;

    ContextFile(final String file){
        this.file=file;
    }

    public String getFileName(){
        return this.file;
    }
}
