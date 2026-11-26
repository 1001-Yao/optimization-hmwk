public class Main {
    public static void main(String[] args) {
        int d = 100;
        int maxEval = 10000;
        int repeats = 100;

        // 把所有问题放进数组
        Problem[] problems = {
                new SumSquares(d),
                new Rosenbrock(d),
                new DixonPrice(d),
                new Ackley(d),
                new Rastrigin(d),
                new Griewank(d),
                new Levy(d),
                new Michalewicz(d),
                new Schwefel(d),
                new StyblinskiTang(d)
        };

        // 算法实例化（可复用）
        RandomSearch rs = new RandomSearch(maxEval);
        SimulatedAnnealing sa = new SimulatedAnnealing(maxEval);
        GeneticAlgorithm ga = new GeneticAlgorithm(maxEval);

        // 双重循环：遍历每个问题，跑 3 个算法
        for (Problem p : problems) {
            System.out.println("=================================================");
            System.out.println("开始测试问题: " + p.getName());

            // 跑 RS
            runAndSave(rs, p, repeats, maxEval, "RS");
            // 跑 SA
            runAndSave(sa, p, repeats, maxEval, "SA");
            // 跑 GA
            runAndSave(ga, p, repeats, maxEval, "GA");
        }

        System.out.println("=== 所有实验完成，准备去 Matlab 画图吧！ ===");
    }

    private static void runAndSave(Object algorithm, Problem problem, int repeatTimes, int maxEvaluations, String algoName) {
        String fileName = problem.getName() + "_" + algoName + ".csv";

        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(fileName))) {
            long startTime = System.currentTimeMillis();

            for (int r = 0; r < repeatTimes; r++) {
                double[] record = new double[maxEvaluations];

                if (algorithm instanceof RandomSearch) {
                    record = ((RandomSearch) algorithm).optimize(problem);
                } else if (algorithm instanceof SimulatedAnnealing) {
                    record = ((SimulatedAnnealing) algorithm).optimize(problem);
                } else if (algorithm instanceof GeneticAlgorithm) {
                    record = ((GeneticAlgorithm) algorithm).optimize(problem);
                }

                for (int i = 0; i < maxEvaluations; i++) {
                    writer.write(String.valueOf(record[i]));
                    if (i < maxEvaluations - 1) writer.write(",");
                }
                writer.newLine();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("  -> " + algoName + " 完成！耗时: " + (endTime - startTime) / 1000.0 + " 秒，已保存: " + fileName);

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}