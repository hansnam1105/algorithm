import java.util.Arrays;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        // 인접한 전화번호끼리 비교
        for (int i = 0; i < phone_book.length - 1; i++) {
            // 현재 전화번호와 다음 전화번호를 비교
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
}
