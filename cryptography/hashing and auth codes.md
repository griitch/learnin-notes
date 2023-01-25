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

#### Birthday attack

- based on the birthday paradox : the proba that 2 people have the same birthday in a room is very high...
- It's probabilistically high to find 2 messages m1 and m2 such as h(m1) == h(m2)
- It's not like the case in which we already knew h(m1) and we wanted to find a collision

# MAC : message authentication code

- goal is to preserve the integrity of the message, using a secret key K
- the receiver can verify the integrity of M using C if he knew k
- C does not guarantee the confidentiality of M, that is, it does guarantee that the message was not seen by eve
- The secret key is known by the emitter and the receiver

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
    - the intuition behind this attack : merkle damgard constructs have an internal state that gets updated
      with each bloc of the message, to get h(m+n), we need to somehow make the internal state of the hash func
      equal to h(m), and then perform another round of the func with n as the last bloc
  - so if we knew mac(k,m) == h(k+m), we will be able to authenticate m+n for any n without knowing k or m
  - merkle-damgard construct is vulnerable to length extension

- length extension free approach : **mac(k,m)=h(k1 + h(k2+m))**
- k1 and k2 are 2 subkeys derived from K
- k1 and k2 are derived by xor-ing K with 2 constants [img](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/SHAhmac.svg/1024px-SHAhmac.svg.png)

## Authenticated encryption : assure the authenticity and confidentiality

- An encryption does not guarantee the integrity of communication
- for example, for AES, every binary string is a valid cipher, and can be decrypted
- to guarantee the integrity of an encrypted message, we use an authentication encryption that involves a mac
- we use the same key for decryption and for authentication
- 3 approaches : mac then encrypt, or encrypt then mac, man and encrypt
- encrypt then mac is preferred because mac-then-enc only guarantees the integrity of the message and not of the ciphertext

#### enc then mac

![](https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Authenticated_Encryption_EtM.png/330px-Authenticated_Encryption_EtM.png)

#### mac then enc

![](https://upload.wikimedia.org/wikipedia/commons/a/ac/Authenticated_Encryption_MtE.png)
A MAC is produced based on the plaintext, and the plaintext is encrypted without the MAC. The plaintext's MAC and the ciphertext are sent together
![](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Authenticated_Encryption_EaM.png/330px-Authenticated_Encryption_EaM.png)
