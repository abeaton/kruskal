import org.scalatest.{ FlatSpec }
import scala.collection.mutable.{ Set }

class KruskalTest extends FlatSpec {

  "Kruskals" should "return only edge if graph contains one edge" in {
    val firstNode = new Node("A");
    val secondNode = new Node("B");
    val onlyEdge = new Edge(firstNode, secondNode, 2);
    val graph = Set(onlyEdge);

    val mst = Kruskal.GetMST(graph);
    assert(mst == graph);
  }

  "Kruskals" should "return all edges if graph disjoint" in {
    val a = new Node("A");
    val b = new Node("B");
    val firstEdge = new Edge(a, b, 2);

    val c = new Node("C");
    val d = new Node("D");
    val secondEdge = new Edge(c, d, 3);

    val e = new Node("E");
    val f = new Node("F");
    val thirdEdge = new Edge(e, f, 4);

    val graph = Set(firstEdge, secondEdge, thirdEdge);

    val mst = Kruskal.GetMST(graph);
    assert(mst == graph);
  }

  "Kruskals" should "return all edges if graph is linear" in {
    val a = new Node("A");
    val b = new Node("B");
    val c = new Node("C");
    val d = new Node("D");
    val e = new Node("E");
    val f = new Node("F");

    val firstEdge = new Edge(a, b, 2);
    val secondEdge = new Edge(b, c, 3);
    val thirdEdge = new Edge(c, d, 4);
    val fourthEdge = new Edge(d, e, 2);
    val fifthEdge = new Edge(e, f, 3);

    val graph = Set(firstEdge, secondEdge, thirdEdge, fourthEdge, fifthEdge);

    val mst = Kruskal.GetMST(graph);
    assert(mst == graph);
  }

  "Kruskals" should "return minimum subset of edges if graph is interconnected" in {
    val a = new Node("A");
    val b = new Node("B");
    val c = new Node("C");
    val d = new Node("D");

    val ab = new Edge(a, b, 3);
    val ac = new Edge(a, c, 2);
    val ad = new Edge(a, d, 1);
    val bc = new Edge(b, c, 4);
    val bd = new Edge(b, d, 5);
    val cd = new Edge(c, d, 6);

    val graph = Set(ab, ac, ad, bc, bd, cd);

    val mst = Kruskal.GetMST(graph);
    assert(mst == Set(ad, ac, ab));
  }

  "Kruskals" should "should find mst of complicated graph" in {
    val a = new Node("A");
    val b = new Node("B");
    val c = new Node("C");
    val d = new Node("D");
    val e = new Node("E");
    val f = new Node("F");
    val g = new Node("G");
    val h = new Node("H");
    val i = new Node("I");
    val j = new Node("J");

    val ab = new Edge(a, b, 1);
    val ac = new Edge(a, c, 3);
    val ad = new Edge(a, d, 2);
    val be = new Edge(b, e, 3);
    val bf = new Edge(b, f, 8);
    val cg = new Edge(c, g, 5);
    val ch = new Edge(c, h, 4);
    val df = new Edge(d, f, 4);
    val dg = new Edge(d, g, 2);
    val ei = new Edge(e, i, 7);
    val fj = new Edge(f, j, 7);
    val gi = new Edge(g, i, 1);
    val hj = new Edge(h, j, 8);

    val graph = Set(ab, ac, ad, be, bf, cg, ch, df, dg, ei, fj, gi, hj);

    val mst = Kruskal.GetMST(graph);
    assert(mst == Set(ab, ac, ad, be, df, dg, ch, fj, gi));
  }
}
