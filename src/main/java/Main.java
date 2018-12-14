public class Main {

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.calculate();
        System.out.println("Всего строк: " + counter.getCount());
    }
}