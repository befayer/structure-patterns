package Interface;

import Model.*;

public interface Transport {
    void setBrand(String brand);
    String getBrand();
    String[] getModelNamesArray();
    double[] getModelPricesArray();
    double getPriceByModelName(String name);
    void setPriceByModelName(String name, double newPrice);
    void setNewModelName(String oldName, String newName);
    void addModel(String name, double price);
    void removeModel(String name);
    int getModelArraySize();
    Transport clone() throws CloneNotSupportedException;
}
