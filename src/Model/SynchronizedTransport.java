package Model;

import Interface.Transport;

public class SynchronizedTransport implements Transport {

    private Transport transport;

    public SynchronizedTransport(Transport transport){
        this.transport = transport;
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized String[] getModelNamesArray() {
        return transport.getModelNamesArray();
    }

    @Override
    public synchronized double[] getModelPricesArray() {
        return transport.getModelPricesArray();
    }

    @Override
    public synchronized double getPriceByModelName(String name) {
        return transport.getPriceByModelName(name);
    }

    @Override
    public synchronized void setPriceByModelName(String name, double newPrice) {
        transport.setPriceByModelName(name, newPrice);
    }

    @Override
    public synchronized void setNewModelName(String oldName, String newName) {
        transport.setNewModelName(oldName, newName);
    }

    @Override
    public synchronized void addModel(String name, double price) {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void removeModel(String name) {
        transport.removeModel(name);
    }

    @Override
    public synchronized int getModelArraySize() {
        return transport.getModelArraySize();
    }

    @Override
    public synchronized Transport clone() throws CloneNotSupportedException {
        return transport.clone();
    }
}
