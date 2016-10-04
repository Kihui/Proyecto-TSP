
import gaframework.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;


public class Ruleta<G,P> implements SelectionOp<G,P> {
    Random r;
    int numInd;

    public Ruleta(int seed, int numInd){
	this.r = new Random(seed);
	this.numInd = numInd;
    }

    public List<Individual<G,P>> select(Population<G,P> p){
	double[] rango = new double[p.size()];
	rango[0] = p.getIndividual(0).getObjectiveEvaluation();
	for(int i = 1; i < p.size(); i++)
	    rango[i] = p.getIndividual(i).getObjectiveEvaluation()+rango[i-1];
	LinkedList<Individual<G,P>> out = new LinkedList<>();
	for(int i =0; i < numInd; i++) {
	    double pestana = r.nextDouble()*rango[rango.length-1];
	    int j = 0;
	    /*System.out.println("hola");*/
	    while(rango[j] <= pestana)
		j++;
	    out.add(p.getIndividual(j));
	}
	return out;
    }
}
