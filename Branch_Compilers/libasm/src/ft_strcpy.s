section .text
    global ft_strcpy

; char *ft_strcpy(char *dest, const char *src)
; rdi = dest pointer
; rsi = src pointer
; Returns dest in rax
ft_strcpy:
    push    rdi                 ; Save original dest pointer
    xor     rcx, rcx            ; Initialize index to 0

.loop:
    mov     al, BYTE [rsi + rcx] ; Load byte from src
    mov     BYTE [rdi + rcx], al ; Store byte to dest
    cmp     al, 0               ; Check if null terminator
    je      .done               ; If yes, exit
    inc     rcx                 ; Increment index
    jmp     .loop               ; Continue loop

.done:
    pop     rax                 ; Restore original dest pointer to rax
    ret                         ; Return dest
