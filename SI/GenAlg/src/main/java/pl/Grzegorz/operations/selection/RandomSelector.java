package pl.Grzegorz.operations.selection;

import pl.Grzegorz.model.Genotype;
import pl.Grzegorz.model.Population;

import java.util.Random;

/**
 * Created by PanG on 07.03.2018.
 */
public class RandomSelector implements ISelector {

    @Override
    public Genotype select(Population population) {
        Random random = new Random();
        Genotype genotype = population.getGenotypes().get(random.nextInt(population.getGenotypes().size()));
        return genotype;
    }

    @Override
    public String getSelectorName() {
        return "Random selector";
    }
}

