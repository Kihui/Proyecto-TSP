import gaframework.*;

public class MaxFun implements FitnessFunction<Integer>{
    public double evaluate(Phenotype<Integer> p){
	if(p == null)
	    System.out.println("Fenotipo null");
	if(p.getAllele(0) == null)
	    System.out.println("Alelo null");
	
	int x = p.getAllele(0).intValue();	
	return x*(1024-x);
    }
}
