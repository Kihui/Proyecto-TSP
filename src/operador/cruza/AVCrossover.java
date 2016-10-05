import gaframework.*;
import java.util.Random;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class AVCrossover<G> implements CrossoverOp<G>{

    private double prob;
    private Random r;

    public AVCrossover(int seed, double prob){
	this.prob=prob;
	r = new Random(seed);
    }

    public List<Genotype<G>> crossover(List<Genotype<G>> parents){
	Genotype<G> p1 = parents.get(0);
	Genotype<G> p2 = parents.get(1);	
        LinkedList<Genotype<G>> l = new LinkedList<>();
        double d = r.nextDouble();
        
        if(d <= prob) {
            Genotype<G> out1 = new Genotype<>(p1.size());
            Genotype<G> out2 = new Genotype<>(p2.size());
            HashMap<G,G> faltan1 = new HashMap<>();
            HashMap<G,G> faltan2 = new HashMap<>();
        
            for(int i = 0; i < p1.size(); i++) {
                int x = (Math.random()<0.5) ? 0 : 1;
                if(x == 1) {
                    out1.setGene(i, p1.getGene(i));
                    out2.setGene(i, p2.getGene(i));
                } else {
                    faltan1.put(p1.getGene(i),p1.getGene(i));
                    faltan2.put(p2.getGene(i),p2.getGene(i));
                }
            }

            int io1 = 0;
            int io2 = 0;
            for(int i = 0; i < p1.size(); i++) {
                if(faltan1.get(p2.getGene(i)) != null) {
                    while(out1.getGene(io1) != null)
                        io1++;
                    out1.setGene(io1, p2.getGene(i));
                }
                if(faltan2.get(p1.getGene(i)) != null) {
                    while(out2.getGene(io2) != null)
                        io2++;
                    out2.setGene(io2, p1.getGene(i));
                }
            }            
            l.add(out1);
            l.add(out2);
        } else {
            l.add(p1);
            l.add(p2);
        }
	return l;
    }
}
