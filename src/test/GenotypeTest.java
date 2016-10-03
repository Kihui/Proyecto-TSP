import gaframework.*;

public class GenotypeTest {

    public static void main(String[] args) {

	int SIZE = 5; 
	
	Genotype<Boolean> g = new Genotype<>(SIZE);

	System.out.println("Contenido del genotipo antes de llenarlo:"); 

	for (int i = 0; i < SIZE; i++) {
	    g.setGene(i, i % 2 == 0);
	}

	System.out.println("\n\nContenido del genotipo después de llenarlo:"); 
	for (int i = 0; i < SIZE; i++) {
	    System.out.print(g.getGene(i) + " ");
	}

	System.out.println("\n\nTamaño del genotipo:\n" + g.size());

	System.out.println("\nEquals misma intancia:");
	System.out.println(g.equals(g));

	System.out.println("\nEquals mismo contenido:");
	Genotype<Boolean> f = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    f.setGene(i, i % 2 == 0);
	}
	System.out.println(g.equals(f));

	System.out.println("\nEquals distinto contenido:");
	f = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    f.setGene(i, i % 2 != 0);
	}
	System.out.println(g.equals(f));

	
	System.out.println("\nEquals distinto tipo:");
	System.out.println(g.equals(new Double(1.0)));

	System.out.println("\nEquals distinto tipo contenido:");
	Genotype<Double> h = new Genotype<>(SIZE);
	for (int i = 0; i < SIZE; i++) {
	    h.setGene(i, (double) i);
	}
	System.out.println(g.equals(h));	
	
	
    }

}
