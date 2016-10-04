import gaframework.*;
public class ObjetivoAV implements ObjectiveFunction<Integer,Integer>{
    public void evaluate(Population<Integer,Integer> p){
        double maxfit,minfit = 0;
        maxfit = p.getBestIndividual().getFitness();
        minfit = p.getWorstIndividual().getFitness();
        for(int i = 0; i < p.size(); i++){
            Individual<Integer, Integer> ind;
            double eval = 0;
            ind = p.getIndividual(i);
            eval = (maxfit+minfit)-ind.getFitness();
            ind.setObjectiveEvaluation(eval);
        }
    }
}
