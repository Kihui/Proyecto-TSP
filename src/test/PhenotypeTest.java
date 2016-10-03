import gaframework.*;

public class PhenotypeTest {

    public static void main(String[] args) {

	int SIZE = 5; 
	
	Phenotype<Boolean> g = new Phenotype<>(SIZE);

	System.out.println("Contenido del fenotipo antes de llenarlo:"); 

	for (int i = 0; i < SIZE; i++) {
	    g.setAllele(i, i % 2 == 0);
	}

	System.out.println("\n\nContenido del fenotipo después de llenarlo:"); 
	for (int i = 0; i < SIZE; i++) {
	    System.out.print(g.getAllele(i) + " ");
	}

	System.out.println("\n\nTamaño del fenotipo:\n" + g.size());

	System.out.println("\nEquals misma intancia:");
	System.out.println(g.equals(g));

	System.out.println("\nEquals mismo contenido:");
	Phenotype<Boolean> f = new Phenotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    f.setAllele(i, i % 2 == 0);
	}
	System.out.println(g.equals(f));

	System.out.println("\nEquals distinto contenido:");
	f = new Phenotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    f.setAllele(i, i % 2 != 0);
	}
	System.out.println(g.equals(f));

	
	System.out.println("\nEquals distinto tipo:");
	System.out.println(g.equals(new Double(1.0)));

	System.out.println("\nEquals distinto tipo contenido:");
	Phenotype<Double> h = new Phenotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    h.setAllele(i, (double) i);
	}
	System.out.println(g.equals(h));	
	
	
    }

}
