import java.util.Random;

public class Main {
    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        for (int i = 0; i < opCount; i++) {
            stack.push(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 1000000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + " s");
        LinkedListStack<Integer> listStack = new LinkedListStack<>();
        double time2 = testStack(listStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + " s");
    }
}
