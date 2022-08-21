package Model;

import Exceptions.DuplicateModelNameException;
import Exceptions.ModelPriceOutOfBoundsException;
import Exceptions.NoSuchModelNameException;
import Interface.Vehicle;

public class Moto implements Vehicle, Cloneable{

    private String brand;

    private int size = 0;

    public Moto(String brand, int n) throws DuplicateModelNameException, NoSuchModelNameException {
        this.brand = brand;
        size = 0;
        for (int i = 0; i < n; i++){
            addModel("model " + i, 0);
        }
    }

    private class Model implements Cloneable{
        String modelName = null;
        double modelPrice = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model(String modelName, double modelPrice){
            this.modelName = modelName;
            this.modelPrice = modelPrice;
        }

        public Model(){
            this.modelName = null;
            this.modelPrice = Double.NaN;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }

    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }

    public String getBrand(){return brand;}

    public void setBrand(String brand){this.brand = brand;}

    @Override
    public void setNewModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Model p = head;
        do{
            p=p.next;
        }
        while((p.next!=head) && (!p.modelName.equals(oldName)));
        if(!(p.modelName.equals(oldName))) throw new NoSuchModelNameException("Такой модели не существует");
        testDuplicate(newName);
        p.modelName = newName;
    }

    @Override
    public String[] getModelNamesArray(){
        Model p = head.next;
        String[] modelNamesArray = new String[size];
        if (modelNamesArray.length != 0){
            for (int i = 0; i < modelNamesArray.length; i++){
                modelNamesArray[i] = p.modelName;
                p = p.next;
            }
        }
        return modelNamesArray;
    }

    @Override
    public double[] getModelPricesArray(){
        Model p = head.next;
        double[] modelPricesArray = new double[size];
        if (modelPricesArray.length != 0){
            for (int i = 0; i < modelPricesArray.length; i++){
                modelPricesArray[i] = p.modelPrice;
                p = p.next;
            }
        }
        return modelPricesArray;
    }

    @Override
    public double getPriceByModelName (String modelName) throws NoSuchModelNameException{
        Model p = head.next;
        if (p != null){
            while ((p.next != head) && !p.modelName.equals(modelName)){
                p = p.next;
            }
            if (!p.modelName.equals(modelName)) throw new NoSuchModelNameException("Модели с данным названием не существует!");
            return p.modelPrice;
        }
        else return 0;
    }

    @Override
    public void setPriceByModelName (String modelName, double newPrice) throws NoSuchModelNameException{
        Model p = head.next;
        if (p != null){
            while ((p.next != head) && !p.modelName.equals(modelName)){
                p = p.next;
            }
            if (!p.modelName.equals(modelName)) throw new NoSuchModelNameException("Модели с данным названием не существует!");
            p.modelPrice = newPrice;
        }

        while (p.next!=head){
            if (p.modelName.equals(modelName)){
                break;
            }
            p = p.next;
        }
        if (p == head) throw new NoSuchModelNameException("Такой модели не существует!");
    }

    private int getIndex(Model test) throws NoSuchModelNameException{
        Model p = head.next;
        if (p == head) return -1;
        else {
            int id = 0;
            while (p!=head && p!=test){
                p = p.next;
            }
            if (p == head) throw new NoSuchModelNameException("Такой модели не существует!");
            else return id;
        }
    }

    public void testDuplicate(String nameModel) throws DuplicateModelNameException, NoSuchModelNameException {
        Model p = head.next;
        while (p != head) {
            if (p.modelName.equals(nameModel)) {
                throw new DuplicateModelNameException("Модель с названием " + "'" + nameModel + "'" + " уже существует");
            }
            p = p.next;
        }
    }

    public void addModel(String name, double price) throws DuplicateModelNameException, NoSuchModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Введено неверное значение цены");
        Model p = new Model(name, price);
        if (head.next == head) {
            head.next = p;
            head.prev = p;
            p.prev = head;
            p.next = head;
        } else {
            testDuplicate(name);
            p.prev = head.prev;
            p.next = head;
            head.prev.next = p;
            head.prev = p;
        }
        size++;
    }

    public void removeModel(String Name) throws NoSuchModelNameException{
        Model p = head;
        do {
            p = p.next;
        } while (p.next != head && (!(p.modelName.equals(Name))));
        if (!p.modelName.equals(Name)) throw new NoSuchModelNameException("Такой модели не существует");
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Moto clonedMotorcycle = (Moto) super.clone();
        clonedMotorcycle.head = new Model();
        clonedMotorcycle.head.next = clonedMotorcycle.head;
        clonedMotorcycle.head.prev = clonedMotorcycle.head;
        Model cloned = this.head.next;
        while(cloned != this.head){
            Model clonedModel = (Model) cloned.clone();
            clonedMotorcycle.head.prev.next = clonedModel;
            clonedModel.prev = clonedMotorcycle.head.prev;
            clonedModel.next = clonedMotorcycle.head;
            clonedMotorcycle.head.prev = clonedModel;
            cloned = cloned.next;
        }
        return clonedMotorcycle;
    }

    @Override
    public int getModelArraySize() {
        return size;
    }

    @Override
    public String getModelName() {
        return null;
    }

    @Override
    public double getModelPrice(int i) {
        return 0;
    }

}
