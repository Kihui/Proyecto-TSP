import gaframework.*;

public class ObjetivoAV implements ObjectiveFunction<Integer,Integer>{

    public void evaluate(Population<Integer,Integer> p){

        double maxfit = p.getWorstIndividual().getFitness();
        double minfit = p.getBestIndividual().getFitness();
        
        for(int i = 0; i < p.size(); i++){
            Individual<Integer, Integer> ind;
            double eval = 0;
            ind = p.getIndividual(i);
            eval = (maxfit + minfit) - ind.getFitness();
            ind.setObjectiveEvaluation(eval);
        }
    }
}
