section .text
    global ft_strlen

; size_t ft_strlen(const char *s)
; rdi = pointer to string
; Returns length in rax
ft_strlen:
    xor     rax, rax            ; Initialize counter to 0

.loop:
    cmp     BYTE [rdi + rax], 0 ; Compare current byte with null terminator
    je      .done               ; If null terminator found, exit
    inc     rax                 ; Increment counter
    jmp     .loop               ; Continue loop

.done:
    ret                         ; Return length in rax
