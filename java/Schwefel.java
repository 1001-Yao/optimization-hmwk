public class Schwefel implements Problem {
    private final int d;
    public Schwefel(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -500.0; }
    @Override public double getUpperBound() { return 500.0; }
    @Override public String getName() { return "Schwefel"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < d; i++) {
            sum += x[i] * Math.sin(Math.sqrt(Math.abs(x[i])));
        }
        return 418.9829 * d - sum;
    }
}