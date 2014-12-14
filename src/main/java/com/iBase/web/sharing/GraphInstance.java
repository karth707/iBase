package com.iBase.web.sharing;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class GraphInstance {

	private static volatile GraphInstance instance = null;
	private static GraphDatabaseService graphDb;
	protected final Log logger = LogFactory.getLog(getClass());
	private String DB_PATH = "/Users/KartheekGanesh/Sources/iBaseGraphs/graphDB";
	
	private GraphInstance(){
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		logger.info("creating graph in:" + DB_PATH);
		registerShutdownHook( graphDb );
	}
	
	public static GraphInstance getInstance(){
		if(instance==null){
			instance = new GraphInstance();
			return instance;
		}
		return instance;
	}
	
	public GraphDatabaseService getGraph(){
		return graphDb;
	}
	
	private static void registerShutdownHook( final GraphDatabaseService graphDb )
	{
	    // Registers a shutdown hook for the Neo4j instance so that it
	    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
	    // running application).
	    Runtime.getRuntime().addShutdownHook( new Thread()
	    {
	        @Override
	        public void run()
	        {
	            graphDb.shutdown();
	        }
	    } );
	}
}
