import gaframework.*;
import java.util.Random;
public class UniformMutation implements MutationOp<Boolean>{
    private double prob;
    private Random r;

    public UniformMutation(double prob, int seed){
	this.prob=prob;
	this.r = new Random(seed);
    }
    public Genotype<Boolean> mutate(Genotype<Boolean> g){
	int gsize = g.size();
	Genotype<Boolean> out = new Genotype<>(gsize);
	for (int i=0; i<gsize; i++) {
	    if (r.nextDouble() < prob){
		out.setGene(i, !g.getGene(i).booleanValue());
	    }
	    else{
		out.setGene(i, g.getGene(i));
	    }
	}
	return out;
    }
}
