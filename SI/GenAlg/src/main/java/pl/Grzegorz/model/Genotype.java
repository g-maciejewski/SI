package pl.Grzegorz.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by PanG on 07.03.2018.
 */
public class Genotype {

    @Getter @Setter private int genotype[];
    @Getter @Setter private int fittnes;
    @Getter @Setter private Context context;

    public Genotype(int[] genotype, Context context){
        this.genotype = genotype;
        this.context = context;
        evaluate();

    }

    public Genotype(Genotype genotype){
        this(genotype.genotype.clone(), genotype.context);
    }

    public void evaluate() {
        int fit = 0;

        for (int i = 0; i < genotype.length; i++) {
            for (int j = 0; j < genotype.length; j++) {
                fit += context.getDistance()[i][j] * context.getFlow()[genotype[i] - 1][genotype[j] - 1];
            }
        }

        this.fittnes=fit;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Genotype: [");
        for(int i: genotype){
            stringBuilder.append(i+",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(" | ");
        stringBuilder.append(fittnes);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static class GenotypeBuilder{
        private int genotypeSize;

        public GenotypeBuilder(){
        }

        public GenotypeBuilder(int genotypeSize){
            this.genotypeSize=genotypeSize;
        }

        public GenotypeBuilder genotypeSize(int genotypeSize){
            this.genotypeSize=genotypeSize;
            return this;
        }

        public Genotype buildRandomGenotype (Context context){
            List<Integer> randomGenotype = IntStream.range(1, genotypeSize+1).boxed().collect(Collectors.toList());
            Collections.shuffle(randomGenotype);
            int[] genotype = randomGenotype.stream().mapToInt(i -> i).toArray();
            return new Genotype(genotype, context);
        }

        public Genotype buildOrderedGenotype (Context context) {
            List<Integer> randomGenotype = IntStream.range(1, genotypeSize+1).boxed().collect(Collectors.toList());
            int[] genotype = randomGenotype.stream().mapToInt(i -> i).toArray();
            return new Genotype(genotype, context);
        }
    }
}