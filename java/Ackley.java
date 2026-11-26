public class Ackley implements Problem {
    private final int d;
    public Ackley(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -32.768; }
    @Override public double getUpperBound() { return 32.768; }
    @Override public String getName() { return "Ackley"; }

    @Override
    public double evaluate(double[] x) {
        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 0; i < d; i++) {
            sum1 += x[i] * x[i];
            sum2 += Math.cos(2.0 * Math.PI * x[i]);
        }
        double term1 = -20.0 * Math.exp(-0.2 * Math.sqrt(sum1 / d));
        double term2 = -Math.exp(sum2 / d);
        // Math.exp(1) 就是自然常数 e
        return term1 + term2 + 20.0 + Math.exp(1);
    }
}