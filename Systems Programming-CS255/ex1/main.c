#include <stdio.h>
#include <stdlib.h>


int main()
{
	int Y;
	char ch = 'a';
	Y = 2;
	__asm__(".byte 0x0f, 0x0b");
	if (ch)
		printf("%d", Y);
	return 0;
}