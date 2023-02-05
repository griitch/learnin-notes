package misc;

public class Hanoi {

    public static void move(char a, char b)
    {
        System.out.printf("move disc from %c to %c\n",a,b);
    }

    public static void hanoi(int n,char f, char i, char t)
    {
        //n: number of discs
        //f: from
        //i: intermediate
        //t: target

        if(n==0)
            return;

        hanoi(n-1,f,t,i);
        move(f,t);
        hanoi(n-1,i,f,t);

    }
}
