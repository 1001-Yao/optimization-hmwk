public class DixonPrice implements Problem {
    private final int d;
    public DixonPrice(int d) { this.d = d; }
    @Override public int getDimension() { return d; }
    @Override public double getLowerBound() { return -10.0; }
    @Override public double getUpperBound() { return 10.0; }
    @Override public String getName() { return "DixonPrice"; }

    @Override
    public double evaluate(double[] x) {
        // 注意数学下标是从1开始的，所以 (x1-1)^2 对应 x[0]
        double sum = Math.pow(x[0] - 1.0, 2);
        for (int i = 1; i < d; i++) {
            // 数学公式中的 i 从 2 到 d，对应 Java 中 i 从 1 到 d-1，系数为 i+1
            sum += (i + 1) * Math.pow(2.0 * x[i] * x[i] - x[i - 1], 2);
        }
        return sum;
    }
}