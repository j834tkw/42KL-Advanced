NAME = libasm.a

NASM = nasm
NASMFLAGS = -f elf64

CC = gcc
CFLAGS = -Wall -Wextra -Werror

AR = ar
ARFLAGS = rcs

SRC_DIR = src
OBJ_DIR = obj

SRCS = $(SRC_DIR)/ft_strlen.s \
       $(SRC_DIR)/ft_strcpy.s \
       $(SRC_DIR)/ft_strcmp.s \
       $(SRC_DIR)/ft_write.s \
       $(SRC_DIR)/ft_read.s \
       $(SRC_DIR)/ft_strdup.s

OBJS = $(SRCS:$(SRC_DIR)/%.s=$(OBJ_DIR)/%.o)

MAIN = main.c
MAIN_OBJ = main.o
EXEC = test_libasm

all: $(NAME)

$(NAME): $(OBJS)
	$(AR) $(ARFLAGS) $(NAME) $(OBJS)

$(OBJ_DIR)/%.o: $(SRC_DIR)/%.s
	@mkdir -p $(OBJ_DIR)
	$(NASM) $(NASMFLAGS) $< -o $@

test: $(NAME) $(MAIN)
	$(CC) $(CFLAGS) $(MAIN) -L. -lasm -o $(EXEC)

clean:
	rm -rf $(OBJ_DIR)
	rm -f $(MAIN_OBJ)

fclean: clean
	rm -f $(NAME)
	rm -f $(EXEC)

re: fclean all

.PHONY: all clean fclean re test
