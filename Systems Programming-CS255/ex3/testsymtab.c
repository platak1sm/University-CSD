#include <stdio.h>
#include <stdlib.h>
int main(){
	int i;
    FILE *fp;
    if((fp = fopen("data3", "w")) == NULL){
		fprintf(stderr, "\nCan not write file: data3\n\n");
		exit(EXIT_FAILURE);
	}
	for(i=0;i<35;i++){
        fputc('0',fp);
    } 
    fclose(fp);
	return 0;

}