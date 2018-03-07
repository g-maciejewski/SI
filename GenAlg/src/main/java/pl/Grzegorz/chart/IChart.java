package pl.Grzegorz.chart;

import pl.Grzegorz.model.Population;
import pl.Grzegorz.operations.selection.ISelector;

import java.util.List;

/**
 * Created by PanG on 07.03.2018.
 */
public interface IChart {
    void draw(List<Population> populationList, ISelector selector, double pm, double px, int generations, int genotypesInPopulation);
}
