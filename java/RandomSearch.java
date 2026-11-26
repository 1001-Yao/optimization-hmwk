import java.util.Random;

public class RandomSearch {
    private final int maxEvaluations; // 最大计算次数 n_max = 10000
    private final Random random;

    public RandomSearch(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
        this.random = new Random();
    }

    /**
     * 执行一次完整的随机搜索
     * @param problem 优化问题对象
     * @return 长度为 maxEvaluations 的数组，记录每一步的当前最优值
     */
    public double[] optimize(Problem problem) {
        int d = problem.getDimension();
        double lb = problem.getLowerBound();
        double ub = problem.getUpperBound();

        double bestFitness = Double.MAX_VALUE; // 初始最优值设为无穷大
        double[] record = new double[maxEvaluations]; // 用于记录 10000 次评估的最优值
        double[] x = new double[d]; // 当前解

        for (int t = 0; t < maxEvaluations; t++) {
            // 1. 在搜索空间内生成一个全新解
            for (int i = 0; i < d; i++) {
                x[i] = lb + (ub - lb) * random.nextDouble();
            }

            // 2. 计算目标函数值
            double currentFitness = problem.evaluate(x);

            // 3. 更新历史最优
            if (currentFitness < bestFitness) {
                bestFitness = currentFitness;
            }

            // 4. 记录当前评估次数下的最优值
            record[t] = bestFitness;
        }

        return record;
    }
}