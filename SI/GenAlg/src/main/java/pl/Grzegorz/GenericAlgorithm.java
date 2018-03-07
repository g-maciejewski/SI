package pl.Grzegorz;

import lombok.Getter;
import pl.Grzegorz.chart.IChart;
import pl.Grzegorz.model.Context;
import pl.Grzegorz.model.Genotype;
import pl.Grzegorz.model.Population;
import pl.Grzegorz.operations.mutation.IMutator;
import pl.Grzegorz.operations.couping.ICrossover;
import pl.Grzegorz.operations.selection.ISelector;
import pl.Grzegorz.operations.selection.RandomSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PanG on 07.03.2018.
 */
public class GenericAlgorithm {

    @Getter
    private List<Population> populations = new ArrayList<>();
    private Context context;
    private ISelector selector;
    private IMutator mutator;
    private ICrossover crossover;
    private int populationsNumber;
    private int generationNumber;
    private double crossoverProbability;
    private double mutateProbability;
    private IChart chart;
    private boolean mutateSingle;


    private GenericAlgorithm(Context context, ISelector selector, IMutator mutator, ICrossover crossover,
                             int populationsNumber, int generationNumber, double crossoverProbability, double mutateProbability,
                             boolean mutateSingle){
        this.context = context;
        this.selector = selector;
        this.mutator = mutator;
        this.crossover = crossover;
        this.populationsNumber = populationsNumber;
        this.generationNumber = generationNumber;
        this.crossoverProbability = crossoverProbability;
        this.mutateProbability = mutateProbability;
        this.mutateSingle = mutateSingle;

        Population.PopulationBuilder populationBuilder = new Population.PopulationBuilder()
                .genotypeSize(context.getContextSize())
                .populationSize(populationsNumber);

        this.populations.add(populationBuilder.buildInitPopulation(context));
    }

    public Genotype run(){
        Genotype bestGenotype = populations.get(0).getBestGenotype();

        for(int i=0; i<generationNumber; i++){
            Population population = evolve(populations.get(i));
            populations.add(population);
            if(population.getBestGenotype().getFittnes() <= bestGenotype.getFittnes()){
                bestGenotype = new Genotype(population.getBestGenotype());
            }
            population.print();
        }
        System.out.println("Best genotype: "+bestGenotype.toString());
        if(chart!=null)
            chart.draw(populations, selector, crossoverProbability, mutateProbability, generationNumber, populationsNumber);

        return bestGenotype;
    }

    private Population evolve(Population population) {
        ArrayList<Genotype> newGenotypes = new ArrayList<>();

//        newGenotypes.add(new Genotype(population.getBestGenotype()));

        for(int i=0; i<population.getGenotypes().size(); i++){

            Genotype genotype = new Genotype(selector.select(population));

            if(Math.random()<crossoverProbability){
                crossover.crossover(genotype, new Genotype(selector.select(population)));
            }

            if(mutateSingle) {
                for (int j = 0; j < genotype.getGenotype().length; j++) {
                    if (Math.random() < mutateProbability)
                        mutator.mutateSingleGeno(genotype, j);
                }
            } else {
                if(Math.random()<mutateProbability){
                    mutator.mutate(genotype);
                }
            }



            newGenotypes.add(genotype);
        }

        return new Population(newGenotypes);
    }

    public void setChartInterpreter(IChart chart) {
        this.chart=chart;
    }

    public static class GeneticAlgolrithmBuilder {

        Context context;
        ISelector selector;
        IMutator mutator;
        ICrossover crossover;
        int populationsNumber;
        int generationsNumber;
        double mutateProbability;
        double crossoverProbability;
        boolean mutateSingle = false;

        public GeneticAlgolrithmBuilder(){
            selector = new RandomSelector();
            context = new Context(0,new int[][]{}, new int[][]{});
        }

        public GeneticAlgolrithmBuilder context(Context context){
            this.context=context;
            return this;
        }

        public GeneticAlgolrithmBuilder selector(ISelector selector){
            this.selector = selector;
            return this;
        }

        public GeneticAlgolrithmBuilder mutator(IMutator mutator) {
            this.mutator=mutator;
            return this;
        }

        public GeneticAlgolrithmBuilder crossover(ICrossover crossover) {
            this.crossover=crossover;
            return this;
        }

        public GeneticAlgolrithmBuilder populationsNumber(int populationsNumber) {
            this.populationsNumber = populationsNumber;
            return this;
        }

        public GeneticAlgolrithmBuilder generationsNumber(int generationsNumber){
            this.generationsNumber = generationsNumber;
            return this;
        }

        public GeneticAlgolrithmBuilder mutateProbability(double mutateProbability){
            this.mutateProbability = mutateProbability;
            return this;
        }

        public GeneticAlgolrithmBuilder crossoverProbability(double crossoverProbability){
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        public GeneticAlgolrithmBuilder mutateSingle(boolean mutateSingle){
            this.mutateSingle = mutateSingle;
            return this;
        }

        public GenericAlgorithm build(){
            return new GenericAlgorithm(context, selector, mutator, crossover, populationsNumber, generationsNumber, crossoverProbability, mutateProbability, mutateSingle);
        }

        public GeneticAlgolrithmBuilder mutateSingle() {
            this.mutateSingle=true;
            return this;
        }
    }
}