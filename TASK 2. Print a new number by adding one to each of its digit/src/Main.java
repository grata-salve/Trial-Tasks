import java.util.Stack;

public class Main {

  public static void main(String[] args) {
    System.out.println(transform(998));
  }

  public static long transform(int number) {
    Stack<Integer> digits = new Stack<>();
    while (number > 0) {
      digits.push(number % 10);
      number = number / 10;
    }

    int result = 0;

    while (!digits.isEmpty()) {
      int num = digits.pop();
      if (num == 9)
        result = 100 * result + (num + 1);
      else
        result = 10 * result + (num + 1);
    }

    return result;
  }
}