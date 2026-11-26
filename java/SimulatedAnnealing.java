import java.util.Random;

public class SimulatedAnnealing {
    private final int maxEvaluations;
    private final Random random;
    
    // 模拟退火参数
    private final double initialTemp = 1000.0; // 初始温度
    private final double finalTemp = 1e-5;     // 终止温度

    public SimulatedAnnealing(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
        this.random = new Random();
    }

    public double[] optimize(Problem problem) {
        int d = problem.getDimension();
        double lb = problem.getLowerBound();
        double ub = problem.getUpperBound();

        double[] record = new double[maxEvaluations];
        
        // 1. 随机生成初始解
        double[] currentX = new double[d];
        for (int i = 0; i < d; i++) {
            currentX[i] = lb + (ub - lb) * random.nextDouble();
        }
        double currentFitness = problem.evaluate(currentX);
        
        double bestFitness = currentFitness;
        double[] bestX = currentX.clone();

        // 计算降温系数 (保证从 initialTemp 降到 finalTemp)
        double alpha = Math.pow(finalTemp / initialTemp, 1.0 / maxEvaluations);
        double temp = initialTemp;

        // 记录第 0 次
        record[0] = bestFitness;

        // 2. 迭代直到达到最大计算次数
        for (int t = 1; t < maxEvaluations; t++) {
            // 生成邻域解（在当前解附近加上随机扰动，扰动范围与温度成正比）
            double[] newX = new double[d];
            for (int i = 0; i < d; i++) {
                // 使用高斯扰动（也可以使用均匀分布）
                double perturbation = random.nextGaussian() * temp * 0.1;
                newX[i] = currentX[i] + perturbation;
                
                // 边界处理
                if (newX[i] < lb) newX[i] = lb;
                if (newX[i] > ub) newX[i] = ub;
            }

            double newFitness = problem.evaluate(newX);
            double delta = newFitness - currentFitness;

            // Metropolis 接受准则
            if (delta < 0 || random.nextDouble() < Math.exp(-delta / temp)) {
                currentX = newX;
                currentFitness = newFitness;

                // 更新全局最优
                if (currentFitness < bestFitness) {
                    bestFitness = currentFitness;
                    bestX = currentX.clone();
                }
            }

            // 记录当前最优值
            record[t] = bestFitness;
            
            // 降温
            temp *= alpha;
        }

        return record;
    }
}