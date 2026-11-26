public class Rastrigin implements Problem {
    private final int d;
    public Rastrigin(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -5.12; }
    @Override public double getUpperBound() { return 5.12; }
    @Override public String getName() { return "Rastrigin"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 10.0 * d;
        for (int i = 0; i < d; i++) {
            sum += x[i] * x[i] - 10.0 * Math.cos(2.0 * Math.PI * x[i]);
        }
        return sum;
    }
}