package A;

public enum Direction {
    LEFT,
    RIGHT;

    public static Direction[] generateRecruits() {
        Direction[] recruits = new Direction[50];
        for(int i = 0; i < recruits.length; ++i) {
            recruits[i] = Math.random() < 0.5 ? LEFT : RIGHT;
        }
        return recruits;
    }


    public static int checkIfLineLooksAtSameDirection(Direction[] recruits) {
       int counter = 0;
       for(int i = 0; i < recruits.length - 1; ++i) {
           if(recruits[i] != recruits[i + 1]) {
               ++counter;
           }
       }
       return counter;
    }

}
