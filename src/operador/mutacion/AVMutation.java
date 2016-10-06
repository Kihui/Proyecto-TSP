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
                tmp[i] = g.getGene(i);
                g.setGene(i,null);
                j++;
            }
        
        if(j == 1){
	    int i = r.nextInt(gsize - 1) + 1;
	    while(g.getGene(i) == null)
		i = r.nextInt(gsize - 1) + 1;
            tmp[i] = g.getGene(i);
            g.setGene(i,null);
	}

        j = 0;
        Integer[] tmp2 = new Integer[gsize];
        for(int i = 0; i < gsize; i++) {
            if(tmp[i] != null) {
                tmp2[j] = tmp[i];
                j++;
            }
        }
        
        j = 0;
        while(j < gsize - 1 && tmp2[j + 1] != null) {
            Integer in = tmp2[j];
            tmp2[j] = tmp2[j + 1];
            tmp2[j + 1] = in;
            j++;
        }

        j = 0;
        for(int i = 1; i < gsize; i++) {
            if(g.getGene(i) == null) {
                g.setGene(i, tmp2[j]);
                j++;
            }
        }
	return g;
    }
}
