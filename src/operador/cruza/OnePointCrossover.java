import gaframework.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;

public class OnePointCrossover<G> implements CrossoverOp<G>{
    private double prob;
    Random r;
    public OnePointCrossover(int seed, double prob){
	this.prob=prob;
	r = new Random(seed);
    }
    public List<Genotype<G>> crossover(List<Genotype<G>> parents){
	Genotype<G> p1 = parents.get(0);
	Genotype<G> p2 = parents.get(0);
	Genotype<G> out1 = new Genotype<>(p1.size());
	Genotype<G> out2 = new Genotype<>(p2.size());
	if (r.nextDouble() < prob) {
	    int point = r.nextInt(p1.size());
	    for (int i=0; i < point; i++) {
		out1.setGene(i, p1.getGene(i));
		out2.setGene(i, p2.getGene(i));
	    }
	    for (int i= point; i< p1.size(); i++) {
		out1.setGene(i, p2.getGene(i));
		out2.setGene(i, p2.getGene(i));						
	    }
	}
	else{
	    //Copiar padres a hijos
	    for(int j = 0; j < p1.size(); j++){
		out1.setGene(j,p1.getGene(j));
		out2.setGene(j,p2.getGene(j));
		
	    }
	    
	}
	LinkedList<Genotype<G>> l = new LinkedList<>();
	l.add(out1);
	l.add(out2);
	return l;
    }
}
