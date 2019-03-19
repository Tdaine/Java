package LinkList;

//import LinkList.LinkImpl;
//import LinkList.ILink;

public class Test {
    public static void main(String[] args) {
        LinkImpl link = new LinkImpl();
        link.add(1);
        link.add(2);
        link.add(3);
        link.printLink();
        new ReviewLinkList().printFromTailToFront(link.getHead());
    }
}
