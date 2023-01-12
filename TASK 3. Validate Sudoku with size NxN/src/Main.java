import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    List<List<Integer>> board = new ArrayList<>();
    board.add(Arrays.asList(7, 8, 4, 1, 5, 9, 3, 2, 6));
    board.add(Arrays.asList(5, 3, 9, 6, 7, 2, 8, 4, 1));
    board.add(Arrays.asList(6, 1, 2, 4, 3, 8, 7, 5, 9));
    board.add(Arrays.asList(9, 2, 8, 7, 1, 5, 4, 6, 3));
    board.add(Arrays.asList(3, 5, 7, 8, 4, 6, 1, 9, 2));
    board.add(Arrays.asList(4, 6, 1, 9, 2, 3, 5, 8, 7));
    board.add(Arrays.asList(8, 7, 6, 3, 9, 4, 2, 1, 5));
    board.add(Arrays.asList(2, 4, 3, 5, 6, 1, 9, 7, 8));
    board.add(Arrays.asList(1, 9, 5, 2, 8, 7, 6, 3, 4));

    if (isSudokuValid(board)) {
      System.out.println("Valid");
    } else {
      System.out.println("Invalid");
    }
  }


  static boolean isValidStructure(List<List<Integer>> board) {
    return board.size() > 1 && Math.sqrt(board.size()) % 1 == 0;
  }

  static boolean isSudokuValid(List<List<Integer>> board) {
    // check data structure dimension: NxN where N > 1 and √N == integer
    if (!isValidStructure(board)) {
      return false;
    }

    int N = board.size();
    int smallSquare = (int) Math.sqrt(N);
    Set<Integer> values = new HashSet<>();

    // check if all values in rows are unique.
    for (List<Integer> integers : board) {
      values.addAll(integers);
      if (values.size() != N) {
        return false;
      }
    }

    // check if all values in columns are unique.
    for (int i = 0; i < N; i++) {
      values.clear();
      for (List<Integer> integers : board) {
        values.add(integers.get(i));
      }
      if (values.size() != N) {
        return false;
      }
    }

    // check if all values in 'Small squares' are unique.
    for (int i = 0; i < N; i += smallSquare) {
      for (int j = 0; j < N; j += smallSquare) {
        values.clear();
        for (int k = 0; k < smallSquare; k++) {
          for (int m = 0; m < smallSquare; m++) {
            values.add(board.get(k + i).get(m + j));
          }
        }
        if (values.size() != N) {
          return false;
        }
      }
    }

    return true;
  }
}