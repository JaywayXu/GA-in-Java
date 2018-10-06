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

	/**
	 * 由于交叉和变异算子的效果，基本遗传算法往往会在世代之间失去种群中最好的个体。
	 * 解决这个问题的简单的优化技术，就是始终让最合适的个体不做改变，添加到下一代的种群中，这样，最优个体不会在世代之间丢失
	 * 虽然这些个体没有进行交叉操作，但它们仍会被选为另一个个体的亲代，让他们的遗传信息仍然可以和种群中的其他个体分享，
	 * 将最优解保留到下一代的过程称为“精英主义”
	 * 
	 * 通常情况下，群体中的“精英”个体的最佳数量只占种群总规模的很小一部分。原因是如果该值太高，会减缓遗传算法搜索过程，
	 * 因为保留太多个体会导致个体缺乏种群多样性
	 * @param args
	 * @author cloud
	 */
	public static void main(String[] args) {
		// Create GA object(初始化遗传算法，作为应用程序的起点)
		
		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);
		// 使用典型的参数值，种群规模=100,变异率=0.01,交叉率=0.95,精英计数为0(即暂时禁用精英策略)

		// Initialize population
		/*
		 * 初始化一个潜在解构成的种群,这通常是随机的，但偶尔也可以采用系统化的方法使其变得更好，
		 * 可以利用对搜索空间的一致信息来初始化种群，初始化种群时需要指定个体染色体长度
		 */
		Population population = ga.initPopulation(50);

		// Evaluate population
		ga.evalPopulation(population);

		// Keep track of current generation
		// 跟踪当前的一代
		int generation = 1;

		/**
		 * Start the evolution loop 开始进化循环
		 * 
		 * Every genetic algorithm problem has different criteria for finishing. In this
		 * case, we know what a perfect solution looks like (we don't always!), so our
		 * isTerminationConditionMet method is very straightforward: if there's a member
		 * of the population whose chromosome is all ones, we're done!
		 * 每个遗传算法问题都有不同的完成标准。 在这种情况下，我们知道一个完美的解决方案是什么样的(我们并不总是知道)，
		 * 所以我们的isTerminationConditionMet方法非常简单:如果有一个种群的个体，其染色体是全1，我们就完成了
		 */
		while (ga.isTerminationConditionMet(population) == false) {
			// Print fittest individual from population
			// 打印种群中最合适的个体
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
