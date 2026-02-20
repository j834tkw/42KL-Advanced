section .text
    global ft_strcmp

; int ft_strcmp(const char *s1, const char *s2)
; rdi = s1 pointer
; rsi = s2 pointer
; Returns difference in rax
ft_strcmp:
    xor     rcx, rcx            ; Initialize index to 0

.loop:
    movzx   rax, BYTE [rdi + rcx] ; Load byte from s1 (movzx clears upper bits and treats bytes as unsigned (strcmp behavior))
    movzx   rdx, BYTE [rsi + rcx] ; Load byte from s2

    cmp     rax, rdx            ; Compare bytes
    jne     .diff               ; If different, calculate difference

    test    al, al              ; Test one byte ('\0'); chars are equal already, so checking al is enough, instead of rax or rdx
    je      .equal              ; If yes, strings are equal

    inc     rcx                 ; Increment index
    jmp     .loop               ; Continue loop

.diff:
    sub     rax, rdx            ; Calculate difference
    ret                         ; Return difference

.equal:
    xor     rax, rax            ; Return 0 (strings are equal)
    ret
