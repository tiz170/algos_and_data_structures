void inOrder2(BinTree *root)      //非递归中序遍历
{
  stack<BinTree*> s;
  BinTree *p=root;
  while(p!=NULL||!s.empty())
  {
      while(p!=NULL)
      {
          s.push(p);
          p=p->lchild;
      }
      if(!s.empty())
      {
          p=s.top();
          cout<<p->data<<" ";
          s.pop();
          p=p->rchild;
      }
  }    
}