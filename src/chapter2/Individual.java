package chapter2;

/**
 * An "Individual" represents a single candidate solution. The core piece of
 * information about an individual is its "chromosome", which is an encoding of
 * a possible solution to the problem at hand. A chromosome can be a string, an
 * array, a list, etc -- in this class, the chromosome is an integer array. 
 * 
 * An individual position in the chromosome is called a gene, and these are the
 * atomic pieces of the solution that can be manipulated or mutated. When the
 * chromosome is a string, as in this case, each character or set of characters
 * can be a gene.
 * 
 * An individual also has a "fitness" score; this is a number that represents
 * how good a solution to the problem this individual is. The meaning of the
 * fitness score will vary based on the problem at hand.
 * 
 * @author bkanber
 *
 */

/**
 * "Individual"个体代表单个的候选解决方案,关于个人的核心信息是它的“Chromosome染色体”，表示当前问题的可能解决方案的编码，
 * 染色体可以是字符串，数组，列表等 - 在这个类中，染色体是整数数组。
 * 
 * 染色体中的单个位置称为基因，这些是可被操作或突变的溶液的原子片段。 当染色体是一个字符串时，每个字符或一组字符可以被视为是一个基因。
 * 
 * 个体也有“适应度”分数; 代表这个个体作为问题的解决方案的好坏。 适应度函数的定义根据当前的问题而变化。
 * @author cloud
 *
 */


public class Individual {
	private int[] chromosome; //定义染色体
	private double fitness = -1; //定义适应度值
	
	/**
	 * Individual类代表一个候选解，主要负责存储和操作一条染色体。
	 * 请注意Individual类有两个构造方法：
	 * 一个构造方法接收一个整数(代表染色体的长度)，在初始化对象时将创建一条随机的染色体
	 * 一个构造方法接受一个整数数组，用它作为染色体
	 * @author cloud
	 *
	 */

	/**
	 * Initializes individual with specific chromosome
	 * 用特定染色体初始化个体
	 * @param chromosome 
	 * 			     染色体
	 *            The chromosome to give individual 赋给个体的染色体
	 */
	public Individual(int[] chromosome) {
		// Create individual chromosome 创建个体染色体
		this.chromosome = chromosome;
	}

	/**
	 * Initializes random individual.
	 * 初始化随机个体
	 * 
	 * This constructor assumes that the chromosome is made entirely of 0s and
	 * 1s, which may not always be the case, so make sure to modify as
	 * necessary. This constructor also assumes that a "random" chromosome means
	 * simply picking random zeroes and ones, which also may not be the case
	 * (for instance, in a traveling salesman problem, this would be an invalid
	 * solution).
	 * 这个构造函数假设染色体完全由0和1组成，这可能并非总是如此，
	 * 因此请务必根据需要进行修改。 这个构造函数还假设“随机”染色体意味着简单地选择随机0和1，
	 * 这也可能不是这种情况（例如，在旅行商问题中，这将是无效的解决方案）。
	 * 
	 * @param chromosomeLength
	 * 			      染色体长度
	 *            The length of the individuals chromosome   染色体个体长度
	 */
	public Individual(int chromosomeLength) {

		this.chromosome = new int[chromosomeLength];
		for (int gene = 0; gene < chromosomeLength; gene++) {
			if (0.5 < Math.random()) {
				this.setGene(gene, 1);
			} else {
				this.setGene(gene, 0);
			}
		}

	}

	/**
	 * Gets individual's chromosome
	 * 返回染色体个体
	 * 
	 * @return The individual's chromosome
	 */
	public int[] getChromosome() {
		return this.chromosome;
	}

	/**
	 * Gets individual's chromosome length
	 * 
	 * @return The individual's chromosome length
	 */
	public int getChromosomeLength() {
		return this.chromosome.length;
	}

	/**
	 * Set gene at offset
	 * offset用于传入位于染色体上的基因位置索引
	 * gene代表基因位上的值
	 * @param gene
	 * @param offset
	 * @return gene
	 */
	public void setGene(int offset, int gene) {
		this.chromosome[offset] = gene;
	}

	/**
	 * Get gene at offset
	 * 
	 * @param offset
	 * @return gene
	 */
	public int getGene(int offset) {
		return this.chromosome[offset];
	}

	/**
	 * Store individual's fitness
	 * 
	 * @param fitness
	 *            The individuals fitness
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Gets individual's fitness
	 * 
	 * @return The individual's fitness
	 */
	public double getFitness() {
		return this.fitness;
	}
	
	
	/**
	 * Display the chromosome as a string.
	 * 
	 * @return string representation of the chromosome
	 */
	public String toString() {
		String output = "";
		for (int gene = 0; gene < this.chromosome.length; gene++) {
			output += this.chromosome[gene];
		}
		return output;
	}
}
