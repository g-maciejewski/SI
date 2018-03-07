import pl.Grzegorz.GenericAlgorithm;
import pl.Grzegorz.IO.ContextFile;
import pl.Grzegorz.IO.ContextReader;
import pl.Grzegorz.IO.Exception.ContextFileException;
import pl.Grzegorz.chart.Chart;
import pl.Grzegorz.model.Context;
import pl.Grzegorz.model.Genotype;
import pl.Grzegorz.operations.couping.SimpleSwapCrossover;
import pl.Grzegorz.operations.mutation.RandomSwapMutator;

/**
 * Created by PanG on 07.03.2018.
 */
public class Application {

    public static void main(String[] args) throws ContextFileException {

        Context context = new ContextReader().loadContextFromFile(ContextFile.QNP_12);

        GenericAlgorithm geneticAlgorithm =
                new GenericAlgorithm.GeneticAlgolrithmBuilder()
                        .context(context)
//                        .selector(new RouletteSelector())
                        //.selector(new TournamentSelector(5))
//                        .selector(new RandomSelector())
                        .mutator(new RandomSwapMutator())
                        .crossover(new SimpleSwapCrossover())
                        .populationsNumber(100)
                        .generationsNumber(100)
                        .crossoverProbability(0.7)
                        .mutateProbability(0.05)
                        .mutateSingle()
                        .build();

        geneticAlgorithm.setChartInterpreter(new Chart());
        Genotype bestGenotype = geneticAlgorithm.run();
        System.out.println("Najlepszy genotyp: " + bestGenotype.toString());



    }
}

