/**
 * P5430
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String order = br.readLine();
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            String temp = br.readLine();
            temp = temp.substring(1, temp.length() - 1);

            String[] arr = temp.isEmpty() ? new String[0] : temp.split(",");
            for (String s : arr) {
                deque.offer(Integer.parseInt(s));
            }
            boolean pollOrder = true;
            boolean error = false;
            F: for (int i = 0; i < order.length(); i++) {
                switch (order.charAt(i)) {
                    case 'R':
                        pollOrder = !pollOrder;
                        break;
                    case 'D':
                        if (deque.isEmpty()) {
                            error = true;
                            break F;
                        }
                        if (pollOrder) {
                            deque.pollFirst();
                        } else {
                            deque.pollLast();
                        }
                        break;
                    default:
                        break;
                }
            }
            if (error) {
                bw.write("error");
            } else {
                int size = deque.size();
                bw.write("[");
                if (!pollOrder) {
                    for (int i = 0; i < size - 1; i++) {
                        bw.write(deque.pollLast() + ",");
                    }
                    if (size > 0) {
                        bw.write(String.valueOf(deque.pollLast()));
                    }
                } else {
                    for (int i = 0; i < size - 1; i++) {
                        bw.write(deque.poll() + ",");
                    }
                    if (size > 0) {
                        bw.write(String.valueOf(deque.poll()));
                    }
                }
                bw.write("]");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}