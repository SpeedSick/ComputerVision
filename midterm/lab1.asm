       .text                         # program instruction
       .globl main
main:
       li   $v0, 4                   # sys call code print_str 
       la   $a0, str                 # addr of string to print 
       syscall                       # print the promt
       li   $v0, 5                   # sys code read integer
       syscall 
       move $t0, $v0                 # $t0 is the variable X
       li   $v0, 4                   # sys call code print_str 
       la   $a0, str                 # addr of string to print 
       syscall                       # print the promt                 
       li   $v0, 5                   # sys code read integer
       syscall 
       move $t1, $v0                 # $t1 is the variable Y
       add  $t2, $t0, $t1            # SUM <- X + Y
       li   $v0, 4                   # sys call code print_str 
       la   $a0, outpr               # addr of string to print 
       syscall                       # print the promt
       li   $v0, 1                   # sys code print integer
       move $a0, $t2                 # integer to print
       syscall                       # now print it
       li   $v0, 10                  # sys code halt
       syscall
       
       .data 
str:    .asciiz "Enter a value:" 
outpr:  .asciiz "The sum is:"
     
       