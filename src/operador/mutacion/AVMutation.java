import gaframework.*;
import java.util.Random;

public class AVMutation implements MutationOp<Integer> {

    private double prob;
    private Random r;

    public AVMutation(double prob, int seed) {
	this.prob = prob;
	this.r = new Random(seed);
    }

    public Genotype<Integer> mutate(Genotype<Integer> g) {
	int gsize = g.size();
        Integer[] tmp = new Integer[gsize];
        int j = 0;
        
	for (int i = 1; i < gsize; i++)
	    if (r.nextDouble() < prob) {
                tmp[j] = g.getGene(i);
                g.setGene(i,null);
                j++;
            }
        
        if(j == 1){
	    int i = r.nextInt(gsize - 1) + 1;
	    while(g.getGene(i) == null)
		i = r.nextInt(gsize - 1) + 1;
            tmp[j] = g.getGene(i);
            g.setGene(i,null);
	}

        j = 0;
        while(j < gsize - 1 && tmp[j + 1] != null) {
            Integer in = tmp[j];
            tmp[j] = tmp[j + 1];
            tmp[j + 1] = in;
            j++;
        }

        j = 0;
        for(int i = 1; i < gsize; i++) {
            if(g.getGene(i) == null) {
                g.setGene(i, tmp[j]);
                j++;
            }
        }
	
	return g;
    }
}
