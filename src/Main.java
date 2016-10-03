import gaframework.*;
import gaframeworkgui.*;
import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.io.File;
public class Main{
    // static BinInteger cod =  new BinInteger(10, problem.getDimension());


    public static void encodeProblem(TSPInstance problem) throws Exception {
        problem.addTour(new File("../tsp/pcb442.opt.tour"));
        for (Tour tour : problem.getTours()) {
            System.out.println(tour.distance(problem));
        }
    	/*int [] phenotype = problem.getFixedEdges().listNodes();
    	Genotype<Integer> gen =  new Genotype<>(genotype.length + 1);
    	/* creamos el ciclo
    	gen.setGene(0, genotype[0]);
    	gen.setGene(genotype.length, genotype[0]);
    	/* omitimos el primero y el último
    	for (int i = 1; i < genotype.length; i++) {
            gen.setGene(i, genotype[i]);
    	}
    	return gen;*/
    }

    public static void main(String[] argumenta){
        TSPInstance problem = null;
        try{
            problem = new TSPInstance(new File("../tsp/pcb442.tsp"));
            encodeProblem(problem);
        } catch(Exception e){e.printStackTrace();}
        /*
	BinInteger cod = new BinInteger(10,1);
	OnePointCrossover<Boolean> opcruza = new OnePointCrossover<>(3, 0.75);
	UniformMutation opmutacion = new UniformMutation(0.01, 3);
	Ruleta<Boolean,Integer> ruleta = new Ruleta<>(10, 100);
	MaxFun ff = new MaxFun();
	NumGeneraciones ng = new NumGeneraciones(1000);

	//Simple<Boolean, Integer> algoSimple = new Simple<>(cod,null,opcruza,opmutacion,ruleta,ff,ng,20);
	Simple<Boolean, Integer> algoSimple = new Simple<>(cod,null,opcruza,opmutacion,ruleta,ff,null,ng,1000);
	gaframeworkgui.GAGUI.setGA(algoSimple);
        gaframeworkgui.GAGUI.launch(gaframeworkgui.GAGUI.class);*/
	//algoSimple.run();

    }
}
