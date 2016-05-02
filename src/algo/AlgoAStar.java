package algo;

import java.util.ArrayList;

import environment.Cell;
import environment.Entrepot;
import environment.TypeCell;
import gui.EnvironmentGui;

/** this class propose an implementation of the a star algorithm */
public class AlgoAStar {

	/** nodes to be evaluated*/
	ArrayList<Cell> freeNodes;
	/** evaluated nodes*/
	ArrayList<Cell> closedNodes;

	/** Start Cell*/
	Cell start;
	/** Goal Cell*/
	Cell goal;

	/** graphe / map of the nodes*/
	Entrepot ent;
	/** gui*/
	EnvironmentGui gui;

	/**initialize the environment (100 x 100 with a density of container +- 20%) */
	AlgoAStar()
	{
		ent = new Entrepot(100, 100, 0.2, this);
		gui = new EnvironmentGui(ent);
		reCompute();
	}

	/** a* algorithm to find the best path between two states 
	 * @param _start initial state
	 * @param _goal final state*/
	ArrayList<Cell> algoASTAR(Cell _start, Cell _goal)
	{
		start = _start;
		goal = _goal;
		// list of visited nodes
		closedNodes = new ArrayList<Cell>();
		// list of nodes to evaluate
		freeNodes = new ArrayList<Cell>();
		freeNodes.add(start);
		// no cost to go from start to start
		// TODO: g(start) <- 0
		// TODO: h(start) <- evaluation(start)
		// TODO: f(start) <- h(start)
		// while there is still a node to evaluate
		while(!freeNodes.isEmpty())
		{
			// choose the node having a F minimal
			Cell n = chooseBestNode();
			// stop if the node is the goal
			if (isGoal(n)) return rebuildPath(n);
			// TODO: freeNodes <- freeNodes - {n}
			// TODO: closedNodes <- closedNodes U {n}
			// construct the list of neighbourgs
			ArrayList<Cell> nextDoorNeighbours  = neighbours(n);
			for(Cell ndn:nextDoorNeighbours)
			{
				// if the neighbour has been visited, do not reevaluate it
				if (closedNodes.contains(ndn))
					continue;
				// cost to reach the neighbour is the cost to reach n + cost from n to the neighbourg
				//TODO: int cost = ...
				boolean bestCost = false;
				// if the neighbour has not been evaluated
				if (!freeNodes.contains(ndn))
				{
					// TODO: freeNodes <- freeNodes U {ndn}
					// TODO: h(ndn) -> evaluation(ndn)
					bestCost = true;
				}
				else
					// if the neighbour has been evaluated to a more important cost, change its evaluation
					if (cost < ndn.getG())
						bestCost = true;
				if(bestCost)
				{
					ndn.setParent(n);
					//TODO : g(ndn) <- cost
					//TODO : f(ndn) <- g(ndn) + h(ndn)
				}
			}
		}
		return null;
	}

	/** return the path from start to the node n*/
	ArrayList<Cell> rebuildPath(Cell n)
	{
		if (n.getParent()!=null)
		{
			ArrayList<Cell> p = rebuildPath(n.getParent());
			n.setVisited(true);
			p.add(n);
			return p;
		}
		else
			return (new ArrayList<Cell>());
	}

	/** algo called to (re)launch a star algo*/
	public void reCompute()
	{
		ArrayList<Cell>  solution = algoASTAR(ent.getStart(), ent.getGoal());
		ent.setSolution(solution);
		if (solution==null) 
			System.out.println("solution IMPOSSIBLE");
		
		gui.repaint();
	}
	

	/** return the estimation of the distance from c to the goal*/
	int evaluation(Cell c)
	{		
		// TODO: cf cours : sur Terre : 10* distance vol d'oiseau entre but(goal) et c
	}

	/** return the free node having the minimal f*/
	Cell chooseBestNode()
	{
		//TODO...
	}

	/** return weither n is a goal or not */
	boolean isGoal(Cell n)
	{
		return (n.getX() == goal.getX() && n.getY() == goal.getY());
	}

	/** return the neighbouring of a node n; a diagonal avoid the containers */
	ArrayList<Cell> neighbours(Cell n)
	{
		// TODO: (en reponse au 3e cas)
	}

	/** return the neighbouring of a node n*/
	ArrayList<Cell> neighboursDiag(Cell n)
	{
		// TODO: (en reponse au 1er cas)
	}

	/** return the neighbouring of a node n without permission to go in diagonal*/
	ArrayList<Cell> neighbours4(Cell n)
	{
		// TODO: (en reponse au 2e cas)

	}

	/** return the cost from n to c : 10 for a longitudinal move, 14 (squareroot(2)*10) for a diagonal move */
	int costBetween(Cell n, Cell c)
	{	
		//TODO : sur terre, deplacement horizontal ou vertical = 10; en diagonale = 14
	}


	public static void main(String []args)
	{
		new AlgoAStar();

	}
}
