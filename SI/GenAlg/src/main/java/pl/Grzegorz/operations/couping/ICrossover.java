package pl.Grzegorz.operations.couping;

import pl.Grzegorz.model.Genotype;

/**
 * Created by PanG on 07.03.2018.
 */
public interface ICrossover {

    void crossover(Genotype genotype, Genotype genotype2);
}
