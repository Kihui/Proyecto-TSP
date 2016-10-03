import gaframework.*;
import gaframeworkgui.*;
import gaframework.*;
import org.moeaframework.problem.tsplib.*;
import java.io.File;
import java.io.IOException;

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
        } catch(IOException ioe){
            System.err.println("Hubo un problema al abrir el archivo TSP");
            ioe.printStackTrace();
        }
        if(problema == null)
            return;


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
