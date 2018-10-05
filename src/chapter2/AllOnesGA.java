package chapter2;

/**
 * This is our main class used to run the genetic algorithm.
 * 每一章都需要一个引导类，用于初始化遗传算法，并作为应用程序的起点，用于定义main方法
 * 
 * This case is one of the simplest problems we can solve: the objective is to
 * end up with an individual whose chromosome is all ones.
 * 这个例子是一个最简单的例子，目标是得到染色体是全一的个体(即111111111111....)
 * 
 * The simplicity of this problem makes the GeneticAlgorithm class'
 * "calcFitness" method very simple. We'll just count the number of ones in the
 * chromosome and use that as the fitness score. Similarly, the
 * "isTerminationConditionMet" method in the GeneticAlgorithm class for this
 * example is very simple: if the fitness score (ie, number of ones) is the same
 * as the length of the chromosome (ie, we're all ones), we're done!
 * 这个问题的简单性使得GeneticAlgorithm类的"calcFitness"(计算适应度函数)方法十分简单。我们仅仅需要数染色体中1的数量，使用这个作为适应度函数。
 * 相似的,这个问题在GeneticAlgorithm class中的"isTerminationConditionMet"(是否达到终止条件)方法也很简单，
 * 只要适应度函数(即染色体中1的个数)和染色体长度一致时，即是染色体中基因全为1的情况，此时迭代停止
 * 
 * @author bkanber
 *
 */
public class AllOnesGA {

	public static void main(String[] args) {
		// Create GA object(初始化遗传算法，作为应用程序的起点)
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);
		// 使用典型的参数值，种群规模=100,变异率=0.01,交叉率=0.95,精英计数为0(即暂时禁用精英策略)

		// Initialize population
		/*
		 * 初始化一个潜在解构成的种群,这通常是随机的，但偶尔也可以采用系统化的方法使其变得更好，
		可以利用对搜索空间的一致信息来初始化种群
		*/
		Population population = ga.initPopulation(50);

		// Evaluate population
		ga.evalPopulation(population);

		// Keep track of current generation
		int generation = 1;

		/**
		 * Start the evolution loop
		 * 
		 * Every genetic algorithm problem has different criteria for finishing. In this
		 * case, we know what a perfect solution looks like (we don't always!), so our
		 * isTerminationConditionMet method is very straightforward: if there's a member
		 * of the population whose chromosome is all ones, we're done!
		 */
		while (ga.isTerminationConditionMet(population) == false) {
			// Print fittest individual from population
			System.out.println("Best solution: " + population.getFittest(0).toString());

			// Apply crossover
			population = ga.crossoverPopulation(population);

			// Apply mutation
			population = ga.mutatePopulation(population);

			// Evaluate population
			ga.evalPopulation(population);

			// Increment the current generation
			generation++;
		}

		/**
		 * We're out of the loop now, which means we have a perfect solution on our
		 * hands. Let's print it out to confirm that it is actually all ones, as
		 * promised.
		 */
		System.out.println("Found solution in " + generation + " generations");
		System.out.println("Best solution: " + population.getFittest(0).toString());
	}
}
