#include <iostream>
#include <algorithm>
using namespace std;

int wine[10001];
int cache[10001] = { 0 };
int wineCnt; //포도주 갯수 
int maxSum(void)

{
        cache[1] = wine[1];
        cache[2] = wine[1] + wine[2];
        if (wineCnt == 1)
               return cache[1];
        else if (wineCnt == 2)
               return cache[2];
        else
        {
               for (int i = 3; i <= wineCnt; i++)
                       cache[i] = max(cache[i - 1], max(cache[i - 2] + wine[i], cache[i - 3] + wine[i - 1] + wine[i]));
               return cache[wineCnt];
        }
}
int main(void)
{
        cin >> wineCnt;
        for (int i = 1; i <= wineCnt; i++)
               cin >> wine[i];
        cout << maxSum() << endl;
        return 0;
}