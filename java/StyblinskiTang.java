public class StyblinskiTang implements Problem {
    private final int d;
    public StyblinskiTang(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -5.0; }
    @Override public double getUpperBound() { return 5.0; }
    @Override public String getName() { return "StyblinskiTang"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < d; i++) {
            double x2 = x[i] * x[i];
            double x4 = x2 * x2;
            sum += x4 - 16.0 * x2 + 5.0 * x[i];
        }
        return 0.5 * sum;
    }
}