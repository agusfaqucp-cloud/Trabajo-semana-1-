package observer;

public interface Subject {
    void agregarObserver(Observer o);
    void eliminarObserver(Observer o);
    void notificar();
}
