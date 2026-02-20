section .text
    global ft_strdup
    extern malloc
    extern ft_strlen
    extern __errno_location

; char *ft_strdup(const char *s)
; rdi = pointer to string
; Returns pointer to duplicated string or NULL on error
ft_strdup:
    push    rdi                 ; Save original string pointer
    call    ft_strlen wrt ..plt ; Get length of string in rax
    inc     rax                 ; Add 1 for null terminator (rax will have ft_strlen's returned string length)
    mov     rdi, rax            ; Move size to rdi for malloc
    call    malloc wrt ..plt    ; Allocate memory

    test    rax, rax            ; Check if malloc returned NULL (rax will have malloc's returned pointer)
    jz      .error              ; If NULL, return NULL

    pop     rsi                 ; Restore original string pointer to rsi
    mov     rdi, rax            ; Move allocated memory pointer to rdi
    push    rax                 ; Save allocated memory pointer
    xor     rcx, rcx            ; Initialize index to 0

.copy_loop:
    mov     dl, BYTE [rsi + rcx] ; Load byte from source
    mov     BYTE [rdi + rcx], dl ; Store byte to destination
    test    dl, dl              ; Check if null terminator
    jz      .done               ; If yes, exit
    inc     rcx                 ; Increment index
    jmp     .copy_loop          ; Continue loop

.done:
    pop     rax                 ; Restore allocated memory pointer to rax
    ret                         ; Return pointer

.error:
    pop     rdi                 ; Clean up stack
    xor     rax, rax            ; Return NULL
    ret
