package pl.Grzegorz.operations.mutation;

import pl.Grzegorz.model.Genotype;

/**
 * Created by PanG on 07.03.2018.
 */
public interface IMutator {

    void mutate(Genotype genotype);
    void mutateSingleGeno(Genotype genotype, int geno);

}
