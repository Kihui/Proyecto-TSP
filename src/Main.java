import gaframework.*;
import gaframeworkgui.*;
import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
public class Main{
    public static TSPInstance problema = null;
    public static void main(String[] argumenta){
        if(argumenta.length != 1){
            System.err.println("La vida es ruda");
            return;
        }
        try{
            File tsp = new File(argumenta[0]);
            problema = new TSPInstance(tsp);
            problema.addTour(new File("../tsp/ulysses16.opt.tour"));
            for (Tour tour : problema.getTours()) {
                System.out.println(tour.distance(problema));
            }
        } catch(IOException ioe){
            System.err.println("Hubo un problema al abrir el archivo TSP");
            ioe.printStackTrace();
        }
        if(problema == null)
            return;

        int[] ciudades = problema.getDistanceTable().listNodes();
        LinkedList<Integer> c = new LinkedList<>();
        for(int i = 0; i < ciudades.length; i++)
            c.add(ciudades[i]);
        NoBin cod = new NoBin(problema.getDistanceTable().listNodes().length);
        AVCrossover<Integer> opcruza = new AVCrossover<>(5,0.8);
        AVMutation opmutacion = new AVMutation(0.01, 3);
        Ruleta<Integer,Integer> ruleta = new Ruleta<>(2,2);
        AdaptacionAV fitfun = new AdaptacionAV(problema.getDistanceTable());
        ObjetivoAV objfun = new ObjetivoAV();
        NumGeneraciones ng = new NumGeneraciones(1200);
        Simple<Integer, Integer> algoSimple = new Simple<Integer,Integer>(cod,null,opcruza,opmutacion,ruleta,fitfun,objfun,ng,c,666);
	gaframeworkgui.GAGUI.setGA(algoSimple);
        gaframeworkgui.GAGUI.launch(gaframeworkgui.GAGUI.class);
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
