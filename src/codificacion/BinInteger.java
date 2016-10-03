import gaframework.*;
import java.util.BitSet;
import java.util.Random;

public class BinInteger implements Codification<Boolean, Integer>{
    private int numBits; //el número de bits para representar un entero
    private int numInts; //el número de ints que componen un fenotipo

    public BinInteger(int b, int i){
	numBits = b;
	numInts = i;
    }

    public Genotype<Boolean> encode (Phenotype<Integer> p){
	Genotype<Boolean> out = new Genotype<>(p.size()*numBits);
	for(int i = 0; i < p.size(); i++){
	    int k = numBits - 1;
	    int c = p.getAllele(i);
	    while(k >= 0) {
		int tmp = c & 1;
		if(tmp != 0)
		    out.setGene(k,true);
		else
		    out.setGene(k,false);
		c >>= 1;
		k--;
	    }
	}
	return out;
    }

    
    public Phenotype<Integer> decode(Genotype<Boolean> g) {
	BitSet bitsBuffer = new BitSet(numBits);
	int pSize = g.size()/numBits;
	Phenotype<Integer> out = new Phenotype<>(pSize);
	int gindex = 0;
	for(int pindex = 0; pindex < pSize; pindex++){
	    while(gindex == 0 || gindex % numBits != 0){
		bitsBuffer.set(gindex % numBits, g.getGene(gindex));
		gindex++;
	    }
	    out.setAllele(pindex,bitsToInt(bitsBuffer));
	    bitsBuffer.clear();
	}
	return out;
    }

    /*public Phenotype<Integer> decode(Genotype<Boolean> g) {
	BitSet bitsBuffer = new BitSet(numBits);
	int pSize = g.size()/numBits;
	Phenotype<Integer> out = new Phenotype<>(pSize);
	int pindex = 0;
	for(int gindex = 0; gindex < g.size(); gindex++){
	    if(gindex != 0 && gindex % numBits == 0){
		out.setAllele(pindex,bitsToInt(bitsBuffer));
		bitsBuffer.clear();
		pindex++;
	    }
	    bitsBuffer.set(gindex % numBits,g.getGene(gindex));	    
	}
	return out;
	}*/


    private int bitsToInt(BitSet bs){
	int out = 0;
	for(int i= bs.length()-1; i >= 0; i--) //Depende cual es el bit más significativo
	    //for(int i = 0; i < bs.length(); i++)
	    if(bs.get(i))
		out += Math.pow(2,i);
	return out;
    }

    public Genotype<Boolean> newRandomGenotype(){
	Random r = new Random();
	Genotype<Boolean> nuevo = new Genotype<>(numBits*numInts);
	for(int i = 0; i < nuevo.size(); i++)
	    nuevo.setGene(i,r.nextBoolean());
	return nuevo;
    }
    

    

}
