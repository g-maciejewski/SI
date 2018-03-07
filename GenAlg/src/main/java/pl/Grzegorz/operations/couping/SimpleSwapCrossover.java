package pl.Grzegorz.operations.couping;

import pl.Grzegorz.model.Genotype;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by PanG on 07.03.2018.
 */
public class SimpleSwapCrossover implements ICrossover {

    @Override
    public void crossover(Genotype genotype, Genotype genotype2) {

        Random random = new Random();
//        int cutPoint = random.nextInt(genotype.getGenotype().length-2)+1;
        int cutPoint = genotype.getGenotype().length/2;
        int[] crossover1a = Arrays.copyOfRange(genotype.getGenotype(), 0, cutPoint);
        int[] crossover1b = Arrays.copyOfRange(genotype2.getGenotype(), cutPoint, genotype2.getGenotype().length);
        int[] crossGenotype1 = concatenate(crossover1a, crossover1b);

        int[] crossover2a = Arrays.copyOfRange(genotype.getGenotype(), cutPoint, genotype.getGenotype().length);
        int[] crossover2b = Arrays.copyOfRange(genotype2.getGenotype(), 0, cutPoint);
        int[] crossGenotype2 = concatenate(crossover2b,crossover2a);

        genotype.setGenotype(crossGenotype1);
        genotype2.setGenotype(crossGenotype2);
        fixGenotypes(genotype, genotype2);
        if(random.nextBoolean()){
            genotype = new Genotype(genotype2);
        }
        genotype.evaluate();
        genotype2.evaluate();
    }


    private int[] concatenate(int[] a, int[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        int[] c = (int[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    private void fixGenotypes(Genotype genotype1, Genotype genotype2) {

        int[] geno1 = genotype1.getGenotype();
        int[] geno2 = genotype2.getGenotype();

        for(int i=0; i<geno1.length; i++){
            if(!arrayContains(geno2,geno1[i])){
                for(int j=0; j<geno2.length; j++){
                    if(!arrayContains(geno2,geno1[i]) && !arrayContains(geno1, geno2[j])){
                        geno1[i]=geno2[j];
                    }
                }
            }
        }
    }

    private boolean arrayContains(int[] array, int number){
        for(int i: array){
            if (i==number)
                return true;
        }
        return false;
    }
}