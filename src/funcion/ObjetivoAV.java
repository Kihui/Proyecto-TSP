import gaframework.*;
public class ObjetivoAV implements ObjectiveFunction<Integer,Integer>{
    public void evaluate(Population<Integer,Integer> p){

        double maxfit = 0, minfit = 0;
        for(int i = 0; i < p.size(); i++) {
            if(minfit > p.getIndividual(i).getFitness())
                minfit = p.getIndividual(i).getFitness();
            if(maxfit < p.getIndividual(i).getFitness())
                maxfit = p.getIndividual(i).getFitness();                
        }
            
        for(int i = 0; i < p.size(); i++){
            Individual<Integer, Integer> ind;
            double eval = 0;
            ind = p.getIndividual(i);
            eval = (maxfit + minfit) - ind.getFitness();
            ind.setObjectiveEvaluation(eval);
        }
    }
}
