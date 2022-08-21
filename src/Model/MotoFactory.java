package Model;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;
import Interface.TransportFactory;
import Interface.Vehicle;

public class MotoFactory implements TransportFactory {

    @Override
    public Vehicle createInstance(String brand, int modelsCount) throws DuplicateModelNameException, NoSuchModelNameException {
        return new Moto(brand, modelsCount);
    }
}
