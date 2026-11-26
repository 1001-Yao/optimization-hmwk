public interface Problem {
    int getDimension();        // 获取维度 (100)
    double getLowerBound();    // 获取搜索下界
    double getUpperBound();    // 获取搜索上界
    double evaluate(double[] x); // 计算目标函数值
    String getName();          // 获取问题名称 (用于生成文件名)
}