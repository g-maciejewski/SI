package pl.Grzegorz.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by PanG on 07.03.2018.
 */
public class Population {

    @Getter @Setter protected List<Genotype> genotypes;
    @Getter @Setter protected int bestFittnes;
    @Getter @Setter protected int avgFittnes;
    @Getter @Setter protected int worstFittnes;
    @Getter protected  Genotype bestGenotype;


    public Population(List<Genotype> genotypes){
        this.genotypes=genotypes;
        calculateFittneses();
    }

    public void calculateFittneses(){
        bestFittnes = genotypes.get(0).getFittnes();
        bestGenotype = genotypes.get(0);
        worstFittnes = genotypes.get(0).getFittnes();

        int sum=0;

        for(Genotype g: genotypes){
            sum+=g.getFittnes();
            if(g.getFittnes()<bestFittnes){
                bestFittnes = g.getFittnes();
                bestGenotype = new Genotype(g);
                if(bestFittnes!=bestGenotype.getFittnes()){
                    Genotype gs = new Genotype(bestGenotype);
                    bestGenotype.getFittnes();
                }
            }
            if(g.getFittnes()>worstFittnes){
                worstFittnes = g.getFittnes();
            }
        }
        avgFittnes=sum/genotypes.size();
    }

    public String toCsv(){
        return bestFittnes+","+avgFittnes+","+worstFittnes;

    }

    public void print() {
        for(Genotype g: genotypes){
            System.out.println(g.toString());
        }
    }



    public static class PopulationBuilder{

        private int populationSize;
        private int genotypeSize;

        public PopulationBuilder(){
            this.populationSize = 0;
            this.genotypeSize = 0;
        }

        public PopulationBuilder populationSize(int populationSize){
            this.populationSize=populationSize;
            return this;
        }

        public PopulationBuilder genotypeSize(int genotypeSize){
            this.genotypeSize = genotypeSize;
            return this;
        }

        public Population buildInitPopulation(Context context){
            List<Genotype> genotypes = new ArrayList<Genotype>();
            Genotype.GenotypeBuilder genotypeBuilder = new Genotype.GenotypeBuilder().genotypeSize(genotypeSize);
            for(int i = 0; i<populationSize; i++){
                genotypes.add(genotypeBuilder.buildRandomGenotype(context));
            }

            return new Population(genotypes);
        }
    }
}