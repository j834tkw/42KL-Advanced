section .text
    global ft_read
    extern __errno_location

; ssize_t ft_read(int fd, void *buf, size_t count)
; rdi = fd
; rsi = buf
; rdx = count
; Returns bytes read or -1 on error
ft_read:
    mov     rax, 0              ; syscall number for read
    syscall                     ; Perform syscall

    cmp     rax, 0              ; Check if error occurred
    jl      .error              ; If negative, handle error
    ret                         ; Return success value

.error:
    neg     rax                 ; Make error code positive
    mov     r8, rax             ; Save error code in r8
    call    __errno_location wrt ..plt ; Get address of errno (using PLT)
    mov     [rax], r8           ; Set errno
    mov     rax, -1             ; Return -1
    ret
