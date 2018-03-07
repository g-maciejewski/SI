package pl.Grzegorz.operations.selection;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import pl.Grzegorz.model.Genotype;
import pl.Grzegorz.model.Population;

import java.util.Random;

/**
 * Created by PanG on 07.03.2018.
 */
public class RouletteSelector implements ISelector {

    @Override
    public Genotype select(Population population) {

        RangeMap<Integer, Genotype> roulette = TreeRangeMap.create();
        int sum=0;
        for(Genotype g: population.getGenotypes()){
            roulette.put(Range.closed(sum, sum+g.getFittnes()-1), g);
            sum+=g.getFittnes();
        }
        Random random = new Random();

        return roulette.get(random.nextInt(sum));
    }

    @Override
    public String getSelectorName() {
        return "Roulette selector";
    }

}