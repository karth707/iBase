package com.iBase.web.sharing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.helpers.collection.IteratorUtil;

public class FriendGraph {

	private static enum RelTypes implements RelationshipType
	{
	    FRIEND_OF
	}
	private static final String USER_ID = "userId";
	
	protected final static Log logger = LogFactory.getLog(FriendGraph.class);
	static Relationship relationship;
	private static ExecutionEngine engine;
	private Index<Node> nodeIndex;
	private GraphDatabaseService graphDb;
	
	public FriendGraph(){
		graphDb = GraphInstance.getInstance().getGraph();
		try ( Transaction tx = graphDb.beginTx() ){
			nodeIndex = graphDb.index().forNodes("nodes");
			tx.success();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		engine = new ExecutionEngine(graphDb);
	}
	
	public boolean addNode(String userId){
		
		Node node = null;
		try ( Transaction tx = graphDb.beginTx() ){
			node = nodeIndex.get(USER_ID, userId).getSingle();
			if(node==null){
				node = graphDb.createNode();
				node.setProperty(USER_ID, userId);
				nodeIndex.add(node, USER_ID, userId);
				logger.info("successfully added "+ userId+ " to graph!");
				tx.success();
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Could not add node " + userId);
		}
		return false;
	}
	
	private Node getNode(String userId){
		Node node = null;
		try ( Transaction tx = graphDb.beginTx() ){
			node = nodeIndex.get(USER_ID, userId).getSingle();
			tx.success();
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("Could not get node " + userId);
		}
		return node;
	}
	
	public int addAsFriends(String userId1, String userId2){
		
		//1=User does not exist; 2=added as friends; 
		//3=already friends; 0=error!;

		Node user1 = getNode(userId1);
		Node user2 = getNode(userId2);
		
		logger.info(user1+"-->"+user2);
		
		if(user2==null || user1==null){
			return 1;
		}
		
		
		try ( Transaction tx = graphDb.beginTx() ){
			if (!isConnected(user1, user2)) {
				relationship = user1.createRelationshipTo(user2,RelTypes.FRIEND_OF);
				tx.success();
				return 2;
			}else{
				return 3;
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	
	public int deleteFriend(String userId1, String userId2){
	
		//1=User does not exist; 2=deleted;
		//3=not friends; 0=error!;
		
		Node user1 = getNode(userId1);
		Node user2 = getNode(userId2);
		
		if(user2==null || user1==null){
			return 1;
		}
		if(isConnected(user1, user2)){
			deleteEdge(user1, user2);
			return 2;
		}else{
			return 3;
		}
	}
	
	private void deleteEdge(Node node1, Node node2) {
		String node1Key = node1.getProperty(USER_ID).toString();
		String node2Key = node2.getProperty(USER_ID).toString();
		
		String query = "start node1=node:nodes(userId = '" + node1Key + "'), "
				+ "node2=node:nodes(userId = '" + node2Key + "') "
				+ "match node1-[r:FRIEND_OF]->node2 " + "delete r";
		
		ExecutionResult result = engine.execute(query);
		logger.info("deleted edge between: "+ node1Key
				+ " and " + node2Key + "; result: "+ result.toString());
	}

	public List<String> getAllFriends(String userId){
		
		ExecutionResult result0 = engine
				.execute("start node=node:nodes(userId = '" + userId + "') "
						+ "match node-[:FRIEND_OF]->deg0 " + "return deg0");

		List<String> deg0Nodes = new ArrayList<String>();
		Iterator<Node> n_column = result0.columnAs("deg0");
		
			for (Node node : IteratorUtil.asIterable(n_column)) {
				String nodeResult = null;
				try ( Transaction tx = graphDb.beginTx() ){
					nodeResult = node.getProperty(USER_ID).toString();
					tx.success();
				}
				deg0Nodes.add(nodeResult);
			}
		return deg0Nodes;
	}
	
	private  boolean isConnected(Node node1, Node node2) {
		String node1Key = null;
		String node2Key = null;
		try ( Transaction tx = graphDb.beginTx() ){
			node1Key = node1.getProperty(USER_ID).toString();
			node2Key = node2.getProperty(USER_ID).toString();
			tx.success();
		}
		
		String query = "start node1=node:nodes(userId = '" + node1Key + "'), "
				+ "node2=node:nodes(userId = '" + node2Key + "') "
				+ "match node1-[r:FRIEND_OF]->node2 " + "return r";

		ExecutionResult result = engine.execute(query);

		Iterator<Node> rcolumn = result.columnAs("r");
		if (rcolumn.hasNext()) {
			// edge exists!
			return true;
		} else {
			return false;
		}
	}
		
	
	public static void main(String[] args){
		FriendGraph f = new FriendGraph();
		f.addNode("kartheek");
		f.addNode("apoorva");
		f.addNode("siddu");
		System.out.println(f.addAsFriends("kartheek", "apoorva"));
		System.out.println(f.addAsFriends("kartheek", "siddu"));
		System.out.println(f.addAsFriends("apoorva", "kartheek"));
		System.out.println("error:"+f.addAsFriends("aaa", "kartheek"));
		System.out.println(f.addAsFriends("siddu", "apoorva"));
		System.out.println(f.getAllFriends("kartheek"));
		System.out.println(f.getAllFriends("apoorva"));
		System.out.println(f.getAllFriends("siddu"));
		System.out.println(f.deleteFriend("kartheek", "siddu"));
		System.out.println(f.getAllFriends("kartheek"));
	}
}
