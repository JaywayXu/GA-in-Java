package chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * A population is an abstraction of a collection of individuals. The population
 * class is generally used to perform group-level operations on its individuals,
 * such as finding the strongest individuals, collecting stats on the population
 * as a whole, and selecting individuals to mutate or crossover.
 * 
 * @author bkanber
 *
 */

/**
 * 种群是个体集合的抽象，种群类通常用于对其个体进行群体级操作，例如找到最强的个体，收集整个种群的统计数据，以及选择个体进行变异或交叉。
 * 
 * @author cloud
 *
 */

public class Population {
	private Individual population[]; // 保存由个体构成的一个数组
	private double populationFitness = -1; // 种群适应度值

	/**
	 * Initializes blank population of individuals 初始化空个体种群
	 * 
	 * @param populationSize
	 *            种群大小 The number of individuals in the population 种群中包含个体数
	 */
	public Population(int populationSize) {
		// Initial population
		this.population = new Individual[populationSize];
	}

	/**
	 * Initializes population of individuals 初始化种群
	 * 
	 * @param populationSize
	 *            种群大小 The number of individuals in the population 种群中个体数量
	 * @param chromosomeLength
	 *            染色体长度 The size of each individual's chromosome 个体染色体长度
	 */
	public Population(int populationSize, int chromosomeLength) {
		// Initialize the population as an array of individuals
		// 初始化种群作为个体数组
		this.population = new Individual[populationSize];

		// Create each individual in turn
		// 依次创建每个个体
		for (int individualCount = 0; individualCount < populationSize; individualCount++) {
			// Create an individual, initializing its chromosome to the given
			// length
			Individual individual = new Individual(chromosomeLength);
			// Add individual to population
			this.population[individualCount] = individual;
		}
	}

	/**
	 * Get individuals from the population 获取种群中所有个体
	 * 
	 * @return individuals Individuals in population
	 */
	public Individual[] getIndividuals() {
		return this.population;
	}

	/**
	 * Find an individual in the population by its fitness 根据适应度值从种群中找到一个个体
	 * 
	 * This method lets you select an individual in order of its fitness. This can
	 * be used to find the single strongest individual (eg, if you're testing for a
	 * solution), but it can also be used to find weak individuals (if you're
	 * looking to cull the population) or some of the strongest individuals (if
	 * you're using "elitism"). 此方法允许您按照适应度值顺序选择个体。这可以用来找到单个最强的个体(例如，如果你正在测试一个解决方案),
	 * 它也可以用来找到弱个体(如果你想要删除个体)或一些最强大的个体 (如果你正在使用"精英主义")。
	 * 
	 * @param offset
	 *            根据适应度函数进行排序得到索引，其中0表示适应度最高的解，索引种群个体个数-1表示最弱的解 The offset of the
	 *            individual you want, sorted by fitness. 0 is the strongest,
	 *            population.length - 1 is the weakest.
	 * @return individual Individual at offset 索引处个体
	 */
	public Individual getFittest(int offset) {
		// Order population by fitness
		// 根据适应度值对种群进行排序
		// 对种群population按照个体Individual的属性进行排序
		Arrays.sort(this.population, new Comparator<Individual>() {
			@Override
			public int compare(Individual o1, Individual o2) {
				if (o1.getFitness() > o2.getFitness()) {
					return -1;
				} else if (o1.getFitness() < o2.getFitness()) {
					return 1;
				}
				return 0;
			}
		});

		// Return the fittest individual
		//返回适应度值排序为offset序号的个体
		return this.population[offset];
	}

	/**
	 * Set population's group fitness
	 * 
	 * @param fitness
	 * 			     计算种群的全体适应度
	 *            The population's total fitness
	 */
	public void setPopulationFitness(double fitness) {
		this.populationFitness = fitness;
	}

	/**
	 * Get population's group fitness
	 * 获取种群整体适应值
	 * @return populationFitness The population's total fitness
	 */
	public double getPopulationFitness() {
		return this.populationFitness;
	}

	/**
	 * Get population's size
	 * 获取种群大小
	 * @return size The population's size
	 */
	public int size() {
		return this.population.length;
	}

	/**
	 * Set individual at offset
	 * 设置种群索引处个体
	 * @param individual
	 * 个体
	 * @param offset 
	 * 索引值
	 * @return individual
	 * 个体
	 */
	public Individual setIndividual(int offset, Individual individual) {
		return population[offset] = individual;
	}

	/**
	 * Get individual at offset
	 * 返回种群索引处个体
	 * @param offset
	 * 所以
	 * @return individual
	 * 个体
	 */
	public Individual getIndividual(int offset) {
		return population[offset];
	}

	/**
	 * Shuffles the population in-place
	 * 打乱种群中个体
	 * 
	 * @param void
	 * @return void
	 */
	
	/**
	 * java Random.nextInt()方法
	 * public int nextInt(int n)
	 * 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
	 * @author cloud
	 */
	public void shuffle() {
		Random rnd = new Random();
		for (int i = population.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			Individual a = population[index];
			population[index] = population[i];
			population[i] = a;
		}
	}
}