#include<cstdio>
#include<iostream>
#include<stdlib.h>
#include<cstring>
using namespace std;
struct node
{
    struct node* next[26];
    bool isword;
    int freq;
    int heapindex;
};
struct heapNode
{
    int cnt;    //frequencey of the word
    struct node* root;  //position of the word in the true
    char *word; //this is the actual word
};
struct minHeap
{
    int capacity;
    int counter;
    struct heapNode* a;
};

node* newnode()
{
    struct node* temp = (struct node*)malloc(sizeof(struct node));
    temp->isword=false;
    temp->freq = 0;
    temp->heapindex =-1;
    for(int i=0;i<26;i++) temp->next[i]=NULL;
}
minHeap* createHeap(int k)
{
    struct minHeap* heap = (struct minHeap*)malloc(sizeof(struct minHeap));
    heap->capacity = k;
    heap->counter = 0;
    heap->a = new heapNode[k+1];
}
void swapNodes(struct heapNode*a, struct heapNode*b)
{
    struct heapNode temp = *a;
    *a=*b;
    *b = temp;
}

void minheapify(struct minHeap* heap, int index)
{
    int l = 2*index +1;
    int r = 2*index +2;
    int smallest = index;
    if(l<heap->counter && heap->a[l].cnt < heap->a[smallest].cnt) smallest = l;
    if(r<heap->counter && heap->a[r].cnt < heap->a[smallest].cnt) smallest = r;
    if(heap->a[smallest].cnt != heap->a[index].cnt)
    {
        heap->a[smallest].root->heapindex = index;
        heap->a[index].root->heapindex = smallest;
        swapNodes(&heap->a[smallest], &heap->a[index]);
        minheapify(heap, smallest);

    }
}
void buildheap(struct minHeap* heap)
{
    int n = heap->counter-1;
    int i = (n-1)/2;
    while(i>=0)
    {
        minheapify(heap, i);
        i--;
    }
}
void insertInHeap(struct node* root, char key[], struct minHeap* heap)
{
    printf("%s %d %d\n", key, heap->counter, heap->capacity);
    //the word is present in the minheap
    if(root->heapindex!=-1)
    {
        printf("in 1\n");
        ++(heap->a[root->heapindex].cnt);  //frequencey counter ++ in heap
        minheapify(heap, root->heapindex);
    }
    //word in not present and the heap is full
    else if(root->heapindex==-1 && heap->counter >= heap->capacity && root->freq > heap->a[0].cnt)
    {
        printf("in 2\n");
        heap->a[0].cnt = root->freq;
        heap->a[0].root = root;
        heap->a[0].word = new char[strlen(key)+1];
        strcpy(heap->a[0].word , key);
        root->heapindex = 0;
        minheapify(heap, 0);
    }
    //word is not present and heap is not full
    else if(root->heapindex==-1 && heap->counter < heap->capacity)
    {
        printf("in 3\n");
        int counter = heap->counter;
        printf("%d %d\n", counter, heap->capacity);
        heap->a[counter].cnt =1;
        heap->a[counter].word = new char[strlen(key)+1];
        strcpy(heap->a[counter].word , key);
        heap->a[counter].root = root;
        root->heapindex = counter;

        heap->counter++;
       buildheap(heap);
    }


}
void insertTrie(struct node* root, char key[], struct minHeap* heap)
{

    int len = strlen(key);
    for(int i=0;i<len;i++)
    {
        int index = key[i]-'a';
        if(!root->next[index])
            root->next[index]= newnode();
        root= root->next[index];
        if(i==len-1)
        {
            if(root->isword)
                root->freq++;
            else
            {
                root->freq=1;
                root->isword=true;
            }

        }
    }
    //root is pointing at the last alphabet of the word
    insertInHeap(root, key, heap);
}
void printHeap(struct minHeap * heap)
{
    for(int i=0;i<heap->counter;i++)
        printf("word: %s\t freq: %d\n", heap->a[i].word, heap->a[i].cnt);
}
void printKmost(int k)
{
    struct minHeap *heap = createHeap(k);
    struct node* root = newnode();
    char a[][9]={"Welcome", "to" ,"the" ,"world" ,"of", "Geeks" ,"This" ,"portal" ,"has"};
    for(int i=0;i<9;i++)
      {
          printf("%s\n",a[i]);
         insertTrie(root,a[i], heap);
         printHeap(heap);
      }
      printf("hi\n\n");
        printHeap(heap);
}
/*"Welcome", "to" ,"the" ,"world" ,"of", "Geeks" ,"This" ,"portal" ,"has" been created to provide well written well thought and well explained
solutions for selected questions If you like Geeks for Geeks and would like to contribute
here is your chance You can write article and mail your article to contribute at
geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help
thousands of other Geeks*/
int main()
{
    int k=5;
    printKmost(k);
}
