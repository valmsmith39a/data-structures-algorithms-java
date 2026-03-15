import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.lang.Math;

public class RouterNetwork {
	private HashMap<String, Router> routerLookup = new HashMap<>();
	
	public RouterNetwork(Router[] routers) {
		for (Router router : routers) {
			addRouter(router.name, router.coord);
		}
	}
	
	public static class Router {
		public String name;
		public int[] coord;
		public LinkedList<Router> adjacent = new LinkedList<>();
		
		public Router (String name, int[] coord) {
			this.name = name;
			this.coord = coord;
		}
	}
	
	public Router getRouter(String name) {
		return routerLookup.get(name);
	}
	
	public void addRouter(String name, int[] coord) {
		Router router = new Router(name, coord);
		routerLookup.put(name, router);
	}

	public void addConnections(String source, String destination) {
		Router sourceRouter = getRouter(source);
		Router destinationRouter = getRouter(destination);
		sourceRouter.adjacent.add(destinationRouter);
	}

	// for each router, populate child routers	
	public void buildNetwork (Router[] routers) {
		for (int i = 0; i < routers.length; i++) {
			for (int j = 0; j < routers.length; j++) {
				if (i == j) {
					continue;
				}
				Router router1 = routers[i];
				Router router2 = routers[j];
				double distance = getDistanceBetweenRouters(router1, router2);
				if (distance < 10) {
					addConnections(routers[i].name, routers[j].name);
				}
			}
		}
	}	

	public static double getDistanceBetweenRouters(Router a, Router b) {
		int[] coordA = a.coord;
		int[] coordB = b.coord;
		return Math.sqrt(Math.pow(coordB[0] - coordA[0], 2) + Math.pow(coordB[1] - coordA[1], 2));
	}

	public boolean hasPath(String router1, String router2) {
		Router routerSource = getRouter(router1);
		Router routerDestination = getRouter(router2);
		// Depth-first Search 
		HashSet<String> visited = new HashSet<>();
		return hasPath(routerSource, routerDestination, visited);
	}

	public boolean hasPath(Router sourceRouter, Router destinationRouter, HashSet<String> visited) {
		if (visited.contains(sourceRouter.name)) {
			return false;
		}
		if(sourceRouter == destinationRouter) {
			return true;
		}	
		visited.add(sourceRouter.name);
		for (Router router : sourceRouter.adjacent) {
			if (hasPath(router, destinationRouter, visited)) {
				return true;
			};
		}
		return false;
	}
	
	public static void main(String[] args) {
		// routers only have range of up to 10 meters 
		Router[] routers = new Router[] { new Router("A", new int[] { 1, 1 }), new Router("B", new int[] { 5, 5 }), new Router("C", new int[] { 12, 12 }), new Router("D", new int[] { 99, 99 }) };
		RouterNetwork routerNetwork = new RouterNetwork(routers);
		routerNetwork.buildNetwork(routers);

		System.out.println("router lookup is: " + routerNetwork.routerLookup);
		System.out.println("children of A is: " + routerNetwork.routerLookup.get("A").adjacent);
		System.out.println("children of B is: " + routerNetwork.routerLookup.get("B").adjacent);
		System.out.println("Route between A and B? " +  routerNetwork.hasPath("A", "B"));
		System.out.println("Route between A and B? " +  routerNetwork.hasPath("A", "C"));
		System.out.println("Route between A and B? " +  routerNetwork.hasPath("A", "D"));
	}
}
