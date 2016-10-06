import gaframework.*;
import java.util.*;
import org.moeaframework.problem.tsplib.*;

public class Simple<G,P> implements GeneticAlgorithm<G,P> {
    private Breeder<G,P> breeder;
    private CrossoverOp<G> crossoverOp;
    private MutationOp<G> mutationOp;
    private SelectionOp<G,P> selectionOp;
    private TerminationCondition<G,P> termination;
    private ObjectiveFunction<G,P> objFun;
    private final LinkedList<P> nodos;
    private final int popSize;
    private boolean cont;

    public Simple(Codification<G,P> cod,
		  Corrector<G> cor,
		  CrossoverOp<G> cro,
		  MutationOp<G> muo,
		  SelectionOp<G,P> seo,
		  FitnessFunction<P> fun,
		  ObjectiveFunction<G,P> objFun,
		  TerminationCondition<G,P> ter,
		  LinkedList<P> nodos,
                  int popSize){
        this.breeder = new Breeder<G,P>(cod, cor, fun);
	this.crossoverOp = cro;
	this.mutationOp = muo;
	this.selectionOp = seo;
	this.termination = ter;
	this.objFun = objFun;
	this.nodos = nodos;
        this.popSize = popSize;
	this.cont = true;
    }

    // Regresa el mejor individuo de la población p
    private Individual<G,P> getMejorIndividuo(Population<G,P> p) {
        Individual<G, P> ind = p.getIndividual(0);
        for(int i = 0; i < p.size(); i++) {
            if(ind.getObjectiveEvaluation() < p.getIndividual(i).getObjectiveEvaluation())
                ind = p.getIndividual(i);
        }
        return ind;
    }
    
    public Population<G,P> iteration(Population<G,P> current) {
	Population<G,P> out = new Population<>(current.getGeneration() + 1);
        // Evaluación función objetivo
        if (objFun != null) 
            objFun.evaluate(current);

        out.addIndividual(getMejorIndividuo(current));
        
	while (out.size() < current.size()) {
	    // Seleccion
	    List<Individual<G,P>> selectionList = selectionOp.select(current);
	    // Cruza
	    List<Genotype<G>> genotypeList = new LinkedList<>();
	    for (Individual<G,P> s : selectionList)
		genotypeList.add(s.getGenotype());
	    List<Genotype<G>> crossedList = crossoverOp.crossover(genotypeList);
	    // Mutacion
	    List<Genotype<G>> mutatedList = new LinkedList<>();
	    for (Genotype<G> c : crossedList)
		mutatedList.add(mutationOp.mutate(c));
	    // Nuevos individuos
	    for (Genotype<G> m : mutatedList)
		out.addIndividual(breeder.newIndividual(m));
	}
	return out;
    }

    private LinkedList<P> clonar(){
        LinkedList<P> villes = new LinkedList<>();
        for(int i = 0; i < nodos.size(); i++)
            villes.add(nodos.get(i));
        return villes;
    }

    private Population<G,P> randomValidP(){
        Population<G,P> p = new Population<G,P>(1);
        Random r = new Random(4);
        for(int i = 0; i < popSize; i++){
            LinkedList<P> ciudades = clonar();
            Phenotype<P> fenotipo = new Phenotype<>(ciudades.size());
            fenotipo.setAllele(0,ciudades.remove());
            for(int j = 1; j < fenotipo.size(); j++){
                int index = r.ints(1,0,ciudades.size()).findFirst().getAsInt();
                fenotipo.setAllele(j,ciudades.remove(index));
            }
            Individual<G,P> nuevo = breeder.newIndividual(fenotipo);
            p.addIndividual(nuevo);
        }
        return p;
    }

    public void run(){
	Population<G,P> p = randomValidP();
	while(!termination.conditionReached(p)){
	    p = iteration(p);
	    System.out.println(getMejorIndividuo(p));
	}
    }

    @Override
    public void run(List<Statistics> l){
	Population<G,P> p = randomValidP();
	while(!termination.conditionReached(p) && cont){
	    l.add(new Statistics(p));
	    p = iteration(p);
	    System.out.println("Generation: " + p.getGeneration());
	    System.out.println(getMejorIndividuo(p));
	}
    }

    public void stop(){
	cont = false;
    }

}
