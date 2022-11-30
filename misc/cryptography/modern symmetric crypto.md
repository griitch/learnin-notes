# Stream cipher - chiffrement par flot

- the idea is to take a small key, and <<stretch>> it into a large pseudo random stream of bits
- uses f, as pseudo-random function capable of generating a random stream of bits of arbitrary length
- input : key K and public init vector v
- from what i've understood so far, K is small in size, K and V are fed into F, the result is a long stream of bits (the same size as M)
- it's practical because, opposed to OTP (one time pad), the key can be small (in otp the key needs to be as big as the message)
- To encrypt M, XOR it with the resulting stream of bits.
- functions :

  - gen(i) : generate key of i bits
  - enc(k,M) : chooses a random init vector V, calculates e = f(k,v) XOR M and returns C=(v,e)
  - deco(k,M) : calculates and returns M = e XOR f(k,v)

- examples :
  - rc4 : broken, used in tls and wep
  - salsa20 and chacha (Mike Pound made a vid about it on computerphile)\
  - A5/1 : used in gsm com

# A5/1 as a example

- Uses LFSR (Linear feedback shift register - registre a decalagea retroaction lineaire)

  - [Mike pound vid about lfsr](https://www.youtube.com/watch?v=Ks1pw1X22y4)
  - The register is a binary string, the state of the register of the value of all its bits
  - The next output is the ,least significant bit of the register, the state is then updated by shifting all the remaining bits one position to the right, the most significant bit is then replaced with a linear combination of the bits of the **_previous_** .(always the same combination, in terms of order of the bits, for example, xor the 4th and 6th bit)
  - to program it, calculate the most significant bit first, shift to the right one position and then add the last bit
  - each output is pseudo random
  - the register will eventually go back to its initial state, for a register of n bits, the maximum period (rounds before the register goes to state 0) is 2^n - 1

- A5/1 combines 3 L SFR's of different sizes, each containing a special bit (called the clocking bit), only the LSFR's with the clocking bits that match the majority bit shift, at least 2 registers will shift (all 3 of them can shift if they all had the same value)
  - maj(x1,x2,x3) returns 1 if at least 2 of the bits are ones, 0 instead
- At each step, the 3 right most bits are XORed, and the result is the next bit of the keystream
- the registers initial state is determined by the key : the registers are first set to zeros, then for `i` in [0..64[, R[0] = K[`i`] xor R[0] then shift R for each R in registers, then for `i` in [0..22[: do the same but with the bits of a 22 bit IV.
- Usage in GSM : GSM transmission is organized as bursts, each burst

# BLOCK Cyphers

- We define a function that can encrypt a fixed size block of bits
- it must ensure diffusion and confusion :

  - diffusion :

    - hide the relation between M and C
    - the statistical biases in M should not appear in C
    - change 1 bit in M, all of C changes

  - confusion :
    - hide the relation between K and C
    - each symbol of C depends on multiple parts of K
    - C does not give any info on K even if M was known
    - change one bit of K, all of C changes.

- split the messages into fixed sized blocks and encrypt each one of them
- there are many families of block ciphers, manly feistel networks and substitution/permutation networks
- the encryption is done by many iterations of a rounds function, plus d'iteration => chiffrement plus sur.
- the rounds function is inversible and the inverse is the decryption
- a new key is generated each round, we need a `key schedule` algorithm that generates k1 from k0, and k2 from k1 and so on.

## Resau de feistel

- uses F, a func that is not necessarily inversible
- in the beginning of round i, we divide the bloc into 2 parts Li and Ri (left and right)
- on pose **_Li+1 = Ri_** and **_Ri+1 = Li xor F(Li,Ri)_**
- pour inverser un tour, we do not need the inverse of F
- LiRi are found in Li+1 and Ri+1
- Ri = Li+1 and Li = Ri+1 xor F(Ki,Ri)

## Substitution/permutation boxes

- use one permutation function and at least one substitution function
- we start the round by xoring the key and the block
- the block is divided into binary words of predefined length
- the words are replaced by other words using the s-boxes
- the bits of the blocs are then permuted using tee p-box **_THE WORDS OF THE WHOLE BLOC NOT THE BINARY WORDS_**
- we re-xor the result with the round key Ki

## Cryptanalysis of block cyphers

### Differential analysis

- attaque a clairs choisis : chosen-plaintext attack : the analyst chooses some particular cyphers, the plain texts lui sont fournis
- choose many combinations of cyphers/plaintexts and try to deduce info about the key
- DES and AES are robust against it

### Linear analysis

- a chosen-plaintext attack as well but more efficient

# BLOC Cypher modes

- a naive method is encrypting the blocs one by one but there are other more subtle methods.

### Electronic code book

- encrypt each bloc independently of the other with the same key
- major drawback : the same block yields the same cipher
- advantage : can be parallelized because of the independence of blocs

### CBC Mode, cipher bloc chaining

- use an IV that is randomly chosen and public
- encryption : xor the IV and the first bloc before encrypting, and repeat for the second block using the first block as the IV, and on and on
- 2 identical blocs => different cypher
- hard to parallelize

### OFB output feedback

- use an IV that is randomly chosen and public
- we encrypt the IV as if it was a block of the message, enc(iV) = E0, we encrypt E0 to get E1, and E1 to get E2 and on and on
- for each bloc of the message Mi, xor Mi and Ei
