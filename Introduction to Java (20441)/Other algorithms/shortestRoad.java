/**
     * This method gets two arrays that represent roads from A to B (both), 
     * and returns the shortest path that includes up to one pass between the roads.
     */
    public static int shortestRoad(int[] road1, int[] road2){
        int N = road1.length;
        int time = Integer.MAX_VALUE, tempTime = time, road1Time = 0, j = N - 1;
        for (int i = 0; i < N; i++){
            road1Time += road1[i];
        }
        tempTime = road1Time;
        time = tempTime;
        while (j != -1){
            tempTime -= road1[j];
            tempTime += road2[j];
            if (tempTime < time)
                time = tempTime; 
            j--;
        }
        j = 0;
        tempTime = road1Time;
        while (j != N){
            tempTime -= road1[j];
            tempTime += road2[j];
            if (tempTime < time)
                time = tempTime;
            j++;
        }
        return time;
    }
