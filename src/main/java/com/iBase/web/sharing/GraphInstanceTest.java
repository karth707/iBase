package com.iBase.web.sharing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.IteratorUtil;

public class GraphInstanceTest {

	private static enum RelTypes implements RelationshipType {
		KNOWS
	}

	GraphDatabaseService graphDb;
	Node firstNode;
	Node secondNode;
	Relationship relationship;
	private Index<Node> nodeIndex;

	GraphInstanceTest() {
		graphDb = GraphInstance.getInstance().getGraph();
		nodeIndex = graphDb.index().forNodes("nodes");
	}

	public void dostuff() {
		Transaction tx = null;
		try {
			tx = graphDb.beginTx();
			firstNode = graphDb.createNode();
			firstNode.setProperty("message", "Hello, ");
			nodeIndex.add(firstNode, "message", "Hello, ");
			secondNode = graphDb.createNode();
			secondNode.setProperty("message", "World!");
			nodeIndex.add(secondNode, "message", "World!");
			relationship = firstNode.createRelationshipTo(secondNode,
					RelTypes.KNOWS);
			relationship.setProperty("message", "brave Neo4j ");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			tx.success();
		}
	}

	public void print() {
		Transaction tx = null;
		try {
			tx = graphDb.beginTx();
			Node node = nodeIndex.get("message", "Hello, ").getSingle();
			if (node != null) {
				System.out.println("whoo!!");
			} else {
				System.out.println("NO!!");
			}
			System.out.print(firstNode.getProperty("message"));
			System.out.print(relationship.getProperty("message"));
			System.out.print(secondNode.getProperty("message"));
			System.out.println(isConnected(firstNode, secondNode));
			System.out.println(getAllFriends(firstNode.getProperty("message").toString()));
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			tx.success();
		}
	}

	private boolean isConnected(Node node1, Node node2) {

		String node1Key = node1.getProperty("message").toString();
		String node2Key = node2.getProperty("message").toString();

		String query = "start node1=node:nodes(message = '" + node1Key + "'), "
				+ "node2=node:nodes(message = '" + node2Key + "') "
				+ "match node1-[r:KNOWS]->node2 " + "return r";

		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result = engine.execute(query);

		Iterator<Node> rcolumn = result.columnAs("r");
		if (rcolumn.hasNext()) {
			// edge exists!
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private void deleteEdge(Node node1, Node node2) {
		String node1Key = node1.getProperty("message").toString();
		String node2Key = node2.getProperty("message").toString();

		String query = "start node1=node:nodes(message = '" + node1Key + "'), "
				+ "node2=node:nodes(message = '" + node2Key + "') "
				+ "match node1-[r:KNOWS]->node2 " + "delete r";

		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result = engine.execute(query);
		System.out.println((result.toString()));
	}

	public List<String> getAllFriends(String userId) {

		ExecutionEngine engine = new ExecutionEngine(graphDb);
		ExecutionResult result0 = engine
				.execute("start node=node:nodes(message = '" + userId + "') "
						+ "match node-[:KNOWS]->deg0 " + "return deg0");

		List<String> deg0Nodes = new ArrayList<String>();
		Iterator<Node> n_column = result0.columnAs("deg0");
		for (Node node : IteratorUtil.asIterable(n_column)) {
			String nodeResult = node.getProperty("message").toString();
			deg0Nodes.add(nodeResult);
		}
		return deg0Nodes;

	}

	public static void main(String[] args) {
		GraphInstanceTest g = new GraphInstanceTest();
		g.dostuff();
		g.print();
	}

}
