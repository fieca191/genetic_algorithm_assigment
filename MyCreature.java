import cosc343.assig2.Creature;
import java.util.Random;


/**
* The MyCreate extends the cosc343 assignment 2 Creature.  Here you implement
* creatures chromosome and the agent function that maps creature percepts to
* actions.  
*
* @author  
* @version 1.0
* @since   2017-04-05 
*/
public class MyCreature extends Creature {

    // Random number generator
    Random rand = new Random();
    int chromosome[];
    int fitness = 0;
   

  /* Empty constructor - might be a good idea here to put the code that 
   initialises the chromosome to some random state   
  
   Input: numPercept - number of percepts that creature will be receiving
          numAction - number of action output vector that creature will need
                      to produce on every turn
  */
    public MyCreature(int numPercepts, int numActions) {

        chromosome = new int[numPercepts * numActions];
        //make an array full of random integers
        //to act as a chromosome
        for(int i = 0; i < chromosome.length; i++){
            chromosome[i] = rand.nextInt();
        }


  }
  
  /* This function must be overridden by MyCreature, because it implements
     the AgentFunction which controls creature behavoiur.  This behaviour
     should be governed by a model (that you need to come up with) that is
     parameterise by the chromosome.  
  
     Input: percepts - an array of percepts
            numPercepts - the size of the array of percepts depend on the percept
                          chosen
            numExpectedAction - this number tells you what the expected size
                                of the returned array of percepts should bes
     Returns: an array of actions 
  */
  @Override
  public float[] AgentFunction(int[] percepts, int numPercepts, int numExpectedActions) {
      
      // This is where your chromosome gives rise to the model that maps
      // percepts to actions.  This function governs your creature's behaviour.
      // You need to figure out what model you want to use, and how you're going
      // to encode its parameters in a chromosome.

      float actions[] = new float[numExpectedActions];
      int count = 0;
      
      for(int i = 0; i < numExpectedActions; i++){//first lot of attributews

          for(int j = 0; j < numPercepts; j++){
              actions[i] = percepts[j] + chromosome[j + count];
          }
          count += numPercepts;
      }
      return actions;
      
  }

    public int[] getChromosome(){
	return chromosome;
    }
    public void setChromosome(int[] chromo){

        chromosome = chromo;
 
    }
    public int getFitness(){
        return fitness;
    }
    public void setFitness(int fit){
        fitness = fit;
    }

    /*
      The creation of the next generation
     */
    public int[] baby(int[] creature1,int[] creature2){

	int chromosomeLength = creature1.length;
        int[] baby = new int[chromosomeLength];
	int[] newChromosome = new int[chromosomeLength];
	//int[] newChromosome2 = new int[chromosomeLength];
        double mutationChance = rand.nextDouble();
        int mutation = (int) mutationChance * 10;


	int crossingOverPoint = rand.nextInt(chromosomeLength);
        //ensure in range for chromosome

	/*
	  Filling the first half of the chromosomes
	 */
	for(int i = 0; i < crossingOverPoint; i++){
	    newChromosome[i] = creature1[i];
	    //newChromosome2[i] = creature2[i];
	}

	/*
	  Following the second half of the chromosomes
	 */
	for(int i = crossingOverPoint; i < chromosomeLength; i++){
	    newChromosome[i] = creature2[i];
	    //newChromosome2[i] = creature1[i];
	}

        int randSelect = rand.nextInt(1);
        
        /*
          Introducing mutation
        */
        for(int i = 0; i < chromosomeLength; i++){

            if(mutationChance <= 0.3){
                if(randSelect == 0){//mutate chomosome 1
                    newChromosome1[i] += mutation;
                }// else{//mutate chromosome 2
                //     newChromosome2[i] += mutation;
                // }
            }
        }

       
	return newChromosome;
        // if(randSelect == 0){

            
        //     return newChromosome1;
        // }else{
        //     return newChromosome2;
        // }


    }
  
}
