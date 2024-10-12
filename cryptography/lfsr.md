# Efficiency of an attack

Evaluated by comparing its performance vs a brute force attack

- a brute force attack on one time pads for keys of size L is O(2^l) in time and O(L) in memory
- an efficient attack needs better complexity than that, and needs to have a high proba to succeed
- to say that an encryption algorithm has `x bytes of security` means that its best known attack needs 2^x operations
- the size of the key L is called `parameter of security`

# time memory tradeoff attack on cipher streams

## Birthday paradox reminder

in a group of 23 ppl, the probability to find 2 ppl with the same birthday is around 51%.  
the proof is : p = 1 - Probability(every person in the group has a distinct birthday) = 1 - [product of ((365 - i)/365 for i in 0:23)].

There is a proof that uses series expansion (dev limite) that says that : in a set of N elements, the number of drawings (tirage) to get the same element twice with probability p is : sqrt(2\*N\*ln(1/(1-p)))

## The attack

similar to a dictionnary attack, precalcluate a bunch of internal states, and look for collisions (i fast forwarded this part need to revisit it)

# LFSRS

lfsr of size l :

- a register of size l : s0,s1,...sl-1
- a binary word of size l : c
- the output of each iteration is s0
- the last bit is computed using S_last_bit_of_next_state = c1.s_l-1 xor c2.s_l-2 ... xor cl.s_0
- then we shift the register to the left

the series produced by a lfsr are periodic, if c_l == 1.  
if the period is 2^l - 1, the lfsr is said to be of maximal length, and each state will appear only once.

A lfsr can be defined by his retroaction polynomial func and his initial state.  
polynome de retroaction is sum( C_i \* x^i ) for i in range(l)
