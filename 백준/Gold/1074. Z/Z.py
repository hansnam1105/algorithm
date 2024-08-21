N, r, c = map(int, input().split())

def Z(N, r, c):
    if N == 0:
        return 0
    return 2*(r%2)+(c%2) + 4*Z(N-1, int(r/2), int(c/2))

print(Z(N, r, c))