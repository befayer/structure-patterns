package Model;

import Exceptions.DuplicateModelNameException;
import Exceptions.ModelPriceOutOfBoundsException;
import Exceptions.NoSuchModelNameException;
import Interface.Vehicle;

import java.util.Arrays;

public class Car implements Vehicle, Cloneable{

    private String brand;

    public Car(String brand, int n){
        this.brand = brand;
        modelArray = new Model[n];
        for (int i = 0; i < n; i++){
            modelArray[i] = new Model("model " + i, 0);
        }
    }

    public String getBrand(){
        return brand;
    }

    @Override
    public void setBrand(String brand){
        this.brand = brand;
    }

    public String[] getModelNamesArray(){
        String[] modelNamesArray = new String[modelArray.length];
        for (int i = 0; i < modelNamesArray.length; i++){
            if (modelArray[i] != null){
                modelNamesArray[i] = modelArray[i].getModelName();
            }
        }
        return modelNamesArray;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car car = (Car)super.clone();
        car.modelArray = new Model[getModelArraySize()];
        for(int i = 0; i<getModelArraySize();i++)
            car.modelArray[i] = (Model) modelArray[i].clone();
        return car;
    }

    @Override
    public void setNewModelName(String oldNAme, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        int id = -1;
        for (int i = 0; i < modelArray.length; i++) {
            if (modelArray[i].modelName.equals(oldNAme)) {
                id = i;

            }
            if(modelArray[i].modelName.equals(newName)) throw new DuplicateModelNameException("Модель с названием " + "'" + newName + "'" + " уже существует");
        }

        if (id == -1) throw new NoSuchModelNameException("Введенной модели не существует");
        else modelArray[id].modelName = newName;
    }

    @Override
    public double[] getModelPricesArray(){
        double[] modelPricesArray = new double[modelArray.length];
        for (int i = 0; i < modelPricesArray.length; i++){
            if (modelArray[i] != null){
                modelPricesArray[i] = modelArray[i].getModelPrice();
            }
        }
        return modelPricesArray;
    }

    @Override
    public double getPriceByModelName(String modelName) throws NoSuchModelNameException{
        int id = -1;
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = i;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
        else  return modelArray[id].getModelPrice();
    }

    @Override
    public void setPriceByModelName(String modelName, double newPrice) throws NoSuchModelNameException{
        int id = -1;
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException("Введено некорректное значение цены");
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = 1;
                modelArray[i].setModelPrice(newPrice);
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
    }

    @Override
    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException{
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException("Введено некорректное значение цены");
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)) throw new DuplicateModelNameException("Модель с названием " + modelName + " уже существует");
        }
        modelArray = Arrays.copyOf(modelArray, modelArray.length + 1);
        modelArray[modelName.length() - 1] = new Model(modelName, modelPrice);
    }

    @Override
    public void removeModel(String modelName) throws NoSuchModelNameException{
        int id = -1;
        for (int i = 0; i < modelArray.length; i++){
            if (modelArray[i].getModelName().equals(modelName)){
                id = i;
            }
        }
        if (id == -1) throw new NoSuchModelNameException("Модели с данным названием не существует");
        System.arraycopy(modelArray, id+1, modelArray, id, modelArray.length - id - 1);
        modelArray = Arrays.copyOf(modelArray, modelArray.length - 1);
    }

    @Override
    public int getModelArraySize(){
        return modelArray.length;
    }

    @Override
    public String getModelName() {
        return null;
    }

    @Override
    public double getModelPrice(int i) {
        return 0;
    }

    private Model[] modelArray;

    private class Model implements Cloneable {
        private double modelPrice = Double.NaN;
        private String modelName = null;

        public Model(String modelName, double modelPrice){
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public void setModelName(String modelName){
            this.modelName = modelName;
        }

        public void setModelPrice(double modelPrice){
            this.modelPrice = modelPrice;
        }

        public String getModelName(){
            return modelName;
        }

        public double getModelPrice(){
            return modelPrice;
        }

    }
}
