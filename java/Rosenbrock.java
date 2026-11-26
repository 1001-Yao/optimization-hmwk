public class Rosenbrock implements Problem {
    private final int d;
    public Rosenbrock(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -2.048; }
    @Override public double getUpperBound() { return 2.048; }
    @Override public String getName() { return "Rosenbrock"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < d - 1; i++) {
            double term1 = 100.0 * Math.pow(x[i + 1] - x[i] * x[i], 2);
            double term2 = Math.pow(x[i] - 1.0, 2);
            sum += term1 + term2;
        }
        return sum;
    }
}