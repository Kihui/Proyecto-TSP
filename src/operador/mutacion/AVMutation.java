import gaframework.*;
import java.util.Random;
import java.util.BitSet;

public class AVMutation<G> implements MutationOp<G> {

    private double prob;
    private Random r;

    public AVMutation(double prob, int seed) {
	this.prob = prob;
	this.r = new Random(seed);
    }

    public Genotype<G> mutate(Genotype<G> g) {
	int gsize = g.size();
	Genotype<G> out = new Genotype<>(gsize);
	Bitset mascara = new BitSet(gsize);
	for (int i = 1; i < gsize; i++)
	    if (r.nextDouble() < prob)
		mascara.set(i);	    
	if(mascara.cardinality() == 1){
	    int i = r.nextInt(mascara.size() - 1) + 1;
	    while(mascara.get(i) != True)
		i = r.nextInt(mascara.size() - 1) + 1;
	}
	
	return out;
    }
}
