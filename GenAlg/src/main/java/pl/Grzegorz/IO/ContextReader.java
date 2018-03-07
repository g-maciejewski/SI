package pl.Grzegorz.IO;

import java.io.*;
import java.util.Arrays;

import pl.Grzegorz.IO.Exception.ContextFileException;
import pl.Grzegorz.model.Context;

/**
 * Created by PanG on 07.03.2018.
 */
public class ContextReader {

    public ContextReader(){

    }

    public Context loadContextFromFile(String filePath) throws ContextFileException {
        File file = new File(filePath);
        return loadContextFromFile(file);
    }

    public Context loadContextFromFile(ContextFile ctxFile) throws ContextFileException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(ctxFile.getFileName());
        File file = new File(classLoader.getResource(ctxFile.getFileName()).getPath());

        return loadContextFromFile(file.getAbsolutePath());
    }

    private Context loadContextFromFile(File file) throws ContextFileException {
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            line = line.replaceAll("\\s++","");
            int contextSize = Integer.parseInt(line);
            int distance[][] = new int[contextSize][contextSize];
            int flow[][] = new int[contextSize][contextSize];
            line=br.readLine();
            while(line.matches("\\s++") || line.matches("")){
                line=br.readLine();
            }
            distance[0]=lineToIntArr(line);
            for(int i=1; i<contextSize; i++){
                line=br.readLine();
                distance[i]=lineToIntArr(line);
            }
            line=br.readLine();
            while(line.matches("\\s++") || line.matches("")){
                line=br.readLine();
            }
            flow[0]=lineToIntArr(line);
            for(int i=1; i<contextSize; i++){
                line=br.readLine();
                flow[i]=lineToIntArr(line);
            }
            return new Context(contextSize, distance, flow);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ContextFileException();
    }

    private int[] lineToIntArr(String line){
        String tokens[] = line.split("\\s++");
        tokens =  Arrays.stream(tokens).map(string -> string.replaceAll("//s++","")).filter(string -> !string.equals("")).toArray(String[]::new);
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
    }
}
