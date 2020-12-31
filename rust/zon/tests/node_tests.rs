//use zon::node::Node;

mod tests {
  #[test]
  fn eq_test_1() {
    let nd1 = zon::node::Node::new(0);
    let nd2 = zon::node::Node::new(1);
    assert_ne!(nd1, nd2);
  }

  #[test]
  fn eq_test_2() {
    let nd1 = zon::node::Node::new(0);
    let nd2 = zon::node::Node::new(0);
    assert_eq!(nd1, nd2);
  }
}