# -----------------------------------
# Program x86asm.s
# Compute and print the sum 0+...+N
# -----------------------------------

	    .section .data	# data section 
N:	  .long 1000	    # N=1000;
S:	  .long 0		    # S=0;
Msg:	.ascii "The sum from 0 to %d is %d\n\0" # Msg= "The sum from 0 to %d is %d\n\0" ;


	 .section .text	# instruction section
.globl main
main:
	pushl %ebp	    #ebp is stored in the stack
	movl %esp, %ebp	# esp=ebp;

 	# initialize
    movl N, %ebx	# ebx=1000;

 	# compute sum
L1:
	addl %ebx, S	# S+=ebx;
	decl %ebx       # ebx--;
	cmpl $0, %ebx   # if ebx==0
	jng  L2	    	# jump to L2
    movl $L1, %eax	# eax= L1 address
    jmp *%eax   	#jump to L1

L2:
	# print result
	pushl S	    	# push S to the stack
	pushl N	        # push N to the stack
	pushl $Msg  	# push msg's address to the stack
	call printf 	# prints Msg(with n,s as parameters)
	popl %eax   	# removes the Msg from the stack
	popl %eax   	# removes  N from the stack
	popl %eax   	# removes s from the stack

	# exit
	movl $0, %eax  	# eax=0;
    leave	    	#exit
 	ret             #return; 
