public class Michalewicz implements Problem {
    private final int d;
    public Michalewicz(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return 0.0; }
    @Override public double getUpperBound() { return Math.PI; }
    @Override public String getName() { return "Michalewicz"; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < d; i++) {
            // 幂次20用 Math.pow，注意 i+1
            double inner = ((i + 1.0) * x[i] * x[i]) / Math.PI;
            sum += Math.sin(x[i]) * Math.pow(Math.sin(inner), 20);
        }
        return -sum; // 公式前面有个负号
    }
}