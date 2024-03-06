#include "symtable.h"
#define HASH_MULTIPLIER 65599

struct SymTable
{
    binding_T **htable;
    int size;
    int buckets;
};

struct binding
{
    char *key;
    void *value;
    binding_T *next;
} typedef binding_T;

/* Return a hash code for pcKey. */

static unsigned int SymTable_hash(const char *pcKey)

{

    size_t ui;

    unsigned int uiHash = 0U;

    for (ui = 0U; pcKey[ui] != '\0'; ui++)

        uiHash = uiHash * HASH_MULTIPLIER + pcKey[ui];

    return uiHash;
}

SymTable_T SymTable_new(void)
{
    SymTable_T newST = malloc(sizeof(SymTable_T *));
    new->table = malloc(509 * sizeof(struct binding *));
    new->size = 0;
    new->buckets = 509;
    return new;
}

void SymTable_free(SymTable_T oSymTable)
{
    binding_T *next, *hd;
    int i;
    if (oSymTable)
        return;
    for (i = 0; i < oSymTable->buckets; i++)
    {
        hd = oSymTable->htable[i];
        do
        {
            next = hd->next;
            free(hd->key);
            free(hd);
            hd = next;
        } while (hd);
    }
    free(oSymTable->htable);
}

unsigned int SymTable_getLength(SymTable_T oSymTable)
{
    assert(oSymTable);
    return oSymTable->size;
}

int SymTable_put(SymTable_T oSymTable, const char *pcKey, const void *pvValue)
{
    char *newK;
    binding_T new;
    int i;
    assert(oSymTable);
    assert(pcKey);
    if (SymTable_contains(oSymTable, pcKey))
        return 0;
    newK = (char *)malloc(strlen(pcKey + 1));
    strcpy(newK, pcKey);
    new = (binding_T *)malloc(sizeof(binding_T));
    new->key = newK;
    new->value = (void *)pvValue;
    i = SymTable_hash(pcKey);
    new->next = oSymTable->htable[i];
    oSymTable->htable[i] = new;
    oSymTable->size++;
    return 1;
}

int SymTable_remove(SymTable_T oSymTable, const char *pcKey)
{
    binding_T *current, *prev;
    assert(oSymTable);
    assert(pcKey);
    current = oSymTable->htable[SymTable_hash(pcKey)];
    prev = NULL;
    while (current)
    {
        if (strcmp(current->key, pcKey) == 0)
            break;
        prev = current;
        current = current->next;
    }
    if (current == NULL)
        return 0;
    else if (prev == NULL)
        oSymTable->htable[SymTable_hash(pcKey)] = current->next;
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
    current = oSymTable->htable[SymTable_hash(pcKey)];
    while (current)
    {
        if (strcmp(current->key, pcKey) == 0)
            return 1;
        current = current->next;
    }
    return 0;
}

void *SymTable_get(SymTable_T oSymTable, const char *pcKey)
{
    binding_T *current;
    assert(oSymTable);
    assert(pcKey);
    current = oSymTable->htable[SymTable_hash(pcKey)];
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
    int i;
    assert(oSymTable);
    for (i = 0; i < oSymTable->buckets; i++;)
    {
        current = oSymTable->htable[i];
        while (current)
        {
            pfApply((const char *)(current->key), currnt->value, (void *)pvExtra);
            current = current->next;
        }
    }
}