import gaframework.*;
import java.util.BitSet;
import java.util.Random;

public class NoBin implements Codification<Integer, Integer>{
    private int numInts; //el número de ints que componen un recorrido en TSP

    public NoBin(int i){
        numInts = i;
    }

    public Genotype<Integer> encode (Phenotype<Integer> p){
	Genotype<Integer> out = new Genotype<>(numInts);
	for(int i = 0; i < p.size(); i++)
	    out.setGene(i,p.getAllele(i));
	return out;
    }


    public Phenotype<Integer> decode(Genotype<Integer> g) {
	Phenotype<Integer> out = new Phenotype<>(numInts);
        for(int i = 0; i < g.size(); i++)
	    out.setAllele(i,g.getGene(i));
	return out;
    }

    public Genotype<Integer> newRandomGenotype(){
	Random r = new Random(4);
	Genotype<Integer> nuevo = new Genotype<>(numInts);
	for(int i = 0; i < nuevo.size(); i++)
	    nuevo.setGene(i,r.ints(1,1,numInts).findFirst().getAsInt());
	return nuevo;
    }




}
