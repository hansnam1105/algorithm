import java.io.*;
import java.util.*;

public class Main {
	static final int MEMORY_SIZE = 100000;
	static boolean[] memory = new boolean[MEMORY_SIZE + 1];
	static Map<String, Integer> variables = new HashMap<>(); // 변수명 -> 시작 주소
    static Map<String, Integer> varSizes = new HashMap<>();  // 변수명 -> 할당 크기
    
    static class Interval {
        int start, end;

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
    
    static TreeSet<Interval> freeIntervals = new TreeSet<>((a, b) -> a.start - b.start);
    static {
        freeIntervals.add(new Interval(1, MEMORY_SIZE));
    }
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++) {
        	String command = br.readLine();
        	 if (command.contains("malloc")) {
                 handleMalloc(command);
             } else if (command.contains("free")) {
                 handleFree(command);
             } else if (command.contains("print")) {
                 handlePrint(command, bw);
             }
        }
        
        bw.flush();
        bw.close();
    }
    
    private static void handleMalloc(String command) {
        StringTokenizer st = new StringTokenizer(command, "=");
        String var = st.nextToken().trim();  // var

        String rightPart = st.nextToken().trim(); // malloc(size)크기;
        int startIdx = rightPart.indexOf('(');
        int endIdx = rightPart.indexOf(')');
        int size = Integer.parseInt(rightPart.substring(startIdx+1, endIdx));

     // 가장 앞에서부터 size를 수용할 수 있는 구간을 찾는다.
        Interval chosen = null;
        for (Interval interval : freeIntervals) {
            int length = interval.end - interval.start + 1;
            if (length >= size) {
                chosen = interval;
                break;
            }
        }

        if (chosen == null) {
            variables.put(var, 0);
            varSizes.put(var, 0);
            return;
        }

        freeIntervals.remove(chosen);
        int allocStart = chosen.start;
        int allocEnd = allocStart + size - 1;

        if (allocEnd < chosen.end) {
            freeIntervals.add(new Interval(allocEnd + 1, chosen.end));
        }

        variables.put(var, allocStart);
        varSizes.put(var, size);
    }

	
	private static void handleFree(String command) {
		int startIdx = command.indexOf('(');
        int endIdx = command.indexOf(')');
        String var = command.substring(startIdx+1, endIdx).trim();

        if (!variables.containsKey(var) || variables.get(var) == 0) {
            // 이미 0이거나 없는 변수
            return;
        }

        int start = variables.get(var);
        int size = varSizes.get(var);
        int end = start + size - 1;

        // 구간 삽입
        Interval newInterval = new Interval(start, end);
        // 인접 구간과 병합
        Interval floor = freeIntervals.floor(newInterval);
        Interval ceil = freeIntervals.ceiling(newInterval);

        // 왼쪽 병합
        if (floor != null && floor.end + 1 == newInterval.start) {
            newInterval.start = floor.start;
            newInterval.end = Math.max(newInterval.end, floor.end);
            freeIntervals.remove(floor);
        }

        // 오른쪽 병합
        if (ceil != null && newInterval.end + 1 == ceil.start) {
            newInterval.end = ceil.end;
            freeIntervals.remove(ceil);
        }

        freeIntervals.add(newInterval);

        variables.put(var, 0);
        varSizes.put(var, 0);
	}

	private static void handlePrint(String command, BufferedWriter bw) throws Exception {
		int startIdx = command.indexOf('(');
        int endIdx = command.indexOf(')');
        String var = command.substring(startIdx+1, endIdx).trim();
        
        bw.write(variables.getOrDefault(var, 0) + "\n");
		
	}
}
