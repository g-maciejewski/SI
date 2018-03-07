package pl.Grzegorz.operations.selection;

import pl.Grzegorz.model.Genotype;
import pl.Grzegorz.model.Population;

/**
 * Created by PanG on 07.03.2018.
 */
public interface ISelector {

    Genotype select(Population population);
    String getSelectorName();
}
