public class Griewank implements Problem {
    private final int d;
    public Griewank(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -600.0; }
    @Override public double getUpperBound() { return 600.0; }
    @Override public String getName() { return "Griewank"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        double product = 1.0;
        for (int i = 0; i < d; i++) {
            sum += (x[i] * x[i]) / 4000.0;
            product *= Math.cos(x[i] / Math.sqrt(i + 1.0)); // i+1 因为数学下标从1开始
        }
        return sum - product + 1.0;
    }
}