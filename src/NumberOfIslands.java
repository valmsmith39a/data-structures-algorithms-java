class NumberOfIslands {
    
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1); 
    }      

    int getNumberOfIslands(char[][] grid) {
        // no grid or empty grid 
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int numOfIslands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++numOfIslands;
                    dfs(grid, r, c);
                }
            }
        }
        return numOfIslands;
    }

    public static void main(String args[]) {
        char[][] grid1 = new char[][]{{}};
        char[] row1 = new char[] { '1', '1', '1', '1', '0' };
        char[] row2 = new char[] { '1', '1', '0', '1', '0' };
        char[] row3 = new char[] { '1', '1', '0', '0', '0' };
        char[] row4 = new char[] { '0', '0', '0', '0', '0' };
        grid1 = new char[][] { row1, row2, row3, row4 }; 

        System.out.println("Number of Islands");
        System.out.println("Grid 1: ");

        for (int i = 0; i < 4; i++) {
            System.out.println(grid1[i]); 
        }

        NumberOfIslands numberOfIslands = new NumberOfIslands(); 
        int totalIslands = numberOfIslands.getNumberOfIslands(grid1); 
        System.out.println("Total number of islands is: " + totalIslands); 
        
        char[][] grid2 = new char[][]{{}};
        char[] grid2row1 = new char[] { '1', '1', '0', '0', '0' };
        char[] grid2row2 = new char[] { '1', '1', '0', '0', '0' };
        char[] grid2row3 = new char[] { '0', '0', '1', '0', '0' };
        char[] grid2row4 = new char[] { '0', '0', '0', '1', '1' };
        grid2 = new char[][] { grid2row1, grid2row2, grid2row3, grid2row4 }; 

        System.out.println("Number of Islands");
        System.out.println("Grid 2: ");

        for (int i = 0; i < 4; i++) {
            System.out.println(grid2[i]); 
        }          
        
        int totalIslandsGrid2 = numberOfIslands.getNumberOfIslands(grid2); 
        System.out.println("Total number of islands is: " + totalIslandsGrid2); 
    }   
}

