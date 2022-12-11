# 1

- l1 || r1

  - l1 = r0
  - r1 = ( l0 + F(l0,r0) ) % 2^32
  - l1 || r1 = r0 || ( l0 + F(K,r0) ) % 2^32

- l2 || r2 = r1 || (l1 + F(K,r1 )) % 2^32

# 2

- i guess no

# 3

- le modulo est implicit... because we are using 32 bit integers
- r0 = l1
- l0 = r1 - f(K,r0)

- r0 =
