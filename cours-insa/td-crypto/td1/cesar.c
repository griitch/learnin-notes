#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define SIZE 100

char * str = "LESUJETDECETPSERALETUDEDESCHIFFREMENTSDEBASE";

int key_gen(){
    return rand() % 26;
}

void encrypt(int key, char * plain, char * cipher){

    int i = 0;

    while( plain[i] != '\0') {
        cipher[i] = 'A' + (plain[i] - 'A' + key)%26;
        i++;
    }
    cipher[i] = '\0';
}

void decrypt(int key, char * plain, char * cipher){
    int i = 0;

    while( cipher[i] != '\0') {
        plain[i] = 'A' + (cipher[i] - 'A' - key + 26)%26;
        i++;
    }
    cipher[i] = '\0';
}

void generate_challenge(char * cipher){ 
    encrypt(key_gen(), str, cipher);
}

void attack(char * cipher){

    int map[26] = {0};

    int i = 0;

    while(cipher[i] != '\0') {
        map[cipher[i] - 'A']++;
        i++;
    }

    for(i = 0 ; i < 26 ; i++) {
        printf("letter : %c, frequency : %d\n",'A'+i,map[cipher[i] - 'A']);
    }


    int max = map[0];    
    int maxIndex = 0;
    for (int i = 0; i < 26; i++) {     
       if(map[i] > max)    
           max = map[i];    
           maxIndex = i;
    }
    // we suppose that e was the most used letter in the plain message
    int key = ('E' - 'A' - maxIndex + 26) % 26;


    char test[strlen(cipher)];
    decrypt(key,test,cipher);
    printf("Plain : %s\n",test);
    printf("Cipher : %s\n",cipher);
    printf("Key : %d\n",key);
    }


int main(int argc, char *argv[]) {
    srand( time( NULL ) );
    int i;
    
    int key = key_gen();
    
    char plain[SIZE]; // Plaintext
    printf("message à chiffrer (en majuscules):\n");
    scanf("%s", plain);
    
    char plainRes[SIZE];
    char cipher[SIZE];
    plainRes[0] = '\0';
    cipher[0] = '\0';
    
    encrypt(key, plain, cipher); 
    decrypt(key, plainRes, cipher);
    
    printf("clé : %i\nMessage : %s\nChiffré : %s\nDéchiffré : %s\n", key, plain, cipher, plainRes);
    
    // generate_challenge(cipher);
    // attack(cipher);
}
