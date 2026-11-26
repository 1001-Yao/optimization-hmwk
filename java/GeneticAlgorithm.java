import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    private final int maxEvaluations;
    private final Random random;
    
    // 遗传算法参数
    private final int popSize = 50;           // 种群大小
    private final double crossoverRate = 0.8; // 交叉概率
    private final double mutationRate = 0.1;  // 变异概率
    private final int tournamentSize = 3;     // 锦标赛规模

    public GeneticAlgorithm(int maxEvaluations) {
        this.maxEvaluations = maxEvaluations;
        this.random = new Random();
    }

    // 内部类：个体
    private static class Individual {
        double[] genes;
        double fitness;

        Individual(double[] genes, double fitness) {
            this.genes = genes;
            this.fitness = fitness;
        }
    }

    public double[] optimize(Problem problem) {
        int d = problem.getDimension();
        double lb = problem.getLowerBound();
        double ub = problem.getUpperBound();

        double[] record = new double[maxEvaluations];
        int evalCount = 0;

        // 1. 初始化种群
        List<Individual> population = new ArrayList<>();
        for (int i = 0; i < popSize; i++) {
            double[] genes = new double[d];
            for (int j = 0; j < d; j++) {
                genes[j] = lb + (ub - lb) * random.nextDouble();
            }
            double fitness = problem.evaluate(genes);
            population.add(new Individual(genes, fitness));
            record[evalCount++] = getBest(population).fitness;
        }

        // 2. 进化循环，直到评估次数达到 10000
        while (evalCount < maxEvaluations) {
            List<Individual> newPopulation = new ArrayList<>();

            // 精英保留：把当前最优个体直接放进新种群
            Individual bestInd = getBest(population);
            newPopulation.add(new Individual(bestInd.genes.clone(), bestInd.fitness));

            // 生成下一代
            while (newPopulation.size() < popSize && evalCount < maxEvaluations) {
                // 选择
                Individual p1 = tournamentSelect(population);
                Individual p2 = tournamentSelect(population);

                // 交叉
                double[] childGenes = new double[d];
                if (random.nextDouble() < crossoverRate) {
                    for (int i = 0; i < d; i++) {
                        // 算术交叉：取两者之间（带随机权重）的点
                        double alpha = random.nextDouble();
                        childGenes[i] = alpha * p1.genes[i] + (1 - alpha) * p2.genes[i];
                    }
                } else {
                    childGenes = p1.genes.clone();
                }

                // 变异
                for (int i = 0; i < d; i++) {
                    if (random.nextDouble() < mutationRate) {
                        // 加上一个较小的高斯扰动
                        childGenes[i] += random.nextGaussian() * (ub - lb) * 0.1;
                    }
                    // 边界截断
                    if (childGenes[i] < lb) childGenes[i] = lb;
                    if (childGenes[i] > ub) childGenes[i] = ub;
                }

                // 评估子代
                double childFitness = problem.evaluate(childGenes);
                newPopulation.add(new Individual(childGenes, childFitness));
                
                // 记录
                record[evalCount] = Math.min(getBest(newPopulation).fitness, 
                                             record[evalCount - 1]);
                evalCount++;
            }
            
            // 处理可能是最后一次循环（如果 popSize 不能被 10000 刚好整除）
            // 如果是最后一步，直接用新种群替换旧种群
            population = newPopulation;
        }

        return record;
    }

    // 锦标赛选择
    private Individual tournamentSelect(List<Individual> pop) {
        Individual best = pop.get(random.nextInt(pop.size()));
        for (int i = 1; i < tournamentSize; i++) {
            Individual contender = pop.get(random.nextInt(pop.size()));
            if (contender.fitness < best.fitness) {
                best = contender;
            }
        }
        return best;
    }

    // 获取种群中最优个体
    private Individual getBest(List<Individual> pop) {
        Individual best = pop.get(0);
        for (Individual ind : pop) {
            if (ind.fitness < best.fitness) {
                best = ind;
            }
        }
        return best;
    }
}