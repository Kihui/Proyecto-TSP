import gaframework.*;
import org.moeaframework.problem.tsplib.*;

public class AdaptacionAV implements FitnessFunction<Integer>{
    DistanceTable dt;
    public AdaptacionAV(DistanceTable dt){
        this.dt = dt;
    }

    public double evaluate(Phenotype<Integer> p){
        double fitness = 0;
        for(int i = 0; i < p.size();i+=2)
            fitness += dt.getDistanceBetween(p.getAllele(i),p.getAllele(i+1));
        fitness += dt.getDistanceBetween(p.getAllele(0),p.getAllele(p.size()-1));
        return fitness;
    }
}
