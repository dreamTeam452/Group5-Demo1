//author: Sharlina Keshava
//RUID: 140009007

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <math.h>
#include <pthread.h>
#include <sys/syscall.h>
#include <sys/time.h>
#include "uthash.h"
#include "mapred.h"

#define wordBufferSize 4096
wordDictionaryPtr global_wdptr = NULL;

void wordPrinter(wordDictionaryPtr* wdptr)
{
	wordDictionaryPtr f;

	for(f = *wdptr; f != NULL; f = f->hh.next)
	{
		printf("Key %s: Value %d\n", f->key, f->value);
	}
}

void readFile(char* name, wordDictionaryPtr* wdptr)
{
	int c;
	FILE* fp;
	struct dirent *data;
	char *nextName,
	*relPath,
	*token,
	*saveptr;
	char word[wordBufferSize];
	wordDictionaryPtr tempWDPtr;

	if(name == NULL || name[strlen(name)-1] == '.')
		return;
	if ((fp = fopen(name, "r")) != NULL) 
	{
		//Loop over the characters of the file in order to print them to the console
		while (fgets(word, wordBufferSize, fp) != NULL) 
		{
			token = strtok_r(word, " .,:;!?/\'\"*#-_<>()~1234567890\r\n",&saveptr);
			while (token) 
			{

				wordDictionaryPtr tempNode = NULL;
				HASH_FIND_STR(*wdptr, token, tempNode);
				if (tempNode) 
					tempNode->value++;
					else 
					{
						wordDictionaryPtr addWord;
						addWord = malloc(sizeof(struct wordDictionary));
						addWord->key = (char*) calloc(strlen(token), sizeof(char));
						strcpy(addWord->key, token);
						addWord->value = 1;

						HASH_ADD_STR(*wdptr, key, addWord);
					}

				token = strtok_r(NULL, " .,:;!?\'\"*#-_<>()~1234567890\r\n",&saveptr);
			}
		}
		fclose(fp);
	} 
	else 
	{
		fprintf(stderr , "ERROR: %s not a directory or file.\n", name);
	}
}

void mapFile(char* infile, int fileNum, wordDictionaryPtr *holder)
{
	int len;
	char *file, *buffer;
	if (fileNum == 0)
		len = 1;
	else
		len = floor(log10(abs(fileNum))) + 1;    
	file = (char*) calloc(strlen(infile) + len + 2, sizeof(char));
	buffer = (char*) calloc(len, sizeof(char));
	sprintf(buffer, "%d", fileNum);
	strcat(file, infile);
	strcat(file, ".");
	strcat(file, buffer);
	strcat(file, "\0");
	readFile(file, holder);
	printf("%s.%d have %d words\n", infile, fileNum, HASH_COUNT(*holder));
}

void* mapControl(void *arg)
{
	_thread_id *p = (_thread_id *)arg;
	mapFile(p->file, p->id, &(p->wdptr) );
	return (NULL);
}

int sortWord(wordDictionaryPtr ptr1, wordDictionaryPtr ptr2)
{
	return strcmp(ptr1->key, ptr2->key);
}

void freeHash(wordDictionaryPtr wdptr)
{
	wordDictionaryPtr ptr;
	for(ptr = wdptr; ptr != NULL; ptr = ptr->hh.next)
	{
		HASH_DEL(wdptr, ptr);
		free(ptr);
	}
}

int main(int argc, char** argv)
{
	int numReducers;
	typeOfRun = argv[2];
	threadOrProc = argv[4];
	numMappers = argv[6];
	numReducers = atoi(argv[8]);
	infile = argv[9];
	outfile = argv[10];
	FILE* output;
	char safety,
	tooMany;
	char *typeOfRun,
	*threadOrProc,
	*infile,
	*outfile,
	*numMappers;

	if (argc != 11)
	{
		fprintf(stderr, "ERROR: Incorrect number of arguments\n");
		return 0;
	}

	if (strcmp(outfile,"mapred.o") == 0 || strcmp(outfile,"Makefile") == 0 || strcmp(outfile,"readme.pdf") == 0)
	{
		fprintf(stderr, "Please don't try to overwrite our sourcefiles with the output\n");
		return 0;
	}

	if ((output = fopen(outfile, "r")) != NULL)
	{
		fclose(output);
		fprintf(stdout, "The file designated for output already exists, are you sure you wish to overwite it? (y/n): ");
		if(fscanf(stdin, "%c%c", &safety, &tooMany)!=2||safety!='y'||tooMany!='\n')
		{
			fprintf(stdout, "Ending program.\n");
			return 0;
		}
	}
	output = fopen(outfile,"w");

	pid_t childLabor, lazyParent;
	int status = 0;
	childLabor = fork();
	if (childLabor == 0)
	{
		char* splitFile[] = {"./split.sh", infile, numMappers, '\0'};
		if (execvp("./split.sh",splitFile) < 0)
			perror("./split.sh");
	}
	else if (childLabor < 0)
		perror("child");

	int numMappers = atoi(numMappers);
	int i, r;
	pthread_t *threads;
	pthread_attr_t pthread_custom_attr;
	_thread_id *p;

	threads = (pthread_t *) malloc(numMappers*sizeof(*threads));
	pthread_attr_init(&pthread_custom_attr);

	p = (_thread_id *) malloc(sizeof(_thread_id)*numMappers);

	for (i = 0; i < numMappers; i++) 
	{
		p[i].id = i;
		p[i].file = (char*) calloc(strlen(infile), sizeof(char));
		strcpy(p[i].file, infile);
		p[i].wdptr = NULL;
		pthread_create(&threads[i], &pthread_custom_attr, mapControl, (void *)(p+i));
	}
	for (i = 0; i < numMappers; i++) 
	{
		pthread_join(threads[i],NULL);
	}

	for (i = 0; i < numMappers; i++) 
	{
		wordDictionaryPtr *master = &global_wdptr;
		wordDictionaryPtr *wdptr = &(p[i].wdptr);
		wordDictionaryPtr s;
		for(s = *wdptr; s != NULL; s = s->hh.next)
		{
			char* word = s->key;
			wordDictionaryPtr* wdptr = master;
			int count = s->value;
			wordDictionaryPtr tempNode = NULL;
			HASH_FIND_STR(*wdptr, word, tempNode);
			if (tempNode) 
				tempNode->value+= count;
				else 
				{
					wordDictionaryPtr addWord;
					addWord = malloc(sizeof(struct wordDictionary));
					addWord->key = (char*) calloc(strlen(word), sizeof(char));
					strcpy(addWord->key, word);
					addWord->value = 1;

					HASH_ADD_STR(*wdptr, key, addWord);
				}
		}
	}

	if (strcmp(typeOfRun, "sort") == 0)
	{
		char* file = outfile;
		wordDictionaryPtr* wdptr = &global_wdptr;

		FILE* fp;
		wordDictionaryPtr ptr;

		if ((fp = fopen(file, "w+")) != NULL)
		{
			HASH_SORT(*wdptr, sortWord);
			for(ptr = *wdptr; ptr != NULL; ptr = ptr->hh.next)
				fprintf(fp, "%s\n", ptr->key);
		}

		fclose(fp);
	}
	else
	{
		wordCount(outfile, &global_wdptr);
		char* file = outfile;
		wordDictionaryPtr* wdptr = &global_wdptr;
		FILE* fp;
		wordDictionaryPtr ptr;
		if ((fp = fopen(file, "w+")) != NULL)
			for(ptr = *wdptr; ptr != NULL; ptr = ptr->hh.next)
				fprintf(fp, "%s : %d\n", ptr->key, ptr->value);
		fclose(fp);

	}
	fclose(output);

	int len = strlen(infile) + 5;
	char* cmd = (char*) calloc(len, sizeof(char));
	strcat(cmd, "rm ");
	strcat(cmd, infile);
	strcat(cmd, ".*");
	cmd[len] = '\0';
	system(cmd);

	return 0;
}

