import scala.collection.mutable.{ Set }

object Kruskal {
  def GetMST(graph: Set[Edge]): Set[Edge] = {
    var graphOrderByEdgeWeight = List.empty[Edge] ++ graph;
    graphOrderByEdgeWeight = graphOrderByEdgeWeight.sortWith(_.weight < _.weight);
    val sets = Set.empty[Set[Node]];
    val mst = Set.empty[Edge];

    while(!graphOrderByEdgeWeight.isEmpty){
      val edge = graphOrderByEdgeWeight.head;

      if(!setContainingBoth(sets, edge).isDefined){
        val setContainingFirstNode = setContainingNode(sets, edge.firstNode);
        val setContainingSecondNode = setContainingNode(sets, edge.secondNode);

        if(setContainingFirstNode.isDefined && setContainingSecondNode.isDefined){
          sets -= setContainingFirstNode.get;
          sets -= setContainingSecondNode.get;
          sets += (setContainingFirstNode.get ++ setContainingSecondNode.get);
        } else if(setContainingFirstNode.isDefined) {
          setContainingFirstNode.get += edge.secondNode;
        } else if(setContainingSecondNode.isDefined) {
          setContainingSecondNode.get += edge.firstNode;
        } else {
          sets += Set(edge.firstNode, edge.secondNode);
        }
        mst += edge;
      }

      graphOrderByEdgeWeight = graphOrderByEdgeWeight.drop(1);
    }

    return mst;
  }

  def setContainingBoth(setOfSets: Set[Set[Node]], edge: Edge): Option[Set[Node]] ={
    return setOfSets.find(s => s.contains(edge.firstNode) && s.contains(edge.secondNode));
  }

  def setContainingNode(setOfSets: Set[Set[Node]], node: Node): Option[Set[Node]] ={
    return setOfSets.find(s => s.contains(node));
  }
}
