#include <stdio.h>
#include <stdlib.h>


int main(){

	char* tab = malloc(512);

	tab[0] = 0xE9;
	tab[1]= 0xFD;
	tab[2]=0xFF;
	int i;
	for(i = 4 ;i < 509 ; i++){
		tab[i]=0;
	}
	tab[510]=0x55;
	tab[511]=0xAA;
	FILE* file = fopen("test.bin","wb");
	fwrite(tab,512,1,file);

	return 0;
}
