; call BIOS interrupt for fun and profit
mov al, 02h ; 80x25 graphical mode (text)
mov ah, 00h ; change the video mode
int 10h ; here we call the interrupt
; call BIOS interrupt to display 'Real Mode'
mov ah, 0x0e ;print
mov al, 'R'
int 0x10
mov ah, 0x0e ;print
mov al, 'e'
int 0x10
mov ah, 0x0e ;print
mov al, 'a'
int 0x10
mov ah, 0x0e ;print
mov al, 'l'
int 0x10
mov ah, 0x0e ;print
mov al, ' '
int 0x10
mov ah, 0x0e ;print
mov al, 'M'
int 0x10
mov ah, 0x0e ;print
mov al, 'o'
int 0x10
mov ah, 0x0e ;print
mov al, 'd'
int 0x10
mov ah, 0x0e ;print
mov al, 'e'
int 0x10
loop:
jmp loop ; forever

times 510-($-$$) db 0 ;zeroeing
dw 0xaa55 ; magic number;
