#[derive(Debug)]
pub struct Node<T: PartialEq> {
  pub parent: Option<Box<Node<T>>>, 
  pub children: Option<Box<Vec<Node<T>>>>, 
  pub level: Option<i32>, 
  pub v: T
}

impl<T: PartialEq> Node<T> {
  pub fn new(v: T) -> Node<T> {
    Node {
      parent: None,
      children: None,
      level: None,
      v: v
    }
  }
}

impl<T: PartialEq> PartialEq for Node<T> {
  fn eq(&self, other: &Self) -> bool {
    self.v == other.v
  }
}