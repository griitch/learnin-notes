#include <stdint.h>
#include <stdio.h>
#include <string.h>

uint32_t F(uint32_t v, const uint32_t k[4], int i)
{
    return ((v << 4) + k[2 * (i % 2)]) ^ (v + (0x9E3779B9 * ((i + 2) / 2))) ^ ((v >> 5) + k[1 + 2 * (i % 2)]);
}

void feistel_enc(uint32_t v[2], const uint32_t k[4])
{

    for (size_t i = 0; i < 64; i++)
    {
        uint32_t li = v[0];
        uint32_t ri = v[1];

        uint32_t l2 = ri;
        uint32_t r2 = li + F(ri, k, i);

        v[0] = l2;
        v[1] = r2;
    }
}

void feistel_dec(uint32_t v[2], const uint32_t k[4])
{
    for (size_t i = 0; i < 64; i++)
    {
        uint32_t liPlusOne = v[0];
        uint32_t riPlusOne = v[1];

        uint32_t ri = liPlusOne;
        uint32_t li = riPlusOne - F(liPlusOne, k, 64 - i - 1);
        v[0] = li;
        v[1] = ri;
    }
}

void ecb_encrypt(uint32_t *v, const uint32_t k[4], int nb_blocks)
{
    for (size_t i = 0; i < nb_blocks; i++)
    {
        feistel_enc(v[i], k);
    }
}

void ecb_decrypt(uint32_t *v, const uint32_t k[4], int nb_blocks)
{
    for (size_t i = 0; i < nb_blocks; i++)
    {
        feistel_dec(v[i], k);
    }
}

void cbc_encrypt(uint32_t *v, uint32_t vect[2], const uint32_t k[4], int nb_blocks)
{
}

void cbc_decrypt(uint32_t *v, uint32_t vect[2], const uint32_t k[4], int nb_blocks)
{
    // TODO
}

void ofb_stream(uint32_t *stream, uint32_t vect[2], const uint32_t k[4], int nb_blocks)
{
    // TODO
}

void ofb_encrypt(uint32_t *v, uint32_t *stream, int nb_blocks)
{
    // TODO
}

int main()
{
    uint32_t k[4];
    uint32_t v[8];
    uint32_t vect[2];

    // Key
    k[0] = 0b01110100011110101010010010010010;
    k[1] = 0b11110000101010100011010101101000;
    k[2] = 0b00100010111010010111010110110011;
    k[3] = 0b00001010110111010110100110100011;

    // Plaintext (4 blocks)
    v[0] = 0b01010101010101010101010101010101;
    v[1] = 0b11111111111111110000000000000000;
    v[2] = 0b10101010101010101010101010101010;
    v[3] = 0b10101010101010101010101010101010;
    v[4] = 0b01010101010101010101010101010101;
    v[5] = 0b01010101010101010101010101010101;
    v[6] = 0b10101010101010101010101010101010;
    v[7] = 0b10101010101010101010101010101010;

    // Initialisation vector
    vect[0] = 0b00101101110101110101110010110001;
    vect[1] = 0b01110101101101100010101101010011;

    printf("Plaintext:\n");
    printf("%u\n", v[0]);
    printf("%u\n", v[1]);

    feistel_enc(v, k);

    printf("Ciphertext:\n");
    printf("%u\n", v[0]);
    printf("%u\n", v[1]);

    feistel_dec(v, k);

    printf("Decryption:\n");
    printf("%u\n", v[0]);
    printf("%u\n", v[1]);

    return 0;
}
