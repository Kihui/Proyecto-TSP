package gaframework;

public class IndividualTest {

    public static void main(String[] args) {
	int SIZE = 5;
	// Constructor
	Genotype<Integer> g = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    g.setGene(i, i);
	}	
	Phenotype<Boolean> p = new Phenotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    p.setAllele(i, i%2 == 0);
	}
	Individual<Integer,Boolean> n = new Individual<>(g, p, 1.0);

	// getPhenotype
	Phenotype<Boolean> p2 = n.getPhenotype();
	for (int i = 0; i < SIZE; i++) {
	    System.out.print(p.getAllele(i)+ " ");
	}
	System.out.println();

	// getGenotype
	Genotype<Integer> g2 = n.getGenotype();
	for (int i = 0; i < SIZE; i++) {
	    System.out.print(g.getGene(i)+ " ");
	}
	System.out.println();

	// getFitness
	System.out.println(n.getFitness());

	// equals mimsa instancia
	System.out.println("Equals misma instancia: " + n.equals(n));

	// equals mismo contenido
	Genotype<Integer> sameValue = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    sameValue.setGene(i, i);
	}
	Individual<Integer,Boolean> n2 = new Individual<>(sameValue, p, 1.0);
	System.out.println("Equals mismos contenido: " + n.equals(n2));	

	// equals diferente contenido
	Genotype<Integer> differentValue = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    differentValue.setGene(i, i%2);
	}
	Individual<Integer,Boolean> n3 = new Individual<>(differentValue, p, 3.0);
	System.out.println("Equals distinto contenido: " + n.equals(n3));	

	// compareto menor
	System.out.println("Compare to menor: " + n.compareTo(n3));

	// compareTo igual
	System.out.println("Compare to igual :" + n.compareTo(n2));	

	// compareTo mayor
	System.out.println("Compare to mayor :" + n3.compareTo(n));

	// toString

	System.out.println(n);
	System.out.println(n2);
	System.out.println(n3);
	
    }



}
