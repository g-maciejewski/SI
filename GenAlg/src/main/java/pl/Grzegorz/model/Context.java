package pl.Grzegorz.model;

import lombok.Getter;
/**
 * Created by PanG on 07.03.2018.
 */
public class Context {

    @Getter private final int contextSize;
    @Getter private final int distance[][];
    @Getter private final int flow[][];

    public Context(final int ctxSize, final int[][] distance, final int[][] flow){
        this.contextSize=ctxSize;
        this.distance=distance;
        this.flow=flow;
    }
}
