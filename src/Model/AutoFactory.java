package Model;

import Interface.*;

public class AutoFactory implements TransportFactory{

    @Override
    public Vehicle createInstance(String brand, int modelsCount) {
        return new Car(brand, modelsCount);
    }
}