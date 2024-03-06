
#include "symtable.h"

struct binding
{
    char *key;
    void *value;
    struct binding *next;
} typedef binding_T;

struct SymTable
{
    binding_T *head;
    int size;
};

SymTable_T SymTable_new(void)
{
    SymTable_T newST = malloc(sizeof(SymTable_T *));
    newST->head = NULL;
    newST->size = 0;
    return newST;
}

void SymTable_free(SymTable_T oSymTable)
{
    binding_T *next, *hd;
    if (oSymTable)
        return;
    hd = oSymTable->head;
    do
    {
        next = hd->next;
        free(hd->key);
        free(hd);
        hd = next;
    } while (hd);
    free(oSymTable);
}

unsigned int SymTable_getLength(SymTable_T oSymTable)
{
    assert(oSymTable);
    oSymTable->size;
}

int SymTable_put(SymTable_T oSymTable, const char *pcKey, const void *pvValue)
{
    char *newk;
    binding_T *new;
    assert(oSymTable);
    assert(pcKey);
    if (SymTable_contains(oSymTable, pcKey))
        return 0;
    newk = (char *)malloc(strlen(pcKey) + 1);
    if (newk == NULL)
    {
        free(newk);
        return 0;
    }
    strcpy(newk, pcKey);
    new->key = newk;
    new->value = (void *)pvValue;
    new->next = oSymTable->head;
    oSymTable->head = new;
    oSymTable->size++;
    return 1;
}

int SymTable_remove(SymTable_T oSymTable, const char *pcKey)
{
    binding_T *current, *prev;
    assert(oSymTable);
    assert(pcKey);
    current = oSymTable->head;
    prev = NULL;
    while (current)
    {
        if (strcmp(current->key, pcKey) == 0)
            break; /*same*/
        prev = current;
        current = current->next;
    }
    if (current == NULL)
        return 0;
    else if (prev == NULL)
        oSymTable->head = current->next;
    else
        prev->next = current->next;
    free(current->key);
    free(current);
    oSymTable->size--;
    return 1;
}

int SymTable_contains(SymTable_T oSymTable, const char *pcKey)
{
    binding_T *current;
    assert(oSymTable);
    assert(pcKey);
    current = oSymTable->head;
    while (current)
    {
        if (strcmp(current->key, pcKey) == 0)
            return 1; /*same*/
        current = current->next;
    }
    return 0;
}

void *SymTable_get(SymTable_T oSymTable, const char *pcKey)
{
    binding_T *current;
    assert(oSymTable);
    assert(pcKey);
    current = oSymTable->head;
    while (current)
    {
        if (strcmp(current->key, pcKey) == 0)
            return current->value;
        current = current->next;
    }
    return NULL;
}

void SymTable_map(SymTable_T oSymTable, void (*pfApply)(const char *pcKey, void *pvValue, void *pvExtra), const void *pvExtra)
{
    binding_T *current;
    current = oSymTable->head;
    assert(oSymTable);
    while (current)
    {
        pfApply((const char *)(current->key), current->value, (void *)pvExtra);
        current = current->next;
    }
}
