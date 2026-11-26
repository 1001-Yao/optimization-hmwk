public class Levy implements Problem {
    private final int d;
    public Levy(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -10.0; }
    @Override public double getUpperBound() { return 10.0; }
    @Override public String getName() { return "Levy"; }

    @Override
    public double evaluate(double[] x) {
        double[] w = new double[d];
        for (int i = 0; i < d; i++) {
            w[i] = 1.0 + (x[i] - 1.0) / 4.0;
        }
        
        double term1 = Math.pow(Math.sin(Math.PI * w[0]), 2);
        double sum = 0.0;
        for (int i = 0; i < d - 1; i++) {
            sum += Math.pow(w[i] - 1.0, 2) * (1.0 + 10.0 * Math.pow(Math.sin(Math.PI * w[i] + 1.0), 2));
        }
        double term2 = sum;
        double term3 = Math.pow(w[d - 1] - 1.0, 2) * (1.0 + Math.pow(Math.sin(2.0 * Math.PI * w[d - 1]), 2));
        
        return term1 + term2 + term3;
    }
}