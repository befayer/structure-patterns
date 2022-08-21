package Source;
import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Interface.*;
import Model.*;

import java.io.*;
import java.util.Arrays;

public class Static {

    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory factory){
        Static.factory = factory;
    }

    public static Vehicle createInstance(String brand, int size) throws DuplicateModelNameException, NoSuchModelNameException {
        return factory.createInstance(brand, size);
    }

    public static void printAllNames(Vehicle vehicle){
        System.out.println("Массив названий моделей: "+Arrays.toString(vehicle.getModelNamesArray()));
    }

    public static void printAllPrices(Vehicle inter){
        System.out.println("Массив цен моделей: "+Arrays.toString(inter.getModelPricesArray()));
    }

    public static double averagePrice(Vehicle vehicle){
        double avg = 0;
        for (int i = 0; i <vehicle.getModelArraySize() ; i++) {
            avg += vehicle.getModelPricesArray()[i];
        }
        return avg;
    }

    public static Transport synchronizedTransport(Transport t){
        return new SynchronizedTransport(t);
    }
}
