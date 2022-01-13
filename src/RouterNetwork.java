import java.util.HashMap;
import java.util.HashSet;

public class RouterNetwork {
	
	public static class Router {
		private String name;
		private int[] coord;
		
		public Router (String name, int[] coord) {
			this.name = name;
			this.coord = coord;
		}
	}
	
	
	public HashMap<String, String[]> buildNetwork (Router[] routers) {
		HashMap<String, Integer[]> network = new HashMap<>();
		for (int i = 0; i < routers.length; i++) {
			Router[] childRouters = new Router[0];
			network.put(routers[i].name, 0);
			for (int j = 0; j < routers.length; j++) {
				if (i == j) {
					continue;
				}
				Router router1 = routers[i];
				Router router2 = routers[j];
				int distance = getDistanceBetweenRouters(router1, router2);
				if (distance < 10) {
					childRouters.add(router);
				}
			}
		}
	}	

	public boolean hasPath(String router1, String router2) {
		Router routerSource = getRouter(router1);
		Router routerDestination = getRouter(router2);
		// DFS 
		HashSet<String> visited = new HashSet<>();
		return hasPath(routerSource, routerDestination, visited);
	}

	public boolean hasPath(Router sourceRouter, Router destinationRouter, HashSet visited) {
		if (visited.contains(sourceRouter)) {
			return false;
		}
		if(sourceRouter == destinationRouter) {
			return true;
		}	
		visited.add(sourceRouter);
		for (Router router : source.children) {
			hasPath(router, destinationRouter, visited);
		}
	}
	
	public static void main(String[] args) {
		// routers only have range of up to 10 meters 
		Router[] routers = new Router[] { new Router("A", new int[] { 1, 1 }), new Router("B", new int[] { 5, 5 }), new Router("C", new int[] { 12, 12 }) };
		
		/**
		 * router A
		 * router B
		 * router C
		 */
		for (Router router : routers ) {
			System.out.println("router " + router.name);
		}
	}
}
