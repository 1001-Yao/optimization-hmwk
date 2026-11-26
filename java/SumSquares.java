public class SumSquares implements Problem {
    private final int dimension;

    public SumSquares(int dimension) {
        this.dimension = dimension;
    }

    @Override
    public int getDimension() { return dimension; }

    @Override
    public double getLowerBound() { return -5.12; }

    @Override
    public double getUpperBound() { return 5.12; }

    @Override
    public double evaluate(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < dimension; i++) {
            // 注意：公式中 i 是从 1 到 d，Java 数组下标从 0 开始，所以是 (i + 1)
            sum += (i + 1) * (x[i] * x[i]);
        }
        return sum;
    }

    @Override
    public String getName() { return "SumSquares"; }
}