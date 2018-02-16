#include <stdio.h>

typedef struct {
  int code;
  char name[50];
  char tel[13];
}Data;

typedef struct{
  struct Snode *pLeft;
  Data data;
  struct SNode *pRight;
}SNode;

typedef struct{
  int size;
  struct SNode *pRight;
}SHead;

void push(sHead head);

int main(){

  int i;
  SHead h1 = malloc(sizeof(SHead));
  h1.pRight = NULL;
  h1.size = 0;

  while (i!=5){
    printf("1 - Add
            2 - Delete
            3 - Busca
            4 - Print
            5 - Sair\n");
    scanf("%d\n", &i);
    switch (i) {
      case 1:
        push(h1);
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
      default:
        printf("Valor Invalido!\n");
        break;
    }
  }

  free (h1);
  return 0;
}

void push(sHead head){

}
