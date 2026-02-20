#include "libasm.h"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>

#define GREEN "\033[0;32m"
#define RED "\033[0;31m"
#define YELLOW "\033[0;33m"
#define RESET "\033[0m"

void	print_test_header(const char *test_name)
{
	printf("\n" YELLOW "=== Testing %s ===" RESET "\n", test_name);
}

void	print_result(int passed)
{
	if (passed)
		printf(GREEN "✓ PASSED" RESET "\n");
	else
		printf(RED "✗ FAILED" RESET "\n");
}

void	test_ft_strlen(void)
{
	print_test_header("ft_strlen");

	const char *test1 = "";
	const char *test2 = "Hello";
	const char *test3 = "Hello, World!";
	const char *test4 = "This is a longer string for testing purposes";

	printf("Test 1 (empty string): ");
	print_result(ft_strlen(test1) == strlen(test1));

	printf("Test 2 (\"Hello\"): ");
	print_result(ft_strlen(test2) == strlen(test2));

	printf("Test 3 (\"Hello, World!\"): ");
	print_result(ft_strlen(test3) == strlen(test3));

	printf("Test 4 (long string): ");
	print_result(ft_strlen(test4) == strlen(test4));
}

void	test_ft_strcpy(void)
{
	print_test_header("ft_strcpy");

	char dest1[50];
	char dest2[50];
	char dest3[100];
	char dest4[100];

	printf("Test 1 (empty string): ");
	ft_strcpy(dest1, "");
	strcpy(dest2, "");
	print_result(strcmp(dest1, dest2) == 0);

	printf("Test 2 (\"Hello\"): ");
	ft_strcpy(dest1, "Hello");
	strcpy(dest2, "Hello");
	print_result(strcmp(dest1, dest2) == 0);

	printf("Test 3 (\"Hello, World!\"): ");
	ft_strcpy(dest3, "Hello, World!");
	strcpy(dest4, "Hello, World!");
	print_result(strcmp(dest3, dest4) == 0);

	printf("Test 4 (return value check): ");
	print_result(ft_strcpy(dest1, "Test") == dest1);
}

void	test_ft_strcmp(void)
{
	print_test_header("ft_strcmp");

	printf("Test 1 (equal strings): ");
	print_result(ft_strcmp("Hello", "Hello") == strcmp("Hello", "Hello"));

	printf("Test 2 (different strings): ");
	int ft_result = ft_strcmp("Hello", "World");
	int std_result = strcmp("Hello", "World");
	print_result((ft_result < 0 && std_result < 0) ||
				 (ft_result > 0 && std_result > 0) ||
				 (ft_result == 0 && std_result == 0));

	printf("Test 3 (empty strings): ");
	print_result(ft_strcmp("", "") == strcmp("", ""));

	printf("Test 4 (one empty): ");
	ft_result = ft_strcmp("Hello", "");
	std_result = strcmp("Hello", "");
	print_result((ft_result < 0 && std_result < 0) ||
				 (ft_result > 0 && std_result > 0) ||
				 (ft_result == 0 && std_result == 0));

	printf("Test 5 (prefix): ");
	ft_result = ft_strcmp("Hello", "Hello World");
	std_result = strcmp("Hello", "Hello World");
	print_result((ft_result < 0 && std_result < 0) ||
				 (ft_result > 0 && std_result > 0) ||
				 (ft_result == 0 && std_result == 0));
}

void	test_ft_write(void)
{
	print_test_header("ft_write");

	printf("Test 1 (write to stdout): ");
	ssize_t ret1 = ft_write(1, "Test\n", 5);
	print_result(ret1 == 5);

	printf("Test 2 (invalid fd - errno check): ");
	errno = 0;
	ssize_t ret2 = ft_write(-1, "Test", 4);
	print_result(ret2 == -1 && errno == EBADF);

	printf("Test 3 (write 0 bytes): ");
	ssize_t ret3 = ft_write(1, "Test", 0);
	print_result(ret3 == 0);
}

void	test_ft_read(void)
{
	print_test_header("ft_read");

	char buffer[100];
	int fd;

	// Create a temporary file for testing
	fd = open("/tmp/test_libasm.txt", O_CREAT | O_WRONLY | O_TRUNC, 0644);
	write(fd, "Hello, World!", 13);
	close(fd);

	fd = open("/tmp/test_libasm.txt", O_RDONLY);
	printf("Test 1 (read from file): ");
	ssize_t ret1 = ft_read(fd, buffer, 13);
	buffer[ret1] = '\0';
	print_result(ret1 == 13 && strcmp(buffer, "Hello, World!") == 0);
	close(fd);

	printf("Test 2 (invalid fd - errno check): ");
	errno = 0;
	ssize_t ret2 = ft_read(-1, buffer, 10);
	print_result(ret2 == -1 && errno == EBADF);

	// Clean up
	unlink("/tmp/test_libasm.txt");
}

void	test_ft_strdup(void)
{
	print_test_header("ft_strdup");

	printf("Test 1 (duplicate string): ");
	char *dup1 = ft_strdup("Hello, World!");
	print_result(dup1 != NULL && strcmp(dup1, "Hello, World!") == 0);
	free(dup1);

	printf("Test 2 (duplicate empty string): ");
	char *dup2 = ft_strdup("");
	print_result(dup2 != NULL && strcmp(dup2, "") == 0);
	free(dup2);

	printf("Test 3 (duplicate long string): ");
	char *dup3 = ft_strdup("This is a much longer string to test memory allocation");
	print_result(dup3 != NULL &&
				 strcmp(dup3, "This is a much longer string to test memory allocation") == 0);
	free(dup3);

	printf("Test 4 (memory independence): ");
	const char *original = "Original";
	char *duplicate = ft_strdup(original);
	int independent = (duplicate != original && strcmp(duplicate, original) == 0);
	free(duplicate);
	print_result(independent);
}

int	main(void)
{
	printf("\n" GREEN "╔════════════════════════════════════════╗" RESET "\n");
	printf(GREEN "║     LIBASM TEST SUITE                  ║" RESET "\n");
	printf(GREEN "╚════════════════════════════════════════╝" RESET "\n");

	test_ft_strlen();
	test_ft_strcpy();
	test_ft_strcmp();
	test_ft_write();
	test_ft_read();
	test_ft_strdup();

	printf("\n" GREEN "╔════════════════════════════════════════╗" RESET "\n");
	printf(GREEN "║     ALL TESTS COMPLETED                ║" RESET "\n");
	printf(GREEN "╚════════════════════════════════════════╝" RESET "\n\n");

	return (0);
}
