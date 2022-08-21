package Interface;

import Exceptions.DuplicateModelNameException;
import Exceptions.NoSuchModelNameException;

public interface TransportFactory {
    Vehicle createInstance(String mark, int modelsCount) throws DuplicateModelNameException, NoSuchModelNameException;
}