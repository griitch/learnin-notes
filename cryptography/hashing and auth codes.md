# Hash functions

### Security properties

- preimage resistance : given an element in the range of a hash function, it should be computationally infeasible to find an input that maps to that element, just a fancy way to say a one-way func
- second preimage resistance : the property of a hash function that it is computationally infeasible to find any second input that has the same output as a given input, AKA collision resistant

### Merkle-Damgard construct

- uses FC a pseudo-random, one way compression function
- FC's param have a fixed size
- Uses V, an IV
- The message M is divided into multiple sub-messages of fixed size, if the message is smaller, it's padded with zeroes
- H0 = FC(v,m0), H1 = FC(H0,m1), Hi = FC(Hi-1, mi) and so on
- HN is the value of the hash

- md5 and sha256 are both merkle-damgard constructs
- [Mike pound the goat's video about sha1](https://www.youtube.com/watch?v=DMtFhACPnTY)

### Hashing using a block cypher

- The idea is to use a block cypher as a compression function :
  - it's a function on a block
  - it's a one-way func if we don't know the the key
  - it's pseudo-random
- first try : FC(h,m) = enc(h,m)
  - can be inverted if we knew some clear message blocks
- Davies-myer function : FC(H,m) = enc(h,m) xor h
  - enc(h,m) and h have the same size
  - is no longer invertible

## TODO : look into attacks (birthday attack in particular)

# MAC : message authentication code

- goal is to preserve the integrity of the message, using a secret key K
- the receiver can verify the integrity of M using C if he knew k
- C does not guarantee the confidentiality of M, that is, it does guarantee that the message was not seen by eve

## Mac implementation

To implement a mac system we need 3 functions

- keygen(some security param (whatever that is, ask prof))
- macGen(k,m) returns C
- macVerify(k,m,C) : returns macGen(k,m) == c
- all the functions need to be polynomial in time, but macGen without knowing the should be not be polynomial

## H-mac : hash mac

- create a MAC using a hash function h
- naive approach macGen(k,m) = h(k+m) :

  - hash the concatenation of k and m, the problem with this is that many hash functions are vulnerable to [length extension attacks](https://en.wikipedia.org/wiki/Length_extension_attack)
  - length extension attack : knowing h(m), we can find h(m+n) without knowing m
  - so if we knew mac(k,m) == h(k+m), we will be able to authenticate m+n for any n without knowing k or m
  - merkle-damgard construct is vulnerable to length extension

- length extension free approach : **mac(k,m)=h(k + h(k+m))**

## Encryption and auth

- An encryption does not guarantee the integrity of communication
- for example, for AES, every binary string is a valid cipher, and can be decrypted
- to guarantee the integrity of an encrypted message, we use an authentication encryption that involves a mac
- we use the same key for decryption and for authentication
- 2 approaches : mac then encrypt, or encrypt then mac
- encrypt then mac is preferred because mac-then-enc only guarantees the integrity of the message and not of the ciphertext
