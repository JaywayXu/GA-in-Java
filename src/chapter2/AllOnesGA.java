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
 * 这个问题的简单性使得GeneticAlgorithm类的"calcFitness"方法是十分简单。我们仅仅需要数染色体中1的数量，使用这个作为适应度函数。
 * 相似的
 * 
 * @author bkanber
 *
 */
public class AllOnesGA {

	public static void main(String[] args) {
		// Create GA object
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);

		// Initialize population
		Population population = ga.initPopulation(50);

		// Evaluate population
		ga.evalPopulation(population);

		// Keep track of current generation
		int generation = 1;

		/**
		 * Start the evolution loop
		 * 
		 * Every genetic algorithm problem has different criteria for finishing.
		 * In this case, we know what a perfect solution looks like (we don't
		 * always!), so our isTerminationConditionMet method is very
		 * straightforward: if there's a member of the population whose
		 * chromosome is all ones, we're done!
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
		 * We're out of the loop now, which means we have a perfect solution on
		 * our hands. Let's print it out to confirm that it is actually all
		 * ones, as promised.
		 */
		System.out.println("Found solution in " + generation + " generations");
		System.out.println("Best solution: " + population.getFittest(0).toString());
	}
}
